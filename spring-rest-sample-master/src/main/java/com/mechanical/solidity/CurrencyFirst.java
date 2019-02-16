package com.mechanical.solidity;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import com.google.gson.Gson;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class CurrencyFirst extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5061050f806100206000396000f3fe608060405234801561001057600080fd5b5060043610610052577c01000000000000000000000000000000000000000000000000000000006000350463c22fd45e8114610057578063ed901f981461013d575b600080fd5b61005f61026c565b604051808060200180602001838103835285818151815260200191508051906020019080838360005b838110156100a0578181015183820152602001610088565b50505050905090810190601f1680156100cd5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b838110156101005781810151838201526020016100e8565b50505050905090810190601f16801561012d5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b61026a6004803603604081101561015357600080fd5b81019060208101813564010000000081111561016e57600080fd5b82018360208201111561018057600080fd5b803590602001918460018302840111640100000000831117156101a257600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092959493602081019350359150506401000000008111156101f557600080fd5b82018360208201111561020757600080fd5b8035906020019184600183028401116401000000008311171561022957600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506103d1945050505050565b005b606080610277610431565b33600090815260208181526040918290208251815460026001821615610100026000190190911604601f81018490049093028101606090810185529381018381529093919284928491908401828280156103125780601f106102e757610100808354040283529160200191610312565b820191906000526020600020905b8154815290600101906020018083116102f557829003601f168201915b50505050508152602001600182018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103b45780601f10610389576101008083540402835291602001916103b4565b820191906000526020600020905b81548152906001019060200180831161039757829003601f168201915b505050919092525050815160209092015191945090925050509091565b6103d9610431565b5060408051808201825283815260208082018490523360009081528082529290922081518051929384936104109284920190610448565b5060208281015180516104299260018501920190610448565b505050505050565b604080518082019091526060808252602082015290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061048957805160ff19168380011785556104b6565b828001600101855582156104b6579182015b828111156104b657825182559160200191906001019061049b565b506104c29291506104c6565b5090565b6104e091905b808211156104c257600081556001016104cc565b9056fea165627a7a72305820b1e6a4666e72777dde81eb2cb0f63c74b097e60da052ca68b81b4cbca8548472002";

    public static final String FUNC_RETURNSTRUCT = "returnStruct";

    public static final String FUNC_SAVESTRUCT = "saveStruct";

    @Deprecated
    public CurrencyFirst(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public CurrencyFirst(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CurrencyFirst(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CurrencyFirst(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CurrencyFirst> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall( CurrencyFirst.class, web3j , credentials , gasPrice , gasLimit , BINARY , "" );
    }

    public RemoteCall<Tuple2<String, String>> returnStruct() {
        final Function function = new Function(FUNC_RETURNSTRUCT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple2<String, String>>(
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                       return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> saveStruct(String _email, String _password) {

        List list = Collections.emptyList();

        final Function function = new Function(
                FUNC_SAVESTRUCT, 
                Arrays.asList(
                        new org.web3j.abi.datatypes.Utf8String(_email),
                        new org.web3j.abi.datatypes.Utf8String(_password)),
                        list);
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CurrencyFirst load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CurrencyFirst(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CurrencyFirst load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CurrencyFirst(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CurrencyFirst load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CurrencyFirst(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CurrencyFirst load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CurrencyFirst(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
