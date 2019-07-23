package com.mechanical

import com.mechanical.cassandraRepository.User
import com.mechanical.cassandraRepository.model.AddressUserCassandraModel
import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.endpoint.LoginEntity
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import com.mechanical.infix_utils.toJson
import java.util.HashSet
import java.util.UUID

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = ["com.mechanical.endpoint", "com.mechanical.topic"])
open class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }

        private fun showJsonLogin() {
            System.out.println(LoginEntity(
                    "caiohandradelima@gmail.com",
                    "senha do cara",
                    "2342",
                    "242424242242424"
            ).toJson())
        }
    }



    fun showJson() {
        System.out.println(User(
                UserCassandraModel(
                        "44574551801",
                        "caiohandradelima@gmail.com",
                        UUID.randomUUID(),
                        "caio henrique",
                        "Solteiro",
                        "11 969467467",
                        "additional info",
                        "qualificacaox",
                        "senha do cara",
                        true,
                        HashSet()
                ),
                AddressUserCassandraModel(
                        UUID.randomUUID(),
                        23.5235235,
                        46.355353,
                        "rua imaculada",
                        "07183070",
                        "bairro",
                        "29",
                        "SP"
                )
        ).toJson())
    }
}
