package com.mechanical.apiescavador.`in`

import com.google.gson.annotations.SerializedName

//se more https://api.escavador.com/docs/#registrar-novo-monitoramento-1
data class ProcessMonitoringIn(
        @SerializedName("valor") var valor: String = "valor",
        @SerializedName("tipo") private val tipo: String = "UNICO",
        @SerializedName("frequencia") private val frequencia: String = "SEMANAL"
)