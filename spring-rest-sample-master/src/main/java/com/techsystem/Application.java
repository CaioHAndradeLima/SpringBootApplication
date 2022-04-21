package com.techsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

import static com.techsystem.web3j.CredentialsProviderKt.provideMasterCredential;


@SpringBootApplication
@ComponentScan(basePackages = "com.techsystem.endpoint")
public class Application {


    static String contractAddress = null;
    public static TransactionSmartContract transactionSmartContract;

    public static void main(String[] args) throws Exception {
        start(args);


//        Credentials credentials1 = provideMasterCredential();
/*

        String password = "secr3t";
        ECKeyPair keyPair = Keys.createEcKeyPair();
        WalletFile wallet = Wallet.createStandard(password, keyPair);

        System.out.println("Priate key: " + keyPair.getPrivateKey().toString(16));
        System.out.println("Account: 0x" + wallet.getAddress());


//        transactionSmartContract = new TransactionSmartContract(args[1], web3j, new RawTransactionManager(web3j, credentials, 4777L), new StaticGasProvider(GAS_PRICE, GAS_LIMIT));

  //      WalletUtils.


 //       long chainId = web3j.ethChainId().send().getChainId().longValue();


        Credentials credentials = WalletUtils.loadCredentials(
                "",
                "/Users/mac/Desktop/projeto_ethereum/data_directory/keystore/UTC--2021-10-19T14-14-42.277725000Z--8e899f516c16068f23fab21a6aacafe42fd67bd9"
        );

/*
        // Decrypt and open the wallet into a Credential object
        System.out.println("Account address: " + credentials.getAddress());
        System.out.println("Balance1: " + Convert.fromWei(web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER));

        TransactionManager transactionManager = new RawTransactionManager(
                web3j,
                credentials,
                chainId
        );

/*

        Credentials credentials1 = WalletUtils.loadCredentials(
                "secr3t",
                "UTC--2021-10-18T20-35-46.598000000Z--e892a02a85d473e26bf630be3de96a7d0c671931.json"
        );


        Transfer transfer = new Transfer(web3j, transactionManager);
        TransactionReceipt transactionReceipt = transfer.sendFunds(
                credentials1.getAddress(),
                BigDecimal.valueOf(1000 * 10),
                Convert.Unit.FINNEY,
                GAS_PRICE,
                GAS_LIMIT
        ).send();
        System.out.println("Balance: " + Convert.fromWei(web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER));


        System.out.print("Transaction = " + transactionReceipt);
        System.out.println("Balance THEIR: " + Convert.fromWei(web3j.ethGetBalance(credentials1.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER));
        //System.out.println("Balance4: " + Convert.fromWei(web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER));

/*
        String fileName = WalletUtils.generateNewWalletFile(
                "secr3t",
                new File(".")); */

    }

    private static void start(String[] args) {
        //SpringApplication.run(Application.class);

        if (args == null || args.length == 0) {
            System.out.println("servidor sem argumentos!");
            return;
        }

        String argument1 = args[0];

        try {
            if (argument1.equals("create")) {
                DeployContract.main(args);
                return;
            }

            Web3j web3j = Web3j.build(new HttpService("http://localhost:30303"));

            if (contractAddress == null) {

                Credentials credentials = provideMasterCredential();
                transactionSmartContract = new TransactionSmartContract(argument1, web3j, new RawTransactionManager(web3j, credentials, 4777L),
                        new StaticGasProvider(
                                BigInteger.valueOf(100000), BigInteger.valueOf(1500000)
                        )
                );


                System.out.println("transactionSmartContract " + transactionSmartContract.getContractAddress());
            }

            SpringApplication.run(Application.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
