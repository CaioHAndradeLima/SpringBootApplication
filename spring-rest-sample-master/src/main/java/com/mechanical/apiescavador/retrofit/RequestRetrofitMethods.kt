package com.mechanical.apiescavador.retrofit

import com.mechanical.apiescavador.`in`.UserEscavador
import com.mechanical.apiescavador.out.AuthenticationEscavadorModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestRetrofitMethods {

    @POST("request-token")
    fun requestToken(@Body user: UserEscavador): Observable<Response<AuthenticationEscavadorModel>>
}


