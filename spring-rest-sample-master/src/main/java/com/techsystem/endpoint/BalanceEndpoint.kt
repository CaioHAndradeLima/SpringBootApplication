package com.techsystem.endpoint

import com.techsystem.web3j.web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.utils.Convert

class BalanceEndpoint {

    fun getCurrentBalance() {

        val publicKey = ""
        val currentValue = Convert.fromWei(web3j.ethGetBalance(publicKey, DefaultBlockParameterName.LATEST).send().balance.toString(), Convert.Unit.FINNEY)

    }
}