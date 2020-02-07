package com.mechanical

import com.mechanical.cassandraRepository.model.UserCassandraModel
import com.mechanical.cassandraRepository.model.Work
import com.mechanical.endpoint.RegisterProcessEndpoint.Companion.routine
import com.mechanical.infix_utils.toJson
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import java.util.*

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = ["com.mechanical.endpoint", "com.mechanical.security", "com.mechanical.endpoint.view"])
open class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
            routine()
        }

        fun showUser() {
            val listFiles = mutableSetOf<String>(UUID.randomUUID().toString())
            val listWork = mutableListOf(Work("44574551801", "b36c878b-41a9-441f-a001-ef4478c49733"))
            val user = UserCassandraModel(
                    "44574551801",
                    true,
                    "caiohandradelima@gmail.com",
                    "Caio Henrique Andrade Lima",
                    "11 968467467",
                    "additional",
                    "senha",
                    "2442",
                    listFiles,
                    -23.2424242425,
                    -46.353535,
                    "Rua imaculada",
                    "07183070",
                    "JdSao Manoel",
                    "29",
                    "SP",
                    listWork.toJson()

            )

            System.out.println(user.toJson())
        }
    }
}
