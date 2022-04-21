package com.techsystem.web3j

import org.web3j.crypto.Credentials
import org.web3j.tx.RawTransactionManager


fun provideTransactionManager(credentials: Credentials): RawTransactionManager {
    return RawTransactionManager(
            web3j,
            credentials,
            provideChainId().toLong()
    )
}