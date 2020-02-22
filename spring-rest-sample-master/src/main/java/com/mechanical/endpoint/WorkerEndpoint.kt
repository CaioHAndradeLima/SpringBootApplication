package com.mechanical.endpoint

import com.mechanical.cassandraRepository.impl.WorkerImpl
import com.mechanical.cassandraRepository.model.WorkerCassandraModel
import com.mechanical.isDebug
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.IllegalStateException

@RestController
@RequestMapping("api/workerSession")
class WorkerEndpoint {

    @Autowired
    private lateinit var repository: WorkerImpl

    /*
        @RequestMapping(method = [RequestMethod.GET])
        fun get(@RequestBody workerSession : WorkerSession): ResponseEntity<*> {
            val userSaved = repository.insert(workerSession)

            return ResponseEntity(userSaved, HttpStatus.CREATED)
        }
    */

    @PostMapping
    fun anything(@RequestBody worker: WorkerCassandraModel) {
        if(!isDebug)
            throw IllegalStateException("error")

    }
}