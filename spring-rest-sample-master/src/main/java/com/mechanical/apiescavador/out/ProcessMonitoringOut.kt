package com.mechanical.apiescavador.out

import com.google.gson.annotations.SerializedName

data class ProcessMonitoringOut(
        @SerializedName("id") val id: Int,
        @SerializedName("monitor") val monitor: Monitor
)

data class Monitor(
        @SerializedName("origem") val origem: String,
        @SerializedName("tipo") val tipo: String,
        @SerializedName("valor") val valor: String
)