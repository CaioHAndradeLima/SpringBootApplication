package com.techsystem.transfer

import com.techsystem.endpoint.TransferResponse
import com.techsystem.web3j.*
import org.web3j.tx.Transfer
import org.web3j.utils.Convert
import java.math.BigDecimal

fun transfer(
        privateKeyWhoWillTransfer: String,
        publicKeyWhoWillReceiveTransfer: String,
        value: BigDecimal,
        unit: Convert.Unit
): TransferResponse {

    try {
        val transfer = Transfer(web3j, provideTransactionManager(provideCredentialsFromPrivateKey(privateKeyWhoWillTransfer)))

        val transactionReceipt = transfer.sendFunds(
                publicKeyWhoWillReceiveTransfer,
                value,
                unit,
                GAS_PRICE,
                GAS_LIMIT
        ).send()


        return TransferResponse(transactionReceipt.isStatusOK)
    } catch (e: Exception) {
        return TransferResponse(
                false,
                e.cause?.message.orEmpty()
        )
    }
}