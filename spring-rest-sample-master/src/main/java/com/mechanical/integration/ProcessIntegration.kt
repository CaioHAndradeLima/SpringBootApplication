package com.mechanical.integration

import com.mechanical.apiescavador.out.ProcessEscavadorModel
import com.mechanical.apiescavador.request.ProcessEscavador

object ProcessIntegration {

    fun requestProcessByLink(linkApi: String): ProcessEscavadorModel? {
        return ProcessEscavador
                .requestProcessByLink(linkApi)
    }

}