package com.techsystem.endpoint

import com.google.gson.annotations.SerializedName
import com.techsystem.Application
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("transaction")
class TransactionEndpoint {

    @GetMapping("/{hash}")
    fun getTransactionByHash(@PathVariable("hash") hash: String) : ResponseEntity<String> {

        val value = Application.transactionSmartContract.returnStruct(hash).send().component2()

        return ResponseEntity(value, HttpStatus.OK)
    }

    @PostMapping
    fun sendTransaction(@RequestBody transaction: Transaction): ResponseEntity<String> {
        val blockNumber = Application.transactionSmartContract.saveStruct(
                transaction.hash,
                transaction.value
        ).send().blockNumber

        return ResponseEntity("numbero do bloco no blockchain: $blockNumber", HttpStatus.CREATED)
    }

}

data class Transaction (
    @SerializedName("hash") val hash: String,
    @SerializedName("value") val value: String
)