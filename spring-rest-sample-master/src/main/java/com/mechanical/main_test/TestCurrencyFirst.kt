package com.mechanical.main_test

import com.mechanical.solidity.CurrencyFirst
import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.tx.RawTransactionManager
import java.math.BigInteger

object TestCurrencyFirst {

    fun main(args : Array<String>) {

        val web3j = Web3j.build( HttpService() )

        val credentials = WalletUtils.loadCredentials(
                "password",
                "wallet/ṕath"
        )

        val contractAddress = "0x8420fc72430f77fbffcd6faebd4fd5567f021f87"
        val privateKeyCredentials = Credentials.create( contractAddress )

        val transactionManager = RawTransactionManager(
                web3j ,
                privateKeyCredentials
        )

        val instance = CurrencyFirst(contractAddress, web3j , privateKeyCredentials ,BigInteger.valueOf(3) , BigInteger.valueOf(10)  )
        instance.saveStruct( "email@email" , "password")
    }
}