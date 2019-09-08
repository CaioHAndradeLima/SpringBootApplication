package com.mechanical.endpoint

import com.mechanical.cassandraRepository.impl.LawOfficeImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/lawOffice")
class LawOfficeEndpoint {

    @Autowired
    lateinit var officeImpl: LawOfficeImpl

    companion object {
        @Bean
        fun getLawOfficeImpl() = LawOfficeImpl()
    }


    @GetMapping
    fun getAllLawOffice() = ResponseEntity.ok(officeImpl.searchAllLawOffice())


}