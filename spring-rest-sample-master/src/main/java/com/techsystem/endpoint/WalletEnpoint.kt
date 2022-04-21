package com.techsystem.endpoint

import com.google.gson.annotations.SerializedName
import com.techsystem.request.managerRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.web3j.crypto.Keys
import org.web3j.crypto.Wallet


@RestController
@RequestMapping("wallet")
class WalletEnpoint {

    @PostMapping
    fun createWallet(@RequestBody jsonCreateWallet: String): ResponseEntity<*> {


        return managerRequest<CreateWallet, CreateWalletAccount>(jsonCreateWallet) { createWallet ->

            val ecKeyPair = Keys.createEcKeyPair()

            val wallet = Wallet.createStandard(
                    createWallet!!.password,
                    ecKeyPair

            )

            val privateKey = ecKeyPair.privateKey.toString(16)
            val address = "0x" + wallet.address

            Pair(
                    ResponseEntity.status(HttpStatus.OK),
                    CreateWalletAccount(address)
            )
        }
    }
}

data class CreateWallet(@SerializedName("password") val password: String)
data class CreateWalletAccount(
        @SerializedName("account") val account: String
)