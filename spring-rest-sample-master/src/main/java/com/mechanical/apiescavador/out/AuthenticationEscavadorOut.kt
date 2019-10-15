package com.mechanical.apiescavador.out

data class AuthenticationEscavadorOut(
        var access_token: String,
        var expires_in: Long,
        var refresh_token: String,
        var token_type: String
)