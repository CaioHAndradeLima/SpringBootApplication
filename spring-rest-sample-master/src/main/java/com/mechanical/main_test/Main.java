package com.mechanical.main_test;

import com.google.common.collect.Iterables;
import com.mechanical.solidity.CurrencyFirst;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.*;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {

    private static final String RECIPIENT = "0xB1938c4C397d71B29349CB421E66258c5b909f40";
    private final static BigInteger GAS_LIMIT = Contract.GAS_LIMIT;
    private final static BigInteger GAS_PRICE = Contract.GAS_PRICE;

    //private final static String CONTRACT_ADDRESS = "0x621C49eF7faA3Da32Ac98C129279eD03F3B0be48";

    public static void main(String[] args) {
        System.out.println("initializing system");
      //  testError();
        Web3j web3j = Web3j.build( new HttpService("http://127.0.0.1:8545") );
        //Web3j web3j = Web3j.build( new HttpService("https://ropsten.infura.io/v3/3ba12ae42ab4422f8479964526483f93") );
        try {

            //testAny(web3j);
            /*
            Credentials credentials = WalletUtils.loadCredentials(
                    "password",
                    "wallet/ṕath"
            );
*/

  //          testInsert();

 //           System.out.println( "version web3j is " + web3j.web3ClientVersion().send().getWeb3ClientVersion() );
            String contractAddress = "/Users/mac/Downloads/geth-darwin-amd64-1.8.22-7fa3509e/Users/mac/desktop/keystore/UTC--2019-02-17T13-53-42.356899000Z--6378bec633ae6c61691a5dfae0095129af62683e";
            //Credentials privateKeyCredentials = Credentials.create( "1",contractAddress );
            TransactionReceipt transferReceipt = Transfer.sendFunds(
                    web3j, WalletUtils.loadCredentials("",contractAddress),
                    "0xf900D5fEEf974F058543E47B27C801D396684266",  // you can put any address here
                    BigDecimal.valueOf(100), Convert.Unit.ETHER)  // 1 wei = 10^-18 Ether
                    .sendAsync().get();

            System.out.println("Transaction complete : "
                    + transferReceipt.getTransactionHash());


            String s = deployContract(web3j, WalletUtils.loadCredentials("",contractAddress));
            System.out.println("deployed " + s);

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
  //          CurrencyFirst instance = CurrencyFirst.load(CONTRACT_ADDRESS, web3j , privateKeyCredentials , GAS_PRICE, GAS_LIMIT  );
          //  RemoteCall<TransactionReceipt> result = instance.returnStruct("email@email", "password");
/*
            RemoteCall<TransactionReceipt> result = instance.saveStruct("email@email", "password");
            TransactionReceipt resultSaveStruct = result.send();
            System.out.println( "TRANSACTION HASH STRUCT EXAMPLE = " + resultSaveStruct.getTransactionHash() );
            System.out.println( "TRANSACTION HASH STRUCT EXAMPLE = " + resultSaveStruct.getTo() );
            System.out.println( "TRANSACTION HASH STRUCT EXAMPLE = " + resultSaveStruct.isStatusOK() );
*/
/*
            instance.saveStruct("email","senha").send();

            RemoteCall<Tuple2<String, String>> tuple2RemoteCall = instance.returnStruct();


            System.out.println( "value is " + tuple2RemoteCall.send().getValue1() );
*/


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

    private static void testAny(Web3j web3j) throws Exception {
        /**
         *  TEST DEPLOY AND CALL CONTRACT FUNCTION
         */
        // load private key into eckey to sign
        String privatekey = "8548484814548784";
        BigInteger privkey = new BigInteger(privatekey, 16);
        ECKeyPair ecKeyPair = ECKeyPair.create(privkey);
        Credentials credentials = Credentials.create(ecKeyPair);
        NoOpProcessor processor = new NoOpProcessor(web3j);

        //deploy new contract
        TransactionManager txManager = new FastRawTransactionManager(web3j, credentials, processor);

        RemoteCall<CurrencyFirst> request = CurrencyFirst.deploy(web3j, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
        CurrencyFirst token = request.send();
        String contractAddress = token.getDeployedAddress("3"); // 3 is ropsten testnet

        System.out.println( contractAddress );
        // load existing contract by address
        // ERC20 token = ERC20.load(contractAddress, web3j, txManager, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
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