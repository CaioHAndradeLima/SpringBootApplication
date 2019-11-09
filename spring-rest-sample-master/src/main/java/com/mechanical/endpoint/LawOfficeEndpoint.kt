package com.mechanical.endpoint

import com.mechanical.provider.UserProvider
import com.mechanical.cassandraRepository.impl.LawOfficeImpl
import com.mechanical.cassandraRepository.model.LawOffice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
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


   /**
    * return the law offices of the user work now
    */
   @GetMapping("/jobs")
   fun getAllLawOfficeOfJobUser(): ResponseEntity<*> {
       return managerRequest<Collection<LawOffice>> {

           val collection = officeImpl.getAllLawOfficeByUser(
                   it.user
           )

           return@managerRequest Pair(ResponseEntity.status(HttpStatus.OK), collection)
       }
   }
}