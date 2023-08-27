package jakkins.automdorg.amdo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.runApplication

@SpringBootApplication
class AmdoApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("started")
    }
}

fun main(args: Array<String>) {
    runApplication<AmdoApplication>(*args)
}
