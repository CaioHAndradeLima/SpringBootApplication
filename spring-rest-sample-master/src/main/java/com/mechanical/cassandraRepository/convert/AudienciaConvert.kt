package com.mechanical.cassandraRepository.convert

import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.cassandraRepository.model.event.modelEvent.Audiencia

fun convertProcessEscavadorModelToAudiencias(processEscavador: ProcessEscavadorModel): Pair<String, MutableList<Audiencia>>? {

    val resposta = processEscavador.resposta

    if (resposta?.instancias.isNullOrEmpty()) {
        return null
    }

    val listAudiencia = mutableListOf<Audiencia>()

    for (instanciaEscavador in resposta!!.instancias) {
        for (audienciaEscavador in instanciaEscavador.audiencias) {
            val audiencia = Audiencia(
                    audienciaEscavador.audiencia,
                    audienciaEscavador.situacao,
                    audienciaEscavador.numero_pessoas,
                    instanciaEscavador.getJuiz(),
                    audienciaEscavador.data
            )
            listAudiencia.add(audiencia)
        }
    }

    return Pair(
            processEscavador.numero_processo,
            listAudiencia
    )
}