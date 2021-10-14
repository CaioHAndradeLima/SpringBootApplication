pragma solidity >=0.4.22 <1.1.0;

contract TransactionSmartContract {

    mapping(string => Transaction) private _mapping;


    function saveStruct(string memory _idTransaction, string memory _valueTransaction) public {
        _mapping[ _idTransaction ] = Transaction({
            idTransaction: _idTransaction,
            valueTransaction: _valueTransaction

            });

    }

    function returnStruct(string memory _idTransaction) public view returns( string memory idTransaction, string memory valueTransaction ) {
        Transaction memory transaction =  _mapping[ _idTransaction ];
        return (transaction.idTransaction,transaction.valueTransaction);
    }

    struct Transaction {
        string idTransaction;
        string valueTransaction;
    }
}
