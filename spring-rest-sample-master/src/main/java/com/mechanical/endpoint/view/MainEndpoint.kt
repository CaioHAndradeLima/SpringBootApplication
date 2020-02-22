package com.mechanical.endpoint.view

import com.mechanical.cassandraRepository.impl.WorkerImpl
import com.mechanical.cassandraRepository.model.Work
import com.mechanical.endpoint.managerRequest
import com.mechanical.endpoint.view.model.SearchModel
import com.mechanical.provider.provideProfessions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/mainEndpoint")
class MainEndpoint {

    @Autowired
    lateinit var workerImpl: WorkerImpl

    companion object {
        @Bean
        fun getWorkerImpl() = WorkerImpl()
    }

    @PostMapping
    fun searchProfessions(@RequestBody searchModel: String): ResponseEntity<*> {
        return managerRequest<SearchModel, MutableList<Work>>(searchModel) { it, _ ->

            val listProfessionsFound = mutableListOf<Work>()

            for (profession in provideProfessions()) {

                if(profession.name.contains(it.name, ignoreCase = false)) {
                    listProfessionsFound.add(profession)
                }
            }

            return@managerRequest Pair(ResponseEntity.status(HttpStatus.OK), listProfessionsFound)
        }
    }

    @GetMapping
    fun get() = provideProfessions()
}