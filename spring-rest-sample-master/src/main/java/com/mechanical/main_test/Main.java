package com.mechanical.main_test;

import com.google.common.collect.Iterables;
import com.mechanical.solidity.CurrencyFirst;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class Main {

    private static final String RECIPIENT = "0xB1938c4C397d71B29349CB421E66258c5b909f40";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(4545454);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(1000);

    private final static String CONTRACT_ADDRESS = "0x621C49eF7faA3Da32Ac98C129279eD03F3B0be48";

    public static void main(String[] args) {

      //  testError();
        Web3j web3j = Web3j.build( new HttpService("http://127.0.0.1:7545") );
      //  Web3j web3j = Web3j.build( new HttpService() );
        try {
            /*
            Credentials credentials = WalletUtils.loadCredentials(
                    "password",
                    "wallet/ṕath"
            );
*/

  //          testInsert();

 //           System.out.println( "version web3j is " + web3j.web3ClientVersion().send().getWeb3ClientVersion() );
            String contractAddress = "8ab2b57dd3e08c34f229fe8a66bf47e3a41e11e729f99beafabc6e6a9a110766";
            Credentials privateKeyCredentials = Credentials.create( contractAddress );
/*
        TransactionManager transactionManager = new RawTransactionManager(
                web3j ,
                privateKeyCredentials
        );

   //         deployContract( web3j , privateKeyCredentials );
/*
            Transfer transfer = new Transfer(web3j, transactionManager);

            TransactionReceipt transactionReceipt = transfer.sendFunds(
                    RECIPIENT,
                    BigDecimal.ONE,
                    Convert.Unit.ETHER,
                    GAS_PRICE,
                    GAS_LIMIT
            ).send();

            System.out.print("Transaction = " + transactionReceipt.getTransactionHash());
*/
            CurrencyFirst instance = CurrencyFirst.load(CONTRACT_ADDRESS, web3j , privateKeyCredentials , GAS_PRICE, GAS_LIMIT  );
          //  RemoteCall<TransactionReceipt> result = instance.returnStruct("email@email", "password");
/*
            RemoteCall<TransactionReceipt> result = instance.saveStruct("email@email", "password");
            TransactionReceipt resultSaveStruct = result.send();
            System.out.println( "TRANSACTION HASH STRUCT EXAMPLE = " + resultSaveStruct.getTransactionHash() );
            System.out.println( "TRANSACTION HASH STRUCT EXAMPLE = " + resultSaveStruct.getTo() );
            System.out.println( "TRANSACTION HASH STRUCT EXAMPLE = " + resultSaveStruct.isStatusOK() );
*/

            instance.saveStruct("email","senha").send();

            RemoteCall<Tuple2<String, String>> tuple2RemoteCall = instance.returnStruct();


            System.out.println( "value is " + tuple2RemoteCall.send().getValue1() );



       } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String deployContract(Web3j web3j, Credentials credentials) throws Exception {
        return CurrencyFirst
                .deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT)
                .send()
                .getContractAddress();
    }


    private static void testError() {

        Web3j web3j = Web3j.build( new HttpService() );

        try {
            /*
            Credentials credentials = WalletUtils.loadCredentials(
                    "password",
                    "wallet/ṕath"
            );
*/
            String contractAddress = "";
            Credentials privateKeyCredentials = Credentials.create( contractAddress );

            TransactionManager transactionManager = new RawTransactionManager(
                    web3j ,
                    privateKeyCredentials
            );

            CurrencyFirst instance = new CurrencyFirst(contractAddress, web3j , privateKeyCredentials , BigInteger.valueOf(3) , BigInteger.valueOf(10)  );
            RemoteCall<TransactionReceipt> result = instance.saveStruct("email@email", "password");

            result.send();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testInsert() throws Exception {
        // create new account
        BigInteger gasPrice = BigInteger.valueOf( 1400 );

        Web3j web3 = Web3j.build( new HttpService("http://127.0.0.1:8545") );

        Admin admin = Admin.build(new HttpService("http://127.0.0.1:8545"));
        NewAccountIdentifier newAccount = admin.personalNewAccount("PASSWORD").send();

        // get current created account
        EthAccounts accounts = web3.ethAccounts().send();
        String lastAccount = accounts.getAccounts().get(2);
// get creadentials for the first account having some ether
        String firstAccount = web3.ethAccounts().send().getAccounts().get(1);
        Credentials credentials = Credentials.create( lastAccount );
    //    Credentials credentials = Credentials.create()
// get current balance for first account
        EthGetBalance balance = admin.ethGetBalance(firstAccount, DefaultBlockParameterName.LATEST).send();
        BigDecimal balanceVaue = Convert.fromWei(balance.getBalance().toString(), Convert.Unit.WEI);

// create transaction to give the new created account some ether from the first one
// log some stuff
        System.out.println("Account: " + firstAccount);
        System.out.println("Account balance: " + balanceVaue);

        System.out.println("Gas Price admin.ethGasPrice() in Ether: " + Convert.fromWei(gasPrice.toString(), Convert.Unit.ETHER));
        System.out.println("Transfer Gas Limit in Ether: " + Convert.fromWei(Transfer.GAS_LIMIT.toString(), Convert.Unit.ETHER));
        System.out.println("Transfer Gas Price in Ether: " + Convert.fromWei(Transfer.GAS_PRICE.toString(), Convert.Unit.ETHER));

        TransactionReceipt transactionReceipt = Transfer.sendFunds(web3, credentials, firstAccount, BigDecimal.valueOf(200000), Convert.Unit.ETHER).send();
        String transactionHash = transactionReceipt.getTransactionHash();
   }

}