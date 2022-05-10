package com.techsystem.endpoint

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.techsystem.Application
import com.techsystem.request.managerRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigInteger

@RestController
@RequestMapping("transaction")
class TransactionEndpoint {

    @GetMapping("/{hash}")
    fun getTransactionByHash(@PathVariable("hash") hash: String): ResponseEntity<*> = managerRequest<Any, Any>(null) {
        val value = Application.transactionSmartContract.returnStruct(hash).send().component2()

        Pair(
                ResponseEntity.status(HttpStatus.OK),
                value
        )
    }


    @PostMapping
    fun sendTransaction(@RequestBody jsonTransaction: String): ResponseEntity<*> = managerRequest<Any, Any>(null) {

        val transaction = Gson().fromJson(jsonTransaction, Transaction::class.java)
        val blockNumber = Application.transactionSmartContract.saveStruct(
                transaction.hash,
                transaction.value
        ).send().blockNumber

        Pair(
                ResponseEntity.status(HttpStatus.CREATED),
                TransactionBlockNumber(
                        blockNumber = blockNumber,
                        hash = transaction.hash
                )
        )
    }
}

data class Transaction(
        @SerializedName("hash") val hash: String,
        @SerializedName("value") val value: String
)

data class TransactionBlockNumber(
        @SerializedName("blockNumber") val blockNumber: BigInteger,
        @SerializedName("hash") val hash: String
)