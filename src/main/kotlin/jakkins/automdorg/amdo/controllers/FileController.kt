package jakkins.automdorg.amdo.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController {

    private var logger: Logger = LoggerFactory.getLogger(FileController::class.java)

    @PostMapping("/upload")
    fun uploadFile(@RequestPart files: List<MultipartFile>): String {
        try {
            logger.info(files.size.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return "Failure"
        }
        return "Success"
    }

}