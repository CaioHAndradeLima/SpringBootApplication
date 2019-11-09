package com.mechanical.endpoint.view.model

import com.google.gson.annotations.SerializedName

data class RegisterProcessOut(
        @SerializedName("errorExternalApi") var errorExternalApi: Boolean = false,
        @SerializedName("wasFoundThisProcess") var wasFoundThisProcess: Boolean = false,
        @SerializedName("wasRegisteredToSeeMovementOfThisProcess") var wasRegisteredToSeeMovementOfThisProcess: Boolean = false
)