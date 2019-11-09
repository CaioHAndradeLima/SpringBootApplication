package com.mechanical.endpoint.view

import com.mechanical.cassandraRepository.impl.EventCassandraImpl
import com.mechanical.cassandraRepository.impl.LawOfficeImpl
import com.mechanical.endpoint.managerRequest
import com.mechanical.endpoint.view.model.PagePrincipalOut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/mainEndpoint")
class MainEndpoint {

    @Autowired
    lateinit var eventImpl: EventCassandraImpl

    @Autowired
    lateinit var lawOfficeImpl: LawOfficeImpl

    companion object {
        @Bean
        fun getEventCassandraImpl() = EventCassandraImpl()
    }

    @GetMapping
    fun getPrincipalPage(): ResponseEntity<*> {
        return managerRequest {
            val user = it.user

            lawOfficeImpl.getAllLawOfficeByUser(
                    user
            )

            val listEvents = eventImpl.searchEvents(
                    user.cpf
            )

            val pagePrincipalOut = if (listEvents.isNullOrEmpty()) {
                PagePrincipalOut(isNeededShowAddProcess = true)
            } else {
                PagePrincipalOut(isPossibleShowListEvents = true, listEvents = listEvents)
            }


            return@managerRequest Pair(ResponseEntity.status(HttpStatus.OK), pagePrincipalOut)
        }

    }
}