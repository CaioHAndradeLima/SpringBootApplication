package com.techsystem.endpoint

import com.google.gson.annotations.SerializedName
import com.techsystem.request.managerRequest
import com.techsystem.transfer.transfer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.web3j.utils.Convert
import java.math.BigDecimal

@RestController
@RequestMapping("transfer")
class TransferEndpoint {

    @PostMapping
    fun makeTransfer(@RequestBody jsonTransferBetweenAccounts: String): ResponseEntity<*> {
        return managerRequest<TransferBetweenAccounts, TransferResponse>(jsonTransferBetweenAccounts) { transferBetweenAccounts ->

            val privateKeyWhoWillTransfer = transferBetweenAccounts!!.who
            val publicKeyWhoWillReceiveTransfer = transferBetweenAccounts.to

            Pair(
                    ResponseEntity.status(HttpStatus.OK),
                    transfer(privateKeyWhoWillTransfer, publicKeyWhoWillReceiveTransfer, transferBetweenAccounts.value, transferBetweenAccounts.unit)
            )
        }
    }
}

data class TransferBetweenAccounts(
        @SerializedName("who")
        val who: String,
        @SerializedName("to")
        val to: String,
        @SerializedName("value")
        val value: BigDecimal,
        @SerializedName("unit")
        val unit: Convert.Unit
)

data class TransferResponse(
        @SerializedName("success")
        val success: Boolean = false,
        @SerializedName("message")
        val message: String? = null
)