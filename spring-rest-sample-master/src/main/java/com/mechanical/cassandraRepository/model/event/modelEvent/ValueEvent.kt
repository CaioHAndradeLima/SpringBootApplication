package com.mechanical.cassandraRepository.model.event.modelEvent

import com.google.gson.annotations.SerializedName

sealed class ValueEvent

data class Audiencia(
        @SerializedName("audiencia") val audiencia: String,
        @SerializedName("situacao") val situacao: String,
        @SerializedName("numeroPessoas") val numeroPessoas: Int?,
        @SerializedName("juiz") val juiz: String?,
        @Transient val date: String
) : ValueEvent()

data class Movimentation(
        @SerializedName("description") val description: String,
        @Transient val date: String
) : ValueEvent()

