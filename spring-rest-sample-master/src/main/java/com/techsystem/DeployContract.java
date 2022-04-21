package com.techsystem;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import static com.techsystem.web3j.CredentialsProviderKt.provideMasterCredential;

public class DeployContract {

    public static void main(String[] args) throws Exception {
        /*
        Web3j web3j = Web3j.build(new HttpService("http://localhost:30303"));
        long chainId = web3j.ethChainId().send().getChainId().longValue();

        Credentials credentials = provideMasterCredential();

        TransactionSmartContract send = TransactionSmartContract.deploy(
                web3j,
                new RawTransactionManager(web3j, credentials, chainId),
                new StaticGasProvider(GAS_PRICE, GAS_LIMIT)
        ).send();

        System.out.println("hashContract: " + send.getContractAddress());

        web3j.shutdown();
        */
    }
}
