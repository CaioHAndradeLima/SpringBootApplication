package com.techsystem.web3j

import org.web3j.crypto.Credentials

internal const val PRIVATE_KEY_MASTER = "66f0539d60c6c9a6d3c8d6149db79ef825f50285c8b50b0ee674d62b271a2406"

internal fun provideMasterCredential() = provideCredentialsFromPrivateKey(PRIVATE_KEY_MASTER)

internal fun provideCredentialsFromPrivateKey(privateKey: String): Credentials {
    return Credentials.create(privateKey)
}
