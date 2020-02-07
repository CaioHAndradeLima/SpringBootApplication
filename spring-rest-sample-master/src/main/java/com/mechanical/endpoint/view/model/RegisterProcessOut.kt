package com.mechanical.endpoint.view.model

import com.google.gson.annotations.SerializedName

data class RegisterProcessOut(
        @SerializedName("wasFoundThisProcess") var wasFoundThisProcess: Boolean = false
)