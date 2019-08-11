package com.mechanical.cassandraRepository.impl

import com.mechanical.cassandraRepository.model.LawOffice
import com.mechanical.cassandraRepository.repository.LawOfficeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LawOfficeImpl {

    @Autowired(required = true)
    lateinit var lawOfficeRepository: LawOfficeRepository

    fun save(lawOffice: LawOffice) {
        lawOfficeRepository.save(lawOffice)
    }

    fun searchLawOfficeByLatitudeAndLongitude() {

    }

    fun getLawOfficeByCpfOwner(cpfOwner: String): List<LawOffice> {
        return lawOfficeRepository.findByCpfOwner(cpfOwner)
    }

}