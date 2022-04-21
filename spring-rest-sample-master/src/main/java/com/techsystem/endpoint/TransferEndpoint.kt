package com.techsystem.endpoint

import com.techsystem.request.managerRequest
import com.techsystem.transfer.transfer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("transfer")
class TransferEndpoint {

    @PostMapping
    fun makeTransfer(@RequestBody jsonTransferBetweenAccounts: String): ResponseEntity<*> {
        return managerRequest<TransferBetweenAccounts, TransferResponse>(jsonTransferBetweenAccounts) { transferBetweenAccounts ->

            val privateKeyWhoWillTransfer = transferBetweenAccounts!!.who.account //TODO PROCURAR POR CHAVE PRIVADA EM VEZ DE PUBLICA
            val publicKeyWhoWillReceiveTransfer = transferBetweenAccounts.to.account

            Pair(
                    ResponseEntity.status(HttpStatus.OK),
                    transfer(privateKeyWhoWillTransfer, publicKeyWhoWillReceiveTransfer, transferBetweenAccounts.value)
            )
        }

    }
}

data class TransferBetweenAccounts(
        val who: CreateWalletAccount,
        val to: CreateWalletAccount,
        val value: BigDecimal // 1000 = 1 Ether
)

data class TransferResponse(
        val success: Boolean = false,
        val message: String? = null
)