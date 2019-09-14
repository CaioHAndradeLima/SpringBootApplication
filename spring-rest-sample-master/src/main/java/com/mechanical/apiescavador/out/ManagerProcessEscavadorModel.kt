package com.mechanical.apiescavador.out

import com.google.gson.annotations.SerializedName
import com.mechanical.cassandraRepository.model.StatusManagerProcess


data class ManagerProcessEscavadorModel(
        @SerializedName("id")              val id: Int,
        @SerializedName("numero_processo") val processNumber: String,
        @SerializedName("status")          val status: StatusManagerProcess?,
        @SerializedName("enviar_callback") val sendCallback: String,
        @SerializedName("status_callback") val statusCallback: String?,
        @SerializedName("link_api")        val linkApi: String
)