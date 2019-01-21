pragma solidity >=0.4.22 <0.6.0;

contract CurrencyFirst {

    mapping(address => First) _mapping;


    function saveStruct(string memory _email, string memory _password) public {
        First memory first = First({
            email: _email,
            password: _password

        });

        _mapping[ msg.sender ] = first;

    }

    function returnStruct() public view returns( string memory email,string memory password ) {
        First memory first =  _mapping[ msg.sender ];
        return (first.email,first.password);
    }

    struct First {
        string email;
        string password;
    }
}
