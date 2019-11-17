package com.mechanical.cassandraRepository.convert

import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.cassandraRepository.model.event.modelEvent.Movimentation

fun convertProcessEscavadorModelToMovimentations(processEscavador: ProcessEscavadorModel): Pair<String, MutableList<Movimentation>>? {
    val instancias = processEscavador.resposta?.instancias
    if (instancias.isNullOrEmpty()) {
        return null
    }

    val listMovimentation = mutableListOf<Movimentation>()

    for (instancia in instancias) {
        for (movimentacao in instancia.movimentacoes) {
            listMovimentation.add(
                    Movimentation(
                            movimentacao.conteudo,
                            movimentacao.data
                    )
            )
        }
    }

    return Pair(
            processEscavador.numero_processo,
            listMovimentation
    )
}

