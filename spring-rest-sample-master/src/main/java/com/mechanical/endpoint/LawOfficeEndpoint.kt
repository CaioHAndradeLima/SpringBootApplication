package com.mechanical.endpoint

import com.mechanical.cassandraRepository.impl.LawOfficeImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("loginapi")
class LawOfficeEndpoint {

    @Autowired
    lateinit var officeImpl: LawOfficeImpl

    companion object {
        @Bean
        private fun getLawOfficeImple() = LawOfficeImpl()
    }

}