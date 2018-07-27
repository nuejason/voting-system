package kr.co.keypair.votingsystem;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
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
    private static final String BINARY = "608060405260068054600160a060020a0319167375b8eccb3993bc5cb0501dd5b0522e95614bfded17905534801561003657600080fd5b50611121806100466000396000f3006080604052600436106100ef5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041662113e0881146100f4578063095ea7b31461011b57806318160ddd1461015357806319293d861461016857806323b872dd1461019457806338cc4831146101be578063650b3587146101ef578063661884631461021057806370a0823114610234578063a9059cbb14610255578063ce56b3e414610279578063d73dd6231461028a578063da0ff1e1146102ae578063dd62ed3e146102c8578063e29664ad146102ef578063ecfd0a56146103c4578063fb632b7c146103d9575b600080fd5b34801561010057600080fd5b506101096103ee565b60408051918252519081900360200190f35b34801561012757600080fd5b5061013f600160a060020a03600435166024356103f4565b604080519115158252519081900360200190f35b34801561015f57600080fd5b5061010961045a565b34801561017457600080fd5b5061019263ffffffff60043581169060243581169060443516610460565b005b3480156101a057600080fd5b5061013f600160a060020a0360043581169060243516604435610606565b3480156101ca57600080fd5b506101d361077d565b60408051600160a060020a039092168252519081900360200190f35b3480156101fb57600080fd5b50610109600160a060020a0360043516610781565b34801561021c57600080fd5b5061013f600160a060020a0360043516602435610806565b34801561024057600080fd5b50610109600160a060020a03600435166108f6565b34801561026157600080fd5b5061013f600160a060020a0360043516602435610911565b61019263ffffffff600435166109f2565b34801561029657600080fd5b5061013f600160a060020a0360043516602435610c58565b61019263ffffffff6004351660243560ff60443516610cf1565b3480156102d457600080fd5b50610109600160a060020a0360043581169060243516610e19565b3480156102fb57600080fd5b506040805160206004803580820135601f810184900484028501840190955284845261019294369492936024939284019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a999881019791965091820194509250829150840183828082843750949750610e449650505050505050565b3480156103d057600080fd5b506101d3610fde565b3480156103e557600080fd5b50610109610fed565b33315b90565b336000818152600260209081526040808320600160a060020a038716808552908352818420869055815186815291519394909390927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925928290030190a350600192915050565b60015490565b600654600160a060020a0316331461047757600080fd5b60035463ffffffff8416600019909101101561049257600080fd5b8160038463ffffffff168154811015156104a857fe5b600091825260209091206004909102016003908101805467ffffffff00000000191664010000000063ffffffff948516021790558054839286169081106104eb57fe5b6000918252602090912060049091020160030180546bffffffff000000000000000019166801000000000000000063ffffffff93841602179055818116908316111561059157600160038463ffffffff1681548110151561054857fe5b6000918252602090912060036004909202010180546cff00000000000000000000000019166c0100000000000000000000000083600281111561058757fe5b0217905550610601565b8063ffffffff168263ffffffff16101561060157600260038463ffffffff168154811015156105bc57fe5b6000918252602090912060036004909202010180546cff00000000000000000000000019166c010000000000000000000000008360028111156105fb57fe5b02179055505b505050565b6000600160a060020a038316151561061d57600080fd5b600160a060020a03841660009081526020819052604090205482111561064257600080fd5b600160a060020a038416600090815260026020908152604080832033845290915290205482111561067257600080fd5b600160a060020a03841660009081526020819052604090205461069b908363ffffffff610ff316565b600160a060020a0380861660009081526020819052604080822093909355908516815220546106d0908363ffffffff61100516565b600160a060020a03808516600090815260208181526040808320949094559187168152600282528281203382529091522054610712908363ffffffff610ff316565b600160a060020a03808616600081815260026020908152604080832033845282529182902094909455805186815290519287169391927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a35060019392505050565b3390565b600080805b6004548110156107ff57600480548290811061079e57fe5b6000918252602090912060039091020154600160a060020a03858116911614156107f7576107f56004828154811015156107d457fe5b9060005260206000209060030201600101548361100590919063ffffffff16565b505b600101610786565b5092915050565b336000908152600260209081526040808320600160a060020a03861684529091528120548083111561085b57336000908152600260209081526040808320600160a060020a0388168452909152812055610890565b61086b818463ffffffff610ff316565b336000908152600260209081526040808320600160a060020a03891684529091529020555b336000818152600260209081526040808320600160a060020a0389168085529083529281902054815190815290519293927f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925929181900390910190a35060019392505050565b600160a060020a031660009081526020819052604090205490565b6000600160a060020a038316151561092857600080fd5b3360009081526020819052604090205482111561094457600080fd5b33600090815260208190526040902054610964908363ffffffff610ff316565b3360009081526020819052604080822092909255600160a060020a03851681522054610996908363ffffffff61100516565b600160a060020a038416600081815260208181526040918290209390935580518581529051919233927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9281900390910190a350600192915050565b6006546000908190819081908190600160a060020a03163314610a1457600080fd5b600094506000935060009250600091505b600454821015610b21576003805463ffffffff8816908110610a4357fe5b9060005260206000209060040201600301600c9054906101000a900460ff166002811115610a6d57fe5b6004805484908110610a7b57fe5b906000526020600020906003020160020160049054906101000a900460ff166002811115610aa557fe5b1415610ae157610ade600483815481101515610abd57fe5b9060005260206000209060030201600101548561100590919063ffffffff16565b93505b610b14600483815481101515610af357fe5b9060005260206000209060030201600101548661100590919063ffffffff16565b9450600190910190610a25565b5060005b600454811015610c50576003805463ffffffff8816908110610b4357fe5b9060005260206000209060040201600301600c9054906101000a900460ff166002811115610b6d57fe5b6004805483908110610b7b57fe5b906000526020600020906003020160020160049054906101000a900460ff166002811115610ba557fe5b1415610c4857610bbb858563ffffffff61101b16565b9250610bf0600482815481101515610bcf57fe5b9060005260206000209060030201600101548461103290919063ffffffff16565b9250600481815481101515610c0157fe5b60009182526020822060039091020154604051600160a060020a039091169185156108fc02918691818181858888f19350505050158015610c46573d6000803e3d6000fd5b505b600101610b25565b505050505050565b336000908152600260209081526040808320600160a060020a0386168452909152812054610c8c908363ffffffff61100516565b336000818152600260209081526040808320600160a060020a0389168085529083529281902085905580519485525191937f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925929081900390910190a350600192915050565b3331821115610cff57600080fd5b600654604051600160a060020a039091169083156108fc029084906000818181858888f19350505050158015610d39573d6000803e3d6000fd5b50600460806040519081016040528033600160a060020a031681526020018481526020018563ffffffff168152602001836002811115610d7557fe5b90528154600180820180855560009485526020948590208451600390940201805473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a03909416939093178355938301519082015560408201516002808301805463ffffffff191663ffffffff909316929092178083556060850151929164ff000000001990911690640100000000908490811115610e0c57fe5b0217905550505050505050565b600160a060020a03918216600090815260026020908152604080832093909416825291909152205490565b600654600090600160a060020a03163314610e5e57600080fd5b506040805160e08101825284815260208082018590529181018390526000606082018190526080820181905260a0820181905260c08201819052600380546001810180835591835283518051939592949360049092027fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b0192610ee69284929091019061105d565b506020828101518051610eff926001850192019061105d565b5060408201518051610f1b91600284019160209091019061105d565b506060820151600382018054608085015160a086015163ffffffff1990921663ffffffff9485161767ffffffff00000000191664010000000091851691909102176bffffffff000000000000000019166801000000000000000093909116929092029190911780825560c084015191906cff00000000000000000000000019166c01000000000000000000000000836002811115610fb557fe5b0217905550505050610fd760018263ffffffff1661100590919063ffffffff16565b5050505050565b600654600160a060020a031681565b60035490565b600082821115610fff57fe5b50900390565b60008282018381101561101457fe5b9392505050565b600080828481151561102957fe5b04949350505050565b60008083151561104557600091506107ff565b5082820282848281151561105557fe5b041461101457fe5b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061109e57805160ff19168380011785556110cb565b828001600101855582156110cb579182015b828111156110cb5782518255916020019190600101906110b0565b506110d79291506110db565b5090565b6103f191905b808211156110d757600081556001016110e15600a165627a7a72305820368f2a256622cea1b3837bfde5463dd0aa5f9cc86f9171e85576dd753bedc15f0029";

    public static final String FUNC_GETBALANCES = "getBalances";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_RESULTGAME = "ResultGame";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_GETBETTINGMONEYBYADDRESS = "getBettingMoneyByAddress";

    public static final String FUNC_DECREASEAPPROVAL = "decreaseApproval";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_MONEYDIVISION = "moneyDivision";

    public static final String FUNC_INCREASEAPPROVAL = "increaseApproval";

    public static final String FUNC_BETTING = "betting";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_ADDGAME = "addGame";

    public static final String FUNC_ACCOUNT1 = "account1";

    public static final String FUNC_GAMESCOUNT = "gamesCount";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected Betting(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Betting(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> getBalances() {
        final Function function = new Function(FUNC_GETBALANCES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> ResultGame(BigInteger _gameId, BigInteger _homeTeamGoals, BigInteger _awayTeamGoals) {
        final Function function = new Function(
                FUNC_RESULTGAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint32(_gameId), 
                new org.web3j.abi.datatypes.generated.Uint32(_homeTeamGoals), 
                new org.web3j.abi.datatypes.generated.Uint32(_awayTeamGoals)), 
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

    public RemoteCall<String> getAddress() {
        final Function function = new Function(FUNC_GETADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> getBettingMoneyByAddress(String _add) {
        final Function function = new Function(
                FUNC_GETBETTINGMONEYBYADDRESS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> decreaseApproval(String _spender, BigInteger _subtractedValue) {
        final Function function = new Function(
                FUNC_DECREASEAPPROVAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
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

    public RemoteCall<TransactionReceipt> increaseApproval(String _spender, BigInteger _addedValue) {
        final Function function = new Function(
                FUNC_INCREASEAPPROVAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_addedValue)), 
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

    public RemoteCall<BigInteger> allowance(String _owner, String _spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner), 
                new org.web3j.abi.datatypes.Address(_spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addGame(String _homeTeam, String _awayTeam, String _date) {
        final Function function = new Function(
                FUNC_ADDGAME, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_homeTeam), 
                new org.web3j.abi.datatypes.Utf8String(_awayTeam), 
                new org.web3j.abi.datatypes.Utf8String(_date)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> account1() {
        final Function function = new Function(FUNC_ACCOUNT1, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> gamesCount() {
        final Function function = new Function(FUNC_GAMESCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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

    public static class ApprovalEventResponse {
        public Log log;

        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger value;
    }
}
