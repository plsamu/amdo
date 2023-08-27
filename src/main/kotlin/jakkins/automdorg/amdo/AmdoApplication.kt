package jakkins.automdorg.amdo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AmdoApplication

fun main(args: Array<String>) {
	runApplication<AmdoApplication>(*args)
}
