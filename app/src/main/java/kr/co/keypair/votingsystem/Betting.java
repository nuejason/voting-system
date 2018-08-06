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
import org.web3j.abi.datatypes.Utf8String;
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
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tuples.generated.Tuple8;
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
    private static final String BINARY = "608060405260078054600160a060020a0319167375b8eccb3993bc5cb0501dd5b0522e95614bfded17905534801561003657600080fd5b50611b40806100466000396000f3006080604052600436106101485763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166301f24701811461014d578063095ea7b3146101cc57806312065fe01461020457806318160ddd1461022b57806323b872dd1461024057806324d107051461026a5780632afb65b31461027f57806338cc48311461029d57806366188463146102ce57806370a08231146102f2578063738b2ec6146103135780637c70a9da146103285780637f1fc12a1461034c578063848a235114610361578063a7402bbf146103cf578063a9059cbb146104b1578063ad8abead146104d5578063bab5b49d14610506578063c0bfe1621461053c578063ce56b3e414610599578063d73dd623146105aa578063da0ff1e1146105ce578063da5d2fac146105e8578063dd62ed3e14610789578063ecfd0a56146107b0575b600080fd5b34801561015957600080fd5b5061016e63ffffffff600435166024356107c5565b6040518086600160a060020a0316600160a060020a031681526020018581526020018463ffffffff1663ffffffff1681526020018360028111156101ae57fe5b60ff1681526020018281526020019550505050505060405180910390f35b3480156101d857600080fd5b506101f0600160a060020a0360043516602435610828565b604080519115158252519081900360200190f35b34801561021057600080fd5b5061021961088e565b60408051918252519081900360200190f35b34801561023757600080fd5b50610219610894565b34801561024c57600080fd5b506101f0600160a060020a036004358116906024351660443561089a565b34801561027657600080fd5b50610219610a11565b34801561028b57600080fd5b5061021963ffffffff60043516610a24565b3480156102a957600080fd5b506102b2610a9e565b60408051600160a060020a039092168252519081900360200190f35b3480156102da57600080fd5b506101f0600160a060020a0360043516602435610aa2565b3480156102fe57600080fd5b50610219600160a060020a0360043516610b92565b34801561031f57600080fd5b50610219610bad565b34801561033457600080fd5b5061016e600160a060020a0360043516602435610bf9565b34801561035857600080fd5b50610219610c14565b34801561036d57600080fd5b5061037f63ffffffff60043516610c1a565b6040805163ffffffff80881682528681166020830152858116928201929092529083166060820152608081018260028111156103b757fe5b60ff1681526020019550505050505060405180910390f35b3480156103db57600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526104af95833563ffffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610c749650505050505050565b005b3480156104bd57600080fd5b506101f0600160a060020a0360043516602435610d8b565b3480156104e157600080fd5b506104ed600435610e6c565b6040805163ffffffff9092168252519081900360200190f35b34801561051257600080fd5b506104af63ffffffff60043581169060243581169060443581169060643581169060843516610eaa565b34801561054857600080fd5b506105546004356110bd565b604051808563ffffffff1663ffffffff16815260200184815260200183600281111561057c57fe5b60ff16815260200182815260200194505050505060405180910390f35b6104af63ffffffff600435166111a6565b3480156105b657600080fd5b506101f0600160a060020a03600435166024356114e8565b6104af63ffffffff6004351660243560ff60443516611581565b3480156105f457600080fd5b5061060663ffffffff600435166117bf565b6040805163ffffffff8088166060830152868116608083015285811660a0830152841660c08201529081906020820190820160e0830185600281111561064857fe5b60ff16815260200184810384528c818151815260200191508051906020019080838360005b8381101561068557818101518382015260200161066d565b50505050905090810190601f1680156106b25780820380516001836020036101000a031916815260200191505b5084810383528b5181528b516020918201918d019080838360005b838110156106e55781810151838201526020016106cd565b50505050905090810190601f1680156107125780820380516001836020036101000a031916815260200191505b5084810382528a5181528a516020918201918c019080838360005b8381101561074557818101518382015260200161072d565b50505050905090810190601f1680156107725780820380516001836020036101000a031916815260200191505b509b50505050505050505050505060405180910390f35b34801561079557600080fd5b50610219600160a060020a03600435811690602435166119d8565b3480156107bc57600080fd5b506102b2611a03565b6005602052816000526040600020818154811015156107e057fe5b60009182526020909120600490910201805460018201546002830154600390930154600160a060020a039092169450925063ffffffff821691640100000000900460ff169085565b336000818152600260209081526040808320600160a060020a038716808552908352818420869055815186815291519394909390927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925928290030190a350600192915050565b33315b90565b60015490565b6000600160a060020a03831615156108b157600080fd5b600160a060020a0384166000908152602081905260409020548211156108d657600080fd5b600160a060020a038416600090815260026020908152604080832033845290915290205482111561090657600080fd5b600160a060020a03841660009081526020819052604090205461092f908363ffffffff611a1216565b600160a060020a038086166000908152602081905260408082209390935590851681522054610964908363ffffffff611a2416565b600160a060020a038085166000908152602081815260408083209490945591871681526002825282812033825290915220546109a6908363ffffffff611a1216565b600160a060020a03808616600081815260026020908152604080832033845282529182902094909455805186815290519287169391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a35060019392505050565b3360009081526006602052604090205490565b600080805b63ffffffff8416600090815260056020526040902054811015610a975763ffffffff841660009081526005602052604090208054610a8d919083908110610a6c57fe5b90600052602060002090600402016001015483611a2490919063ffffffff16565b9150600101610a29565b5092915050565b3390565b336000908152600260209081526040808320600160a060020a038616845290915281205480831115610af757336000908152600260209081526040808320600160a060020a0388168452909152812055610b2c565b610b07818463ffffffff611a1216565b336000908152600260209081526040808320600160a060020a03891684529091529020555b336000818152600260209081526040808320600160a060020a0389168085529083529281902054815190815290519293927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925929181900390910190a35060019392505050565b600160a060020a031660009081526020819052604090205490565b600080805b33600090815260066020526040902054811015610bf3573360009081526006602052604090208054610be9919083908110610a6c57fe5b9150600101610bb2565b50919050565b6006602052816000526040600020818154811015156107e057fe5b60035490565b63ffffffff9081166000908152600460205260409020600301548082169264010000000082048316926801000000000000000083048116926c01000000000000000000000000810490911691608060020a90910460ff1690565b600754600160a060020a03163314610c8b57600080fd5b6003805460018101909155600881047fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b01805463ffffffff8088166004600790951685026101000a8181029202199092161790915560009081526020918252604090208451610cfc92860190611a7c565b5063ffffffff841660009081526004602090815260409091208351610d2992600190920191850190611a7c565b5063ffffffff841660009081526004602090815260409091208251610d5692600290920191840190611a7c565b5050505063ffffffff166000908152600460205260409020600301805470ffffffffffffffffffffffffffffffffff19169055565b6000600160a060020a0383161515610da257600080fd5b33600090815260208190526040902054821115610dbe57600080fd5b33600090815260208190526040902054610dde908363ffffffff611a1216565b3360009081526020819052604080822092909255600160a060020a03851681522054610e10908363ffffffff611a2416565b600160a060020a038416600081815260208181526040918290209390935580518581529051919233927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a350600192915050565b6000600382815481101515610e7d57fe5b90600052602060002090600891828204019190066004029054906101000a900463ffffffff169050919050565b600754600160a060020a03163314610ec157600080fd5b63ffffffff8581166000908152600460205260409020600301805463ffffffff191686831690811767ffffffff000000001916640100000000938716938402179091551115610f4d5763ffffffff8516600090815260046020526040902060030180546001919070ff000000000000000000000000000000001916608060020a835b02179055506110b6565b8263ffffffff168463ffffffff161015610f9e5763ffffffff8516600090815260046020526040902060030180546002919070ff000000000000000000000000000000001916608060020a83610f43565b8263ffffffff168463ffffffff1614156110b65763ffffffff858116600090815260046020526040902060030180546bffffffff0000000000000000191668010000000000000000858416908102919091176fffffffff00000000000000000000000019166c0100000000000000000000000093851693840217909155111561105e5763ffffffff8516600090815260046020526040902060030180546001919070ff000000000000000000000000000000001916608060020a83610f43565b8063ffffffff168263ffffffff1610156110b65763ffffffff85166000908152600460205260409020600301805470ff0000000000000000000000000000000019167002000000000000000000000000000000001790555b5050505050565b336000908152600660205260408120805482918291829190869081106110df57fe5b600091825260208083206002600490930201919091015433835260069091526040909120805463ffffffff909216918790811061111857fe5b60009182526020808320600160049093020191909101543383526006909152604090912080548890811061114857fe5b600091825260208083206004929092029091016002015433835260069091526040909120805464010000000090920460ff16918990811061118557fe5b90600052602060002090600402016003015493509350935093509193509193565b60075460009081908190819081908190600160a060020a031633146111ca57600080fd5b6111d387610a24565b95506000945060009350600092505b63ffffffff87166000908152600560205260409020548310156112d35763ffffffff8716600090815260046020526040902060030154608060020a900460ff16600281111561122d57fe5b63ffffffff8816600090815260056020526040902080548590811061124e57fe5b906000526020600020906004020160020160049054906101000a900460ff16600281111561127857fe5b14156112c85763ffffffff8716600090815260056020526040902080546112c59190859081106112a457fe5b90600052602060002090600402016001015486611a2490919063ffffffff16565b94505b6001909201916111e2565b600091505b63ffffffff87166000908152600560205260409020548210156114df5763ffffffff8716600090815260046020526040902060030154608060020a900460ff16600281111561132357fe5b63ffffffff8816600090815260056020526040902080548490811061134457fe5b906000526020600020906004020160020160049054906101000a900460ff16600281111561136e57fe5b14156114d457611384868663ffffffff611a3a16565b63ffffffff8816600090815260056020526040902080549195506113cd91849081106113ac57fe5b90600052602060002090600402016001015485611a5190919063ffffffff16565b63ffffffff88166000908152600560205260409020805491955090839081106113f257fe5b60009182526020822060049091020154604051600160a060020a039091169186156108fc02918791818181858888f19350505050158015611437573d6000803e3d6000fd5b50600090505b336000908152600660205260409020548110156114d457336000908152600660205260409020805463ffffffff891691908390811061147857fe5b600091825260209091206002600490920201015463ffffffff1614156114cc573360009081526006602052604090208054859190839081106114b657fe5b9060005260206000209060040201600301819055505b60010161143d565b6001909101906112d8565b50505050505050565b336000908152600260209081526040808320600160a060020a038616845290915281205461151c908363ffffffff611a2416565b336000818152600260209081526040808320600160a060020a0389168085529083529281902085905580519485525191937f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925929081900390910190a350600192915050565b600754604051600160a060020a039091169083156108fc029084906000818181858888f193505050501580156115bb573d6000803e3d6000fd5b5063ffffffff8316600081815260056020908152604091829020825160a0810184523381529182018690529181019290925290606081018360028111156115fe57fe5b81526000602091820181905283546001808201808755958352918390208451600490920201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909216919091178155918301519082015560408201516002808301805463ffffffff191663ffffffff909316929092178083556060850151929164ff00000000199091169064010000000090849081111561169b57fe5b0217905550608082015181600301555050506006600033600160a060020a0316600160a060020a0316815260200190815260200160002060a06040519081016040528033600160a060020a031681526020018481526020018563ffffffff16815260200183600281111561170b57fe5b81526000602091820181905283546001808201808755958352918390208451600490920201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909216919091178155918301519082015560408201516002808301805463ffffffff191663ffffffff909316929092178083556060850151929164ff0000000019909116906401000000009084908111156117a857fe5b021790555060808201518160030155505050505050565b60046020908152600091825260409182902080548351601f600260001961010060018616150201909316929092049182018490048402810184019094528084529092918391908301828280156118565780601f1061182b57610100808354040283529160200191611856565b820191906000526020600020905b81548152906001019060200180831161183957829003601f168201915b505050505090806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156118f45780601f106118c9576101008083540402835291602001916118f4565b820191906000526020600020905b8154815290600101906020018083116118d757829003601f168201915b50505060028085018054604080516020601f60001961010060018716150201909416959095049283018590048502810185019091528181529596959450909250908301828280156119865780601f1061195b57610100808354040283529160200191611986565b820191906000526020600020905b81548152906001019060200180831161196957829003601f168201915b5050506003909301549192505063ffffffff8082169164010000000081048216916801000000000000000082048116916c0100000000000000000000000081049091169060ff608060020a9091041688565b600160a060020a03918216600090815260026020908152604080832093909416825291909152205490565b600754600160a060020a031681565b600082821115611a1e57fe5b50900390565b600082820183811015611a3357fe5b9392505050565b6000808284811515611a4857fe5b04949350505050565b600080831515611a645760009150610a97565b50828202828482811515611a7457fe5b0414611a3357fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611abd57805160ff1916838001178555611aea565b82800160010185558215611aea579182015b82811115611aea578251825591602001919060010190611acf565b50611af6929150611afa565b5090565b61089191905b80821115611af65760008155600101611b005600a165627a7a723058204f5af69da137ecada7f846411788414bd6b8917b89b7aa125c868a6e029eb63f0029";

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

    public static final String FUNC_GAMES = "games";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETBETBYADDRESSINFO = "getBetByAddressInfo";

    public static final String FUNC_GETBETBYADDRESSLENGTH = "getBetByAddressLength";

    public static final String FUNC_GETBETTINGMONEYBYGAMEID = "getBettingMoneyByGameid";

    public static final String FUNC_GETGAMEIDSBYINT = "getGameIDsByint";

    public static final String FUNC_GETGAMEIDSLEN = "getGameIDsLen";

    public static final String FUNC_GETGAMEINFO = "getGameInfo";

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

    public RemoteCall<Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger>> BetByAddress(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_BETBYADDRESS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0),
                        new org.web3j.abi.datatypes.generated.Uint256(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger>> BetByGameid(BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_BETBYGAMEID,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(param0),
                        new org.web3j.abi.datatypes.generated.Uint256(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<Tuple8<String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> games(BigInteger param0) {
        final Function function = new Function(FUNC_GAMES,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple8<String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple8<String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple8<String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<String, String, String, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (String) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue(),
                                (BigInteger) results.get(5).getValue(),
                                (BigInteger) results.get(6).getValue(),
                                (BigInteger) results.get(7).getValue());
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

    public RemoteCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> getBetByAddressInfo(BigInteger i) {
        final Function function = new Function(FUNC_GETBETBYADDRESSINFO,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getBetByAddressLength() {
        final Function function = new Function(FUNC_GETBETBYADDRESSLENGTH,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getBettingMoneyByGameid(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETBETTINGMONEYBYGAMEID,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getGameIDsByint(BigInteger i) {
        final Function function = new Function(FUNC_GETGAMEIDSBYINT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(i)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getGameIDsLen() {
        final Function function = new Function(FUNC_GETGAMEIDSLEN,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> getGameInfo(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETGAMEINFO,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint32>() {}, new TypeReference<Uint8>() {}));
        return new RemoteCall<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (BigInteger) results.get(4).getValue());
                    }
                });
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
