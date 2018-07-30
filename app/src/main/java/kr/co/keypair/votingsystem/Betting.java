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
    private static final String BINARY = "608060405260068054600160a060020a0319167375b8eccb3993bc5cb0501dd5b0522e95614bfded17905534801561003657600080fd5b5061165f806100466000396000f30060806040526004361061011c5763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166301f247018114610121578063095ea7b31461019957806312065fe0146101d15780631398a64c146101f857806318160ddd1461021657806323b872dd1461022b5780632afb65b31461025557806338cc48311461027357806366188463146102a457806370a08231146102c8578063738b2ec6146102e95780637c70a9da146102fe578063a28515d414610322578063a7402bbf1461035e578063a9059cbb14610440578063bab5b49d14610464578063ce56b3e41461049a578063d73dd623146104ab578063da0ff1e1146104cf578063dd62ed3e146104e9578063ecfd0a5614610510575b600080fd5b34801561012d57600080fd5b5061014263ffffffff60043516602435610525565b6040518085600160a060020a0316600160a060020a031681526020018481526020018363ffffffff1663ffffffff16815260200182600281111561018257fe5b60ff16815260200194505050505060405180910390f35b3480156101a557600080fd5b506101bd600160a060020a0360043516602435610583565b604080519115158252519081900360200190f35b3480156101dd57600080fd5b506101e66105e9565b60408051918252519081900360200190f35b34801561020457600080fd5b506101e663ffffffff600435166105ef565b34801561022257600080fd5b506101e661069d565b34801561023757600080fd5b506101bd600160a060020a03600435811690602435166044356106a3565b34801561026157600080fd5b506101e663ffffffff6004351661081a565b34801561027f57600080fd5b5061028861086c565b60408051600160a060020a039092168252519081900360200190f35b3480156102b057600080fd5b506101bd600160a060020a0360043516602435610870565b3480156102d457600080fd5b506101e6600160a060020a0360043516610960565b3480156102f557600080fd5b506101e661097b565b34801561030a57600080fd5b50610142600160a060020a03600435166024356109c7565b34801561032e57600080fd5b5061033a6004356109e2565b6040518082600281111561034a57fe5b60ff16815260200191505060405180910390f35b34801561036a57600080fd5b5060408051602060046024803582810135601f810185900485028601850190965285855261043e95833563ffffffff1695369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610a829650505050505050565b005b34801561044c57600080fd5b506101bd600160a060020a0360043516602435610c88565b34801561047057600080fd5b5061043e63ffffffff60043581169060243581169060443581169060643581169060843516610d69565b61043e63ffffffff60043516610f80565b3480156104b757600080fd5b506101bd600160a060020a0360043516602435611240565b61043e63ffffffff6004351660243560ff604435166112d9565b3480156104f557600080fd5b506101e6600160a060020a03600435811690602435166114f7565b34801561051c57600080fd5b50610288611522565b60046020528160005260406000208181548110151561054057fe5b6000918252602090912060039091020180546001820154600290920154600160a060020a03909116935090915063ffffffff811690640100000000900460ff1684565b336000818152600260209081526040808320600160a060020a038716808552908352818420869055815186815291519394909390927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925928290030190a350600192915050565b33315b90565b600080805b336000908152600560205260409020548110156106965733600090815260056020526040902080548290811061062657fe5b600091825260209091206002600390920201015463ffffffff8581169116141561068e57336000908152600560205260409020805461068b91908390811061066a57fe5b9060005260206000209060030201600101548361153190919063ffffffff16565b91505b6001016105f4565b5092915050565b60015490565b6000600160a060020a03831615156106ba57600080fd5b600160a060020a0384166000908152602081905260409020548211156106df57600080fd5b600160a060020a038416600090815260026020908152604080832033845290915290205482111561070f57600080fd5b600160a060020a038416600090815260208190526040902054610738908363ffffffff61154716565b600160a060020a03808616600090815260208190526040808220939093559085168152205461076d908363ffffffff61153116565b600160a060020a038085166000908152602081815260408083209490945591871681526002825282812033825290915220546107af908363ffffffff61154716565b600160a060020a03808616600081815260026020908152604080832033845282529182902094909455805186815290519287169391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a35060019392505050565b600080805b63ffffffff84166000908152600460205260409020548110156106965763ffffffff84166000908152600460205260409020805461086291908390811061066a57fe5b915060010161081f565b3390565b336000908152600260209081526040808320600160a060020a0386168452909152812054808311156108c557336000908152600260209081526040808320600160a060020a03881684529091528120556108fa565b6108d5818463ffffffff61154716565b336000908152600260209081526040808320600160a060020a03891684529091529020555b336000818152600260209081526040808320600160a060020a0389168085529083529281902054815190815290519293927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925929181900390910190a35060019392505050565b600160a060020a031660009081526020819052604090205490565b600080805b336000908152600560205260409020548110156109c15733600090815260056020526040902080546109b791908390811061066a57fe5b9150600101610980565b50919050565b60056020528160005260406000208181548110151561054057fe5b6000805b336000908152600560205260409020548110156109c157336000908152600560205260409020805482908110610a1857fe5b600091825260209091206002600390920201015463ffffffff16831415610a7a57336000908152600560205260409020805482908110610a5457fe5b906000526020600020906003020160020160049054906101000a900460ff1691506109c1565b6001016109e6565b600654600160a060020a03163314610a9957600080fd5b6040805161012081018252848152602080820185905291810183905263ffffffff8616606082015260006080820181905260a0820181905260c0820181905260e08201819052610100820181905260038054600181018083559190925282518051919460049093027fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0192610b339284929091019061159b565b506020828101518051610b4c926001850192019061159b565b5060408201518051610b6891600284019160209091019061159b565b506060820151600382018054608085015160a086015160c087015160e088015163ffffffff9081167001000000000000000000000000000000000273ffffffff00000000000000000000000000000000199282166c01000000000000000000000000026fffffffff0000000000000000000000001994831668010000000000000000026bffffffff0000000000000000199684166401000000000267ffffffff000000001994909a1663ffffffff19909816979097179290921697909717939093169390931716171691909117808255610100840151919074ff0000000000000000000000000000000000000000191674010000000000000000000000000000000000000000836002811115610c7a57fe5b021790555050505050505050565b6000600160a060020a0383161515610c9f57600080fd5b33600090815260208190526040902054821115610cbb57600080fd5b33600090815260208190526040902054610cdb908363ffffffff61154716565b3360009081526020819052604080822092909255600160a060020a03851681522054610d0d908363ffffffff61153116565b600160a060020a038416600081815260208181526040918290209390935580518581529051919233927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a350600192915050565b600654600160a060020a03163314610d8057600080fd5b8360038663ffffffff16815481101515610d9657fe5b600091825260209091206004909102016003908101805467ffffffff00000000191664010000000063ffffffff94851602179055805485928816908110610dd957fe5b6000918252602090912060049091020160030180546bffffffff000000000000000019166801000000000000000063ffffffff938416021790558381169085161115610e8f57600160038663ffffffff16815481101515610e3657fe5b60009182526020909120600360049092020101805474ff0000000000000000000000000000000000000000191674010000000000000000000000000000000000000000836002811115610e8557fe5b0217905550610f79565b8263ffffffff168463ffffffff161015610eba57600260038663ffffffff16815481101515610e3657fe5b8263ffffffff168463ffffffff161415610f79578063ffffffff168263ffffffff161115610ef957600160038663ffffffff16815481101515610e3657fe5b8063ffffffff168263ffffffff161015610f7957600260038663ffffffff16815481101515610f2457fe5b60009182526020909120600360049092020101805474ff0000000000000000000000000000000000000000191674010000000000000000000000000000000000000000836002811115610f7357fe5b02179055505b5050505050565b6006546000908190819081908190600160a060020a03163314610fa257600080fd5b610fab8661081a565b94506000935060009250600091505b63ffffffff86166000908152600460205260409020548210156110bb576003805463ffffffff8816908110610feb57fe5b906000526020600020906004020160030160149054906101000a900460ff16600281111561101557fe5b63ffffffff8716600090815260046020526040902080548490811061103657fe5b906000526020600020906003020160020160049054906101000a900460ff16600281111561106057fe5b14156110b05763ffffffff8616600090815260046020526040902080546110ad91908490811061108c57fe5b9060005260206000209060030201600101548561153190919063ffffffff16565b93505b600190910190610fba565b5060005b63ffffffff8616600090815260046020526040902054811015611238576003805463ffffffff88169081106110f057fe5b906000526020600020906004020160030160149054906101000a900460ff16600281111561111a57fe5b63ffffffff8716600090815260046020526040902080548390811061113b57fe5b906000526020600020906003020160020160049054906101000a900460ff16600281111561116557fe5b14156112305761117b858563ffffffff61155916565b63ffffffff8716600090815260046020526040902080549194506111c491839081106111a357fe5b9060005260206000209060030201600101548461157090919063ffffffff16565b63ffffffff87166000908152600460205260409020805491945090829081106111e957fe5b60009182526020822060039091020154604051600160a060020a039091169185156108fc02918691818181858888f1935050505015801561122e573d6000803e3d6000fd5b505b6001016110bf565b505050505050565b336000908152600260209081526040808320600160a060020a0386168452909152812054611274908363ffffffff61153116565b336000818152600260209081526040808320600160a060020a0389168085529083529281902085905580519485525191937f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925929081900390910190a350600192915050565b600654604051600160a060020a039091169083156108fc029084906000818181858888f19350505050158015611313573d6000803e3d6000fd5b5063ffffffff8316600081815260046020908152604091829020825160808101845233815291820186905291810192909252906060810183600281111561135657fe5b90528154600180820180855560009485526020948590208451600390940201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909416939093178355938301519082015560408201516002808301805463ffffffff191663ffffffff909316929092178083556060850151929164ff0000000019909116906401000000009084908111156113ed57fe5b02179055505050506005600033600160a060020a0316600160a060020a0316815260200190815260200160002060806040519081016040528033600160a060020a031681526020018481526020018563ffffffff16815260200183600281111561145357fe5b90528154600180820180855560009485526020948590208451600390940201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909416939093178355938301519082015560408201516002808301805463ffffffff191663ffffffff909316929092178083556060850151929164ff0000000019909116906401000000009084908111156114ea57fe5b0217905550505050505050565b600160a060020a03918216600090815260026020908152604080832093909416825291909152205490565b600654600160a060020a031681565b60008282018381101561154057fe5b9392505050565b60008282111561155357fe5b50900390565b600080828481151561156757fe5b04949350505050565b6000808315156115835760009150610696565b5082820282848281151561159357fe5b041461154057fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106115dc57805160ff1916838001178555611609565b82800160010185558215611609579182015b828111156116095782518255916020019190600101906115ee565b50611615929150611619565b5090565b6105ec91905b80821115611615576000815560010161161f5600a165627a7a723058205a4a4dfe4b5b74230abe52f090b874c8445ad83f4d1fdbddc1a83fa139272c3f0029";

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
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
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
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
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
                Arrays.<Type>asList(new Uint32(_gameId),
                        new org.web3j.abi.datatypes.Utf8String(_homeTeam),
                        new org.web3j.abi.datatypes.Utf8String(_awayTeam),
                        new org.web3j.abi.datatypes.Utf8String(_date)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE,
                Arrays.<Type>asList(new Address(_spender),
                        new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> betting(BigInteger _gameId, BigInteger _betMoney, BigInteger _gameResult, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_BETTING,
                Arrays.<Type>asList(new Uint32(_gameId),
                        new Uint256(_betMoney),
                        new Uint8(_gameResult)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
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
                EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
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
                Arrays.<Type>asList(new Address(_spender),
                        new Uint256(_subtractedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> increaseApproval(String _spender, BigInteger _addedValue) {
        final Function function = new Function(
                FUNC_INCREASEAPPROVAL,
                Arrays.<Type>asList(new Address(_spender),
                        new Uint256(_addedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> moneyDivision(BigInteger _gameId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_MONEYDIVISION,
                Arrays.<Type>asList(new Uint32(_gameId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> ResultGame(BigInteger _gameId, BigInteger _homeTeamGoals, BigInteger _awayTeamGoals, BigInteger _homeTeamPenaltyGoals, BigInteger _awayTeamPenaltyGoals) {
        final Function function = new Function(
                FUNC_RESULTGAME,
                Arrays.<Type>asList(new Uint32(_gameId),
                        new Uint32(_homeTeamGoals),
                        new Uint32(_awayTeamGoals),
                        new Uint32(_homeTeamPenaltyGoals),
                        new Uint32(_awayTeamPenaltyGoals)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER,
                Arrays.<Type>asList(new Address(_to),
                        new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM,
                Arrays.<Type>asList(new Address(_from),
                        new Address(_to),
                        new Uint256(_value)),
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
                Arrays.<Type>asList(new Address(_owner),
                        new Address(_spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(new Address(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Tuple4<String, BigInteger, BigInteger, BigInteger>> BetByAddress(String param0, BigInteger param1) {
        final Function function = new Function(FUNC_BETBYADDRESS,
                Arrays.<Type>asList(new Address(param0),
                        new Uint256(param1)),
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
                Arrays.<Type>asList(new Uint32(param0),
                        new Uint256(param1)),
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

    public RemoteCall<BigInteger> getBettingMoneyByGameid(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETBETTINGMONEYBYGAMEID,
                Arrays.<Type>asList(new Uint32(_gameId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMyBettingMoneyByGameid(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETMYBETTINGMONEYBYGAMEID,
                Arrays.<Type>asList(new Uint32(_gameId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getMyBettingTeam(BigInteger _gameId) {
        final Function function = new Function(FUNC_GETMYBETTINGTEAM,
                Arrays.<Type>asList(new Uint256(_gameId)),
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