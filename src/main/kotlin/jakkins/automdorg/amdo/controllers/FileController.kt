package jakkins.automdorg.amdo.controllers

import jakkins.automdorg.amdo.models.entities.FileEntity
import jakkins.automdorg.amdo.repositories.FileRepository
import jakkins.automdorg.amdo.utils.HashHelper
import org.apache.tika.Tika
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class FileController {

    private var logger: Logger = LoggerFactory.getLogger(FileController::class.java)
    private val tika = Tika()

    @Autowired
    private lateinit var hashHelper: HashHelper

    @Autowired
    private lateinit var fileRepository: FileRepository

    @PostMapping("/upload")
    fun handleFileUpload(@RequestParam("files") files: Array<MultipartFile?>): ResponseEntity<String> {
        for (file in files) {
            if (!isFileTypeValid(file!!)) {
                throw IllegalArgumentException("Incorrect file type: PDF or Markdown required")
            }
            try {
                val newFile = FileEntity(file.originalFilename!!, hashHelper.calculateHash(file.inputStream))
                fileRepository.save(newFile)
            } catch (e: Exception) {
                if (e.message != null && e.message!!.contains("UNIQUE constraint failed: files.hash")) {
                    return ResponseEntity("hash already exists", HttpStatus.INTERNAL_SERVER_ERROR)
                }
                return ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        return ResponseEntity("File saved successfully", HttpStatus.OK)
    }

    private fun isFileTypeValid(file: MultipartFile): Boolean {
        val detectedMimeType = tika.detect(file.inputStream)
        val mediaType = MediaType.parseMediaType(detectedMimeType)
        if (MediaType.APPLICATION_PDF_VALUE == mediaType.toString()) return true
        return MediaType.TEXT_PLAIN_VALUE == mediaType.toString() && file.originalFilename!!.endsWith(".md")
    }

}