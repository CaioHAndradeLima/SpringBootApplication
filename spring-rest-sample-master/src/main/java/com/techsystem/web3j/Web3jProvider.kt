package com.techsystem.web3j

import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService

internal val web3j = Web3j.build(HttpService("http://localhost:30303"))
