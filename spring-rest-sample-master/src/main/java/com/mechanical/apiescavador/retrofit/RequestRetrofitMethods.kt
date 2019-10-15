package com.mechanical.apiescavador.retrofit

import com.mechanical.apiescavador.`in`.ProcessMonitoringIn
import com.mechanical.apiescavador.`in`.UserEscavador
import com.mechanical.apiescavador.out.AuthenticationEscavadorOut
import com.mechanical.apiescavador.out.ManagerProcessEscavadorOut
import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.apiescavador.out.ProcessMonitoringOut
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface RequestRetrofitMethods {

    @POST("request-token")
    fun requestToken(@Body user: UserEscavador): Observable<Response<AuthenticationEscavadorOut>>


    @POST("processo-tribunal/{number}/async")
    fun requestInfosToGetProcess(@Path("number") number: String): Observable<Response<ManagerProcessEscavadorOut>>

    @POST
    fun addMonitoring(@Body infoProcess: ProcessMonitoringIn) : Observable<Response<ProcessMonitoringOut>>
}


interface RequestRetrofitLink {

    @POST
    fun requestProcess(@Url url: String) : Observable<Response<ProcessEscavadorModel>>

}
