pragma solidity >=0.4.22 <0.6.0;

contract TransactionSmartContract {

    mapping(string => Transaction) private _mapping;


    function saveStruct(string memory _idTransaction, string memory _valueTransaction) public {
        Transaction memory Transaction = Transaction({
            idTransaction: _idTransaction,
            valueTransaction: _valueTransaction

        });

        _mapping[ _idTransaction ] = Transaction;

    }

    function returnStruct() public view returns( string memory idTransaction,string memory valueTransaction ) {
        Transaction memory Transaction =  _mapping[ idTransaction ];
        return (Transaction.idTransaction,Transaction.valueTransaction);
    }

    struct Transaction {
        string idTransaction;
        string valueTransaction;
    }
}
