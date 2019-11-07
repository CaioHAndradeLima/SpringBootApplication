package com.mechanical.model

import com.google.gson.annotations.SerializedName

data class ProcessNumberIn(
        @SerializedName("processNumber") val processNumber: String,
        @SerializedName("isNeededRegister") val isNeededRegister: Boolean
)