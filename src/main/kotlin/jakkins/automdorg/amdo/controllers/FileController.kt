package jakkins.automdorg.amdo.controllers

import jakkins.automdorg.amdo.models.entities.FileEntity
import jakkins.automdorg.amdo.repositories.FileRepository
import jakkins.automdorg.amdo.utils.HashHelper
import org.apache.tika.Tika
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class FileController {

    private var logger: Logger = LoggerFactory.getLogger(FileController::class.java)
    private val tika = Tika();

    @Autowired
    private lateinit var hashHelper: HashHelper
    @Autowired
    private lateinit var fileRepository: FileRepository

    @PostMapping("/upload")
    fun handleFileUpload(@RequestParam("files") files: Array<MultipartFile?>): String? {
        for (file in files) {
            if (!isFileTypeValid(file!!)) {
                throw IllegalArgumentException("Incorrect file type: PDF or Markdown required")
            }
            val newFile = FileEntity(file.originalFilename!!, hashHelper.calculateHash(file.inputStream))
            fileRepository.save(newFile)
        }
        return "upload_success" // "upload_success.html"
    }

    private fun isFileTypeValid(file: MultipartFile): Boolean {
        val detectedMimeType = tika.detect(file.inputStream)
        val mediaType = MediaType.parseMediaType(detectedMimeType)
        if (MediaType.APPLICATION_PDF_VALUE == mediaType.toString()) return true
        return MediaType.TEXT_PLAIN_VALUE == mediaType.toString() && file.originalFilename!!.endsWith(".md")
    }

}