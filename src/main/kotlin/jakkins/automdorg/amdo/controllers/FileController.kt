package jakkins.automdorg.amdo.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class FileController {

    private var logger: Logger = LoggerFactory.getLogger(FileController::class.java)

    @PostMapping("/upload")
    @ResponseBody
    fun uploadFile(@RequestParam("file") file: MultipartFile): String {
        try {
            logger.info(file.originalFilename)
        } catch (e: Exception) {
            e.printStackTrace()
            return "Failure"
        }
        return "Success"
    }

}