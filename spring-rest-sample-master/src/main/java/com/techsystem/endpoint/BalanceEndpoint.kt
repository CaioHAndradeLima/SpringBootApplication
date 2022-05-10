package com.techsystem.endpoint

import com.google.gson.annotations.SerializedName
import com.techsystem.request.managerRequest
import com.techsystem.web3j.web3j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.utils.Convert
import java.math.BigDecimal

@RestController
@RequestMapping("balance")
class BalanceEndpoint {

    @GetMapping("/{publicKey}")
    fun getCurrentBalance(@PathVariable("publicKey") publicKey: String): ResponseEntity<*> {

        return managerRequest<Any, BalanceInfo>(null) {

            val valueBalance = web3j.ethGetBalance(publicKey, DefaultBlockParameterName.LATEST).send().balance.toString()

            val unit = Convert.Unit.FINNEY
            val currentValue = Convert.fromWei(
                    valueBalance,
                    unit
            )

            Pair(
                    ResponseEntity.status(HttpStatus.OK),
                    BalanceInfo(
                            value = currentValue,
                            publicKey = publicKey,
                            unit = Convert.Unit.FINNEY
                    )
            )
        }
    }
}

data class BalanceInfo(
        @SerializedName("value")
        val value: BigDecimal,
        @SerializedName("public_key")
        val publicKey: String,
        @SerializedName("unit")
        val unit: Convert.Unit
)