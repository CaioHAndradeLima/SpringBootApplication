package com.mechanical.cassandraRepository.impl

import com.mechanical.cassandraRepository.model.LawOffice
import com.mechanical.cassandraRepository.repository.LawOfficeRepository
import com.mechanical.core.getCoordinates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LawOfficeImpl {

    @Autowired(required = true)
    lateinit var lawOfficeRepository: LawOfficeRepository

    fun save(lawOffice: LawOffice) {
        lawOfficeRepository.save(lawOffice)
    }

    fun searchLawOfficeByLatitudeAndLongitude(latitude: Double, longitude: Double) {
        val arrayCoordinates = getCoordinates(latitude, longitude, 0.5)

        if(arrayCoordinates[0] < 0 && arrayCoordinates[2] < 0) {

        } else if(arrayCoordinates[0] < 0 && arrayCoordinates[2] > 0) {

        } else if(arrayCoordinates[0] > 0 && arrayCoordinates[0] < 0) {

        } else if(arrayCoordinates[0] > 0 && arrayCoordinates[0] > 0) {

        }
        TODO()
    }

    fun searchAllLawOffice() = lawOfficeRepository.findAll()

    fun getLawOfficeByCpfOwner(cpfOwner: String): List<LawOffice> {
        return lawOfficeRepository.findByCpfOwner(cpfOwner)
    }

}