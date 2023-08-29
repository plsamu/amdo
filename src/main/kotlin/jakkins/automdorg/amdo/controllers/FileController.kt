package jakkins.automdorg.amdo.controllers

import jakarta.servlet.http.HttpServletResponse
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

    // TODO show already saved error
    // TODO
    //      "multipart/form-data" request without html form
    //      https://stackoverflow.com/questions/19617996/file-upload-without-form


    @PostMapping("/upload")
    fun handleFileUpload(@RequestParam("files") files: Array<MultipartFile?>, response: HttpServletResponse): Any {
        for (file in files) {
            if (!isFileTypeValid(file!!)) {
                throw IllegalArgumentException("Incorrect file type: PDF or Markdown required")
            }
            try {
                val newFile = FileEntity(file.originalFilename!!, hashHelper.calculateHash(file.inputStream))
                fileRepository.save(newFile)
            } catch (e: Exception) {
                if (e.message != null && e.message!!.contains("UNIQUE constraint failed: files.hash")) {
                    val entities = HashMap<String, String>()
                    entities["error"] = "Hash already exists"
                    return response.sendError(500, "Hash already exists")
                    // return ResponseEntity<Any>(entities, HttpStatus.INTERNAL_SERVER_ERROR)
                }
                return ResponseEntity<Any>(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("File saved successfully")
        // return "upload_success" // "upload_success.html"
    }

    private fun isFileTypeValid(file: MultipartFile): Boolean {
        val detectedMimeType = tika.detect(file.inputStream)
        val mediaType = MediaType.parseMediaType(detectedMimeType)
        if (MediaType.APPLICATION_PDF_VALUE == mediaType.toString()) return true
        return MediaType.TEXT_PLAIN_VALUE == mediaType.toString() && file.originalFilename!!.endsWith(".md")
    }

}