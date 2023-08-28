package jakkins.automdorg.amdo.controllers

import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HomeController {

    @GetMapping("/")
    fun method(httpServletResponse: HttpServletResponse) {
        httpServletResponse.setHeader("Location", "/home")
        httpServletResponse.status = 302
    }

    @GetMapping("/home")
    fun getHome(): String = "home"

    @GetMapping("/upload")
    fun uploadFile(): String = "upload"
}