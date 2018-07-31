package kr.co.keypair.votingsystem;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Betting extends Contract {
    private static final String BINARY = "608060405260068054600160a060020a0319167375b8eccb3993bc5cb0501dd5b0522e95614bfded17905534801561003657600080fd5b5061179a806100466000396000f30060806040526004361061013d5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166301f247018114610142578063095ea7b3146101ba57806312065fe0146101f25780631398a64c1461021957806318160ddd1461023757806323b872dd1461024c57806324d10705146102765780632afb65b31461028b57806338cc4831146102a957806366188463146102da57806370a08231146102fe578063738b2ec61461031f5780637c70a9da14610334578063a37f5de714610358578063a7402bbf14610376578063a9059cbb14610458578063bab5b49d1461047c578063c0bfe162146104b2578063ce56b3e414610508578063d73dd62314610519578063da0ff1e11461053d578063dd62ed3e14610557578063ecfd0a561461057e578063fa5c717e14610593575b600080fd5b34801561014e57600080fd5b5061016363ffffffff600435166024356105d5565b6040518085600160a060020a0316600160a060020a031681526020018481526020018363ffffffff1663ffffffff1681526020018260028111156101a357fe5b60ff16815260200194505050505060405180910390f35b3480156101c657600080fd5b506101de600160a060020a0360043516602435610633565b604080519115158252519081900360200190f35b3480156101fe57600080fd5b50610207610699565b60408051918252519081900360200190f35b34801561022557600080fd5b5061020763ffffffff6004351661069f565b34801561024357600080fd5b5061020761074d565b34801561025857600080fd5b506101de600160a060020a0360043581169060243516604435610753565b34801561028257600080fd5b506102076108ca565b34801561029757600080fd5b5061020763ffffffff600435166108dd565b3480156102b557600080fd5b506102be61092f565b60408051600160a060020a039092168252519081900360200190f35b3480156102e657600080fd5b506101de600160a060020a0360043516602435610933565b34801561030a57600080fd5b50610207600160a060020a0360043516610a23565b34801561032b57600080fd5b50610207610a3e565b34801561034057600080fd5b50610163600160a060020a0360043516602435610a8a565b34801561036457600080fd5b5061020763ffffffff60043516610aa5565b34801561038257600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261045695833563ffffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610abd9650505050505050565b005b34801561046457600080fd5b506101de600160a060020a0360043516602435610cc3565b34801561048857600080fd5b5061045663ffffffff60043581169060243581169060443581169060643581169060843516610da4565b3480156104be57600080fd5b506104ca600435610fbb565b604051808463ffffffff1663ffffffff1681526020018381526020018260028111156104f257fe5b60ff168152602001935050505060405180910390f35b61045663ffffffff6004351661106f565b34801561052557600080fd5b506101de600160a060020a036004351660243561132f565b61045663ffffffff6004351660243560ff604435166113c8565b34801561056357600080fd5b50610207600160a060020a03600435811690602435166115e6565b34801561058a57600080fd5b506102be611611565b34801561059f57600080fd5b506105b163ffffffff60043516611620565b604051808260028111156105c157fe5b60ff16815260200191505060405180910390f35b6004602052816000526040600020818154811015156105f057fe5b6000918252602090912060039091020180546001820154600290920154600160a060020a03909116935090915063ffffffff811690640100000000900460ff1684565b336000818152600260209081526040808320600160a060020a038716808552908352818420869055815186815291519394909390927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925928290030190a350600192915050565b33315b90565b600080805b33600090815260056020526040902054811015610746573360009081526005602052604090208054829081106106d657fe5b600091825260209091206002600390920201015463ffffffff8581169116141561073e57336000908152600560205260409020805461073b91908390811061071a57fe5b9060005260206000209060030201600101548361166c90919063ffffffff16565b91505b6001016106a4565b5092915050565b60015490565b6000600160a060020a038316151561076a57600080fd5b600160a060020a03841660009081526020819052604090205482111561078f57600080fd5b600160a060020a03841660009081526002602090815260408083203384529091529020548211156107bf57600080fd5b600160a060020a0384166000908152602081905260409020546107e8908363ffffffff61168216565b600160a060020a03808616600090815260208190526040808220939093559085168152205461081d908363ffffffff61166c16565b600160a060020a0380851660009081526020818152604080832094909455918716815260028252828120338252909152205461085f908363ffffffff61168216565b600160a060020a03808616600081815260026020908152604080832033845282529182902094909455805186815290519287169391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a35060019392505050565b3360009081526005602052604090205490565b600080805b63ffffffff84166000908152600460205260409020548110156107465763ffffffff84166000908152600460205260409020805461092591908390811061071a57fe5b91506001016108e2565b3390565b336000908152600260209081526040808320600160a060020a03861684529091528120548083111561098857336000908152600260209081526040808320600160a060020a03881684529091528120556109bd565b610998818463ffffffff61168216565b336000908152600260209081526040808320600160a060020a03891684529091529020555b336000818152600260209081526040808320600160a060020a0389168085529083529281902054815190815290519293927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925929181900390910190a35060019392505050565b600160a060020a031660009081526020819052604090205490565b600080805b33600090815260056020526040902054811015610a84573360009081526005602052604090208054610a7a91908390811061071a57fe5b9150600101610a43565b50919050565b6005602052816000526040600020818154811015156105f057fe5b63ffffffff1660009081526004602052604090205490565b600654600160a060020a03163314610ad457600080fd5b6040805161012081018252848152602080820185905291810183905263ffffffff8616606082015260006080820181905260a0820181905260c0820181905260e08201819052610100820181905260038054600181018083559190925282518051919460049093027fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0192610b6e928492909101906116d6565b506020828101518051610b8792600185019201906116d6565b5060408201518051610ba39160028401916020909101906116d6565b506060820151600382018054608085015160a086015160c087015160e088015163ffffffff9081167001000000000000000000000000000000000273ffffffff00000000000000000000000000000000199282166c01000000000000000000000000026fffffffff0000000000000000000000001994831668010000000000000000026bffffffff0000000000000000199684166401000000000267ffffffff000000001994909a1663ffffffff19909816979097179290921697909717939093169390931716171691909117808255610100840151919074ff0000000000000000000000000000000000000000191674010000000000000000000000000000000000000000836002811115610cb557fe5b021790555050505050505050565b6000600160a060020a0383161515610cda57600080fd5b33600090815260208190526040902054821115610cf657600080fd5b33600090815260208190526040902054610d16908363ffffffff61168216565b3360009081526020819052604080822092909255600160a060020a03851681522054610d48908363ffffffff61166c16565b600160a060020a038416600081815260208181526040918290209390935580518581529051919233927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a350600192915050565b600654600160a060020a03163314610dbb57600080fd5b8360038663ffffffff16815481101515610dd157fe5b600091825260209091206004909102016003908101805467ffffffff00000000191664010000000063ffffffff94851602179055805485928816908110610e1457fe5b6000918252602090912060049091020160030180546bffffffff000000000000000019166801000000000000000063ffffffff938416021790558381169085161115610eca57600160038663ffffffff16815481101515610e7157fe5b60009182526020909120600360049092020101805474ff0000000000000000000000000000000000000000191674010000000000000000000000000000000000000000836002811115610ec057fe5b0217905550610fb4565b8263ffffffff168463ffffffff161015610ef557600260038663ffffffff16815481101515610e7157fe5b8263ffffffff168463ffffffff161415610fb4578063ffffffff168263ffffffff161115610f3457600160038663ffffffff16815481101515610e7157fe5b8063ffffffff168263ffffffff161015610fb457600260038663ffffffff16815481101515610f5f57fe5b60009182526020909120600360049092020101805474ff0000000000000000000000000000000000000000191674010000000000000000000000000000000000000000836002811115610fae57fe5b02179055505b5050505050565b33600090815260056020526040812080548291829185908110610fda57fe5b600091825260208083206002600390930201919091015433835260059091526040909120805463ffffffff909216918690811061101357fe5b60009182526020808320600160039093020191909101543383526005909152604090912080548790811061104357fe5b906000526020600020906003020160020160049054906101000a900460ff169250925092509193909250565b6006546000908190819081908190600160a060020a0316331461109157600080fd5b61109a866108dd565b94506000935060009250600091505b63ffffffff86166000908152600460205260409020548210156111aa576003805463ffffffff88169081106110da57fe5b906000526020600020906004020160030160149054906101000a900460ff16600281111561110457fe5b63ffffffff8716600090815260046020526040902080548490811061112557fe5b906000526020600020906003020160020160049054906101000a900460ff16600281111561114f57fe5b141561119f5763ffffffff86166000908152600460205260409020805461119c91908490811061117b57fe5b9060005260206000209060030201600101548561166c90919063ffffffff16565b93505b6001909101906110a9565b5060005b63ffffffff8616600090815260046020526040902054811015611327576003805463ffffffff88169081106111df57fe5b906000526020600020906004020160030160149054906101000a900460ff16600281111561120957fe5b63ffffffff8716600090815260046020526040902080548390811061122a57fe5b906000526020600020906003020160020160049054906101000a900460ff16600281111561125457fe5b141561131f5761126a858563ffffffff61169416565b63ffffffff8716600090815260046020526040902080549194506112b3918390811061129257fe5b906000526020600020906003020160010154846116ab90919063ffffffff16565b63ffffffff87166000908152600460205260409020805491945090829081106112d857fe5b60009182526020822060039091020154604051600160a060020a039091169185156108fc02918691818181858888f1935050505015801561131d573d6000803e3d6000fd5b505b6001016111ae565b505050505050565b336000908152600260209081526040808320600160a060020a0386168452909152812054611363908363ffffffff61166c16565b336000818152600260209081526040808320600160a060020a0389168085529083529281902085905580519485525191937f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925929081900390910190a350600192915050565b600654604051600160a060020a039091169083156108fc029084906000818181858888f19350505050158015611402573d6000803e3d6000fd5b5063ffffffff8316600081815260046020908152604091829020825160808101845233815291820186905291810192909252906060810183600281111561144557fe5b90528154600180820180855560009485526020948590208451600390940201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909416939093178355938301519082015560408201516002808301805463ffffffff191663ffffffff909316929092178083556060850151929164ff0000000019909116906401000000009084908111156114dc57fe5b02179055505050506005600033600160a060020a0316600160a060020a0316815260200190815260200160002060806040519081016040528033600160a060020a031681526020018481526020018563ffffffff16815260200183600281111561154257fe5b90528154600180820180855560009485526020948590208451600390940201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909416939093178355938301519082015560408201516002808301805463ffffffff191663ffffffff909316929092178083556060850151929164ff0000000019909116906401000000009084908111156115d957fe5b0217905550505050505050565b600160a060020a03918216600090815260026020908152604080832093909416825291909152205490565b600654600160a060020a031681565b600060038263ffffffff1681548110151561163757fe5b600091825260209091206004909102016003015474010000000000000000000000000000000000000000900460ff1692915050565b60008282018381101561167b57fe5b9392505050565b60008282111561168e57fe5b50900390565b60008082848115156116a257fe5b04949350505050565b6000808315156116be5760009150610746565b508282028284828115156116ce57fe5b041461167b57fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061171757805160ff1916838001178555611744565b82800160010185558215611744579182015b82811115611744578251825591602001919060010190611729565b50611750929150611754565b5090565b61069c91905b80821115611750576000815560010161175a5600a165627a7a723058200bed3a65f04b49172fd9abdd92933747919e94d2e7d0abd3b6c7c228dbffec5b0029";

    public static final String FUNC_ADDGAME = "addGame";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BETTING = "betting";

    public static final String FUNC_DECREASEAPPROVAL = "decreaseApproval";

    public static final String FUNC_INCREASEAPPROVAL = "increaseApproval";

    public static final String FUNC_MONEYDIVISION = "moneyDivision";

    public static final String FUNC_RESULTGAME = "ResultGame";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_ACCOUNT1 = "account1";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BETBYADDRESS = "BetByAddress";

    public static final String FUNC_BETBYGAMEID = "BetByGameid";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETBETBYADDRESSINFO = "getBetByAddressInfo";

    public static final String FUNC_GETBETBYADDRESSLENGTH = "getBetByAddressLength";

    public static final String FUNC_GETBETBYGAMEIDLENGTH = "getBetByGameidLength";

    public static final String FUNC_GETBETTINGMONEYBYGAMEID = "getBettingMoneyByGameid";

    public static final String FUNC_GETMYBETTINGMONEYBYGAMEID = "getMyBettingMoneyByGameid";

    public static final String FUNC_GETMYBETTINGTEAM = "getMyBettingTeam";

    public static final String FUNC_GETMYTOTALBETTINGMONEY = "getMyTotalBettingMoney";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected Betting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Betting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> addGame(BigInteger _gameId, String _homeTeam, String _awayTeam, String _date) {
        final Function function = new Function(
                FUNC_ADDGAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId), 
                new org.web3j.abi.datatypes.Utf8String(_homeTeam), 
                new org.web3j.abi.datatypes.Utf8String(_awayTeam), 
                new org.web3j.abi.datatypes.Utf8String(_date)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> betting(BigInteger _gameId, BigInteger _betMoney, BigInteger _gameResult, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BETTING, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId), 
                new org.web3j.abi.datatypes.generated.Uint256(_betMoney), 
                new org.web3j.abi.datatypes.generated.Uint8(_gameResult)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> decreaseApproval(String _spender, BigInteger _subtractedValue) {
        final Function function = new Function(
                FUNC_DECREASEAPPROVAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> increaseApproval(String _spender, BigInteger _addedValue) {
        final Function function = new Function(
                FUNC_INCREASEAPPROVAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> moneyDivision(BigInteger _gameId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_MONEYDIVISION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApprovalEventResponse> approvalEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ApprovalEventResponse> approvalEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> ResultGame(BigInteger _gameId, BigInteger _homeTeamGoals, BigInteger _awayTeamGoals, BigInteger _homeTeamPenaltyGoals, BigInteger _awayTeamPenaltyGoals) {
        final Function function = new Function(
                FUNC_RESULTGAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId), 
                new org.web3j.abi.datatypes.generated.Uint32(_homeTeamGoals), 
                new org.web3j.abi.datatypes.generated.Uint32(_awayTeamGoals), 
                new org.web3j.abi.datatypes.generated.Uint32(_homeTeamPenaltyGoals), 
                new org.web3j.abi.datatypes.generated.Uint32(_awayTeamPenaltyGoals)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_from), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> account1() {
        final Function function = new Function(FUNC_ACCOUNT1, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> allowance(String _owner, String _spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner), 
                new org.web3j.abi.datatypes.Address(_spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple4<String, BigInteger, BigInteger, BigInteger>> BetByAddress(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_BETBYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple4<String, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple4<String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<Tuple4<String, BigInteger, BigInteger, BigInteger>> BetByGameid(BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_BETBYGAMEID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple4<String, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple4<String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<String> getAddress() {
        final Function function = new Function(FUNC_GETADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getBalance() {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple3<BigInteger, BigInteger, BigInteger>> getBetByAddressInfo(BigInteger i) {
        final Function function = new Function(FUNC_GETBETBYADDRESSINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple3<BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple3<BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getBetByAddressLength() {
        final Function function = new Function(FUNC_GETBETBYADDRESSLENGTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getBetByGameidLength(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETBETBYGAMEIDLENGTH, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getBettingMoneyByGameid(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETBETTINGMONEYBYGAMEID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMyBettingMoneyByGameid(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETMYBETTINGMONEYBYGAMEID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMyBettingTeam(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETMYBETTINGTEAM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMyTotalBettingMoney() {
        final Function function = new Function(FUNC_GETMYTOTALBETTINGMONEY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<Betting> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Betting.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Betting> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Betting.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Betting load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Betting(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Betting load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Betting(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger value;
    }

    public static class ApprovalEventResponse {
        public Log log;

        public String owner;

        public String spender;

        public BigInteger value;
    }
}
