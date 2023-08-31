package jakkins.automdorg.amdo.controllers

import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HomeController {

    @GetMapping("/")
    fun method(httpServletResponse: HttpServletResponse) {
        httpServletResponse.setHeader("Location", "/home_page")
        httpServletResponse.status = 302
    }

    @GetMapping("/home_page")
    fun getHome(): String = "home"

    @GetMapping("/upload_page")
    fun uploadFile(): String = "upload"
}