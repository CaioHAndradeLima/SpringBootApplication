package com.techsystem

import com.techsystem.endpoint.CreateWalletAccount
import com.techsystem.web3j.GAS_LIMIT
import com.techsystem.web3j.GAS_PRICE
import com.techsystem.web3j.provideChainId
import com.techsystem.web3j.provideMasterCredential
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.web3j.crypto.Keys
import org.web3j.crypto.Wallet
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.tx.RawTransactionManager
import org.web3j.tx.gas.StaticGasProvider

@SpringBootApplication
@ComponentScan(basePackages = ["com.techsystem.endpoint", "com.techsystem.security"])
@EnableAutoConfiguration
open class Application {

    companion object {
        internal lateinit var transactionSmartContract: TransactionSmartContract

        @JvmStatic
        fun main(args: Array<String>) {
            start(args)


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

/ *
        // Decrypt and open the wallet into a Credential object
        System.out.println("Account address: " + credentials.getAddress());
        System.out.println("Balance1: " + Convert.fromWei(web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Convert.Unit.ETHER));

        TransactionManager transactionManager = new RawTransactionManager(
                web3j,
                credentials,
                chainId
        );

/ *

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

/ *
        String fileName = WalletUtils.generateNewWalletFile(
                "secr3t",
                new File(".")); */
        }

        private fun start(args: Array<String>?) {
            //SpringApplication.run(Application.class);
            startServer(args)

        }

        private fun startServer(args: Array<String>?) {
            if (args.isNullOrEmpty()) {
                println("servidor sem argumentos!")
                return
            }

            val argument1 = args[0]
            try {
                if (argument1 == "create") {
                    DeployContract.main(args)
                    return
                }
                val web3j = Web3j.build(HttpService("http://localhost:30303"))
                val credentials = provideMasterCredential()
                val chainId = provideChainId()
                assert(4777L == chainId.toLong())
                transactionSmartContract = TransactionSmartContract(argument1, web3j, RawTransactionManager(web3j, credentials, chainId.toLong()),
                        StaticGasProvider(
                                GAS_PRICE, GAS_LIMIT
                        )
                )
                println("transactionSmartContract " + transactionSmartContract.contractAddress)

                SpringApplication.run(Application::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        private fun createWallet() {
            val ecKeyPair = Keys.createEcKeyPair()

            val wallet = Wallet.createStandard(
                    "KDSIJFI8SFUAHURHQWR78HYAQFU8HAU0AW8WQ37R5",
                    ecKeyPair

            )

            val privateKey = ecKeyPair.privateKey.toString(16)
            val address = "0x" + wallet.address

            println(
                    CreateWalletAccount(address, privateKey)
            )

        }
    }
}