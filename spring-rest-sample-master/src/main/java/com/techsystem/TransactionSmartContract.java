package com.techsystem;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
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
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class TransactionSmartContract extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506106ca806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c80635c43d0c91461003b578063ed901f981461006c575b600080fd5b61005560048036038101906100509190610460565b610088565b604051610063929190610531565b60405180910390f35b61008660048036038101906100819190610568565b6101ef565b005b6060806000808460405161009c919061061c565b90815260200160405180910390206040518060400160405290816000820180546100c590610662565b80601f01602080910402602001604051908101604052809291908181526020018280546100f190610662565b801561013e5780601f106101135761010080835404028352916020019161013e565b820191906000526020600020905b81548152906001019060200180831161012157829003601f168201915b5050505050815260200160018201805461015790610662565b80601f016020809104026020016040519081016040528092919081815260200182805461018390610662565b80156101d05780601f106101a5576101008083540402835291602001916101d0565b820191906000526020600020905b8154815290600101906020018083116101b357829003601f168201915b5050505050815250509050806000015181602001519250925050915091565b604051806040016040528083815260200182815250600083604051610214919061061c565b9081526020016040518091039020600082015181600001908051906020019061023e929190610263565b50602082015181600101908051906020019061025b929190610263565b509050505050565b82805461026f90610662565b90600052602060002090601f01602090048101928261029157600085556102d8565b82601f106102aa57805160ff19168380011785556102d8565b828001600101855582156102d8579182015b828111156102d75782518255916020019190600101906102bc565b5b5090506102e591906102e9565b5090565b5b808211156103025760008160009055506001016102ea565b5090565b6000604051905090565b600080fd5b600080fd5b600080fd5b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b61036d82610324565b810181811067ffffffffffffffff8211171561038c5761038b610335565b5b80604052505050565b600061039f610306565b90506103ab8282610364565b919050565b600067ffffffffffffffff8211156103cb576103ca610335565b5b6103d482610324565b9050602081019050919050565b82818337600083830152505050565b60006104036103fe846103b0565b610395565b90508281526020810184848401111561041f5761041e61031f565b5b61042a8482856103e1565b509392505050565b600082601f8301126104475761044661031a565b5b81356104578482602086016103f0565b91505092915050565b60006020828403121561047657610475610310565b5b600082013567ffffffffffffffff81111561049457610493610315565b5b6104a084828501610432565b91505092915050565b600081519050919050565b600082825260208201905092915050565b60005b838110156104e35780820151818401526020810190506104c8565b838111156104f2576000848401525b50505050565b6000610503826104a9565b61050d81856104b4565b935061051d8185602086016104c5565b61052681610324565b840191505092915050565b6000604082019050818103600083015261054b81856104f8565b9050818103602083015261055f81846104f8565b90509392505050565b6000806040838503121561057f5761057e610310565b5b600083013567ffffffffffffffff81111561059d5761059c610315565b5b6105a985828601610432565b925050602083013567ffffffffffffffff8111156105ca576105c9610315565b5b6105d685828601610432565b9150509250929050565b600081905092915050565b60006105f6826104a9565b61060081856105e0565b93506106108185602086016104c5565b80840191505092915050565b600061062882846105eb565b915081905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b6000600282049050600182168061067a57607f821691505b6020821081141561068e5761068d610633565b5b5091905056fea2646970667358221220de283ea287f6ee24d8611ce267e46335ced1bcc936f182d813274a424ca5232d64736f6c63430008090033";

    public static final String FUNC_RETURNSTRUCT = "returnStruct";

    public static final String FUNC_SAVESTRUCT = "saveStruct";

    @Deprecated
    protected TransactionSmartContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TransactionSmartContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TransactionSmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TransactionSmartContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple2<String, String>> returnStruct(String _idTransaction) {
        final Function function = new Function(FUNC_RETURNSTRUCT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_idTransaction)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple2<String, String>>(function,
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

    public RemoteFunctionCall<TransactionReceipt> saveStruct(String _idTransaction, String _valueTransaction) {
        final Function function = new Function(
                FUNC_SAVESTRUCT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_idTransaction), 
                new org.web3j.abi.datatypes.Utf8String(_valueTransaction)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static TransactionSmartContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransactionSmartContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TransactionSmartContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TransactionSmartContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TransactionSmartContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TransactionSmartContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TransactionSmartContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TransactionSmartContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TransactionSmartContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransactionSmartContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransactionSmartContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransactionSmartContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TransactionSmartContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TransactionSmartContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TransactionSmartContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TransactionSmartContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
