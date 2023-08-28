package jakkins.automdorg.amdo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AmdoApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        println("http://locahost:8080")
    }
}

fun main(args: Array<String>) {
    runApplication<AmdoApplication>(*args)
}
