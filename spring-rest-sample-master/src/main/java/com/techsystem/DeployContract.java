package com.techsystem;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import static com.techsystem.Application.*;

public class DeployContract {

    public static void main(String[] args) throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:30303"));

        Credentials credentials = getCredentialsFromPrivateKey();


        TransactionSmartContract send = TransactionSmartContract.deploy(
                web3j,
                new RawTransactionManager(web3j, credentials, 4777L),
                new StaticGasProvider(GAS_PRICE, GAS_LIMIT)
        ).send();

        System.out.println("hashContract:" + send.getContractAddress());
    }
}
