package com.mechanical.model

import com.google.gson.annotations.SerializedName
import com.mechanical.apiescavador.`in`.FREQUENCY

data class ProcessNumberIn(
        @SerializedName("processNumber") val processNumber: String,
        @SerializedName("isNeededRegister") val isNeededRegister: Boolean,
        @SerializedName("frequency") val frequency: FREQUENCY? = null
)