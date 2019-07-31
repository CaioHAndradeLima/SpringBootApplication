import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = ["com.mechanical.endpoint", "com.mechanical.topic", "/"])
open class ApplicationTest {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ApplicationTest::class.java, *args)
        }
    }
}