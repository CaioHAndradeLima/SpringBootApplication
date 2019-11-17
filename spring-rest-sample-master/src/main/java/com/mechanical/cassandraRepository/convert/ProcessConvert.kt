package com.mechanical.cassandraRepository.convert

import com.mechanical.apiescavador.out.*
import com.mechanical.cassandraRepository.model.*

fun convertProcessEscavadorModelToProcessCassandraModel(numberProcess: String, processEscavadorModel: RespostaEscavadorModel): ProcessCassandraModel {
    return ProcessCassandraModel(
            numberProcess,
            System.currentTimeMillis(),
            convertInstanciaEscavadorToInstanciaModel(processEscavadorModel.instancias)
    )
}

fun convertInstanciaEscavadorToInstanciaModel(instancias: List<InstanciaEscavadorModel>): Array<InstanciasModel> {
    val list = MutableList(instancias.size) { index ->
        val it = instancias[index]
        InstanciasModel(
                it.instancia,
                it.segredo,
                it.assunto,
                it.classe,
                it.area,
                it.data_distribuicao,
                it.orgao_julgador,
                it.valor_causa,
                convertDadosEscavadorToDadosModel(it.dados),
                convertPartesEscavadorToPartesModel(it.partes)
        )
    }

    return list.toTypedArray()
}

fun convertPartesEscavadorToPartesModel(partes: List<PartesEscavadorModel>): MutableSet<PartesModel> {
    return MutableList(partes.size) {
        val parteEscavador = partes[it]

        val documentModel = if (parteEscavador.documento == null ||
                parteEscavador.documento!!.numero == null ||
                parteEscavador.documento!!.tipo == null) {
            null
        } else {
            DocumentoModel(
                    parteEscavador.documento!!.tipo.toString(),
                    parteEscavador.documento!!.numero.toString()
            )
        }

        return@MutableList PartesModel(
                parteEscavador.tipo,
                parteEscavador.nome,
                parteEscavador.principal,
                documentModel

                )
    }.toMutableSet()
}

fun convertDadosEscavadorToDadosModel(dados: List<DadosEscavadorModel>?): MutableSet<DadosModel>? {
    if (dados == null) return null

    return MutableList(dados.size) {
        val dado = dados[it]
        DadosModel(
                dado.tipo,
                dado.valor
        )
    }.toMutableSet()
}
