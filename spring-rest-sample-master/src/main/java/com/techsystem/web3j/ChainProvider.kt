package com.techsystem.web3j

import java.math.BigInteger


private lateinit var chainId: BigInteger

fun provideChainId(): BigInteger {
    if(::chainId.isInitialized) {
        return chainId
    }

    chainId = web3j.ethChainId().send().chainId

    return chainId
}