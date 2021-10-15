package com.techsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;


@SpringBootApplication
@ComponentScan(basePackages = "com.techsystem.endpoint")
public class Application {


    final static String PRIVATE_KEY = "6febbc5ca434ce999391834415a8d6892e785a494ed3118c2f42935f4aea9393";

    final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    final static BigInteger GAS_PRICE = BigInteger.valueOf(0);

    static String contractAddress = null;
    public static TransactionSmartContract transactionSmartContract;

    public static void main(String[] args) {

        try {

            if(args == null || args.length == 0) {
                System.out.println("servidor sem argumentos!");
            } else {
                if(args[0].equals("create")) {
                    DeployContract.main(args);
                    return;
                }

                Web3j web3j = Web3j.build(new HttpService("http://localhost:30303"));

                if (contractAddress == null) {

                    Credentials credentials = getCredentialsFromPrivateKey();


                    transactionSmartContract = new TransactionSmartContract(args[0], web3j, new RawTransactionManager(web3j, credentials, 4777L), new StaticGasProvider(GAS_PRICE, GAS_LIMIT));


                    System.out.println("transactionSmartContract " + transactionSmartContract.getContractAddress());
                }

                SpringApplication.run(Application.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static Credentials getCredentialsFromPrivateKey() {
        return Credentials.create(PRIVATE_KEY);
    }

}
