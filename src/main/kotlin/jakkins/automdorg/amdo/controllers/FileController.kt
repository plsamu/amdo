package jakkins.automdorg.amdo.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class FileController {

    private var logger: Logger = LoggerFactory.getLogger(FileController::class.java)

    @PostMapping("/upload")
    fun handleFileUpload(@RequestParam("files") files: Array<MultipartFile?>): String? {
        for (file in files) {
            // You can access file information using methods like file.getOriginalFilename(), file.getSize(), etc.
            // You can also save the file to your desired location or perform other processing.
        }
        return "upload_success" // "upload_success.html"
    }

}