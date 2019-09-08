package com.mechanical

import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.model.LawOffice
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.infix_utils.toJson
import com.mechanical.security.usingSecurity
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import java.util.*
import kotlin.collections.HashSet

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = ["com.mechanical.endpoint", "com.mechanical.security"])
open class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }

        fun showJson() {
            System.out.println(User(
                    UserCassandraModel(
                            "44574551801",
                            false,
                            "caiohandradelima@gmail.com",
                            "caio henrique",
                            "Solteiro",
                            "11 969467467",
                            "additional info",
                            "qualificacaox",
                            "senha do cara",
                            "2324",
                            HashSet(),
                            23.3452342,
                            46.234535,
                            "rua imaculada",
                            "07183-070",
                            "bairro",
                            "29",
                            "SP"
                    )
            ).toJson())
        }

    }
}
