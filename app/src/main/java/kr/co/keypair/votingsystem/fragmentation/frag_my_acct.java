package kr.co.keypair.votingsystem.fragmentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Betting;
import kr.co.keypair.votingsystem.MainActivity;
import kr.co.keypair.votingsystem.R;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

public class frag_my_acct extends Fragment{
    private TextView address,balance;
    private Spinner spinner;
    private String str_balance;
    private BigDecimal Default_num = new BigDecimal(1000);
    private TextView total;    //
    private EditText editText;//
    private Button button;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("내 계좌");
        v= inflater.inflate(R.layout.fragment_frag_my_acct, container, false);

        EthGetBalance ethGetBalance = null;

        try {
            ethGetBalance = MainActivity.web3.ethGetBalance(MainActivity.u_a_s,LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final BigInteger user_balance = ethGetBalance.getBalance();
        str_balance = "" + user_balance;

        address =(TextView)v.findViewById(R.id.address);
        balance = (TextView)v.findViewById(R.id.balance);
        final BigDecimal BD = new BigDecimal(user_balance);
        address.setText(MainActivity.u_a_s);
        balance.setText(str_balance);

        final String list[] = getResources().getStringArray(R.array.unit);

        spinner = (Spinner)v.findViewById(R.id.spinner1);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.unit));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spinner.getSelectedItem().toString();
                String balance_s = "";
                BigDecimal Result = null, divide_num = new BigDecimal(1);
                int index_i = 0;
                if(text.equals(list[0])){
                    index_i = 0;
                }else if(text.equals(list[1]) || text.equals(list[2]) || text.equals(list[3])){
                    index_i = 1;
                }else if(text.equals(list[4]) || text.equals(list[5]) || text.equals(list[6])){
                    index_i = 2;
                }else if(text.equals(list[7]) || text.equals(list[8]) || text.equals(list[9]) || text.equals(list[10])){
                    index_i = 3;
                }else if(text.equals(list[11]) || text.equals(list[12]) || text.equals(list[13])){
                    index_i = 4;
                }else if(text.equals(list[14]) || text.equals(list[15]) || text.equals(list[16])){
                    index_i = 5;
                }else if(text.equals(list[17])){
                    index_i = 6;
                }else if(text.equals(list[18]) || text.equals(list[19]) || text.equals(list[20])){
                    index_i = 7;
                }else if(text.equals(list[21])){
                    index_i = 8;
                }else if(text.equals(list[22])){
                    index_i = 9;
                }else if(text.equals(list[23])){
                    index_i = 10;
                }
                for(int i = 0; i < index_i; i++){
                    divide_num = divide_num.multiply(Default_num);
                }
                Result = BD.divide(divide_num);
                balance_s = "" + Result;
                balance.setText(balance_s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //여기부터 테스트

        BigInteger Total_money = null;
        total = (TextView)v.findViewById(R.id.total);
        MainActivity.contract.addGame(BigInteger.valueOf(0),"h1","a1","180730");


        editText = v.findViewById(R.id.editText);
        button = v.findViewById(R.id.button);




        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigInteger Bet_money = null;
                Bet_money = new BigInteger(editText.getText().toString());
                RemoteCall<TransactionReceipt> remoteCall = MainActivity.contract.betting(BigInteger.valueOf(0),Bet_money,BigInteger.valueOf(1),Bet_money);
                try {
                    TransactionReceipt receipt = remoteCall.sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                RemoteCall<BigInteger> Total_money_r;
                String total_money_s = null;
                Total_money_r = MainActivity.contract.getMyTotalBettingMoney();
                try {
                    total_money_s = "" +Total_money_r.sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                total.setText(total_money_s);
            }
        });



        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}