package kr.co.keypair.votingsystem.fragmentation;

<<<<<<< HEAD
<<<<<<< HEAD
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kr.co.keypair.votingsystem.R;
=======
        import android.content.Context;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import kr.co.keypair.votingsystem.R;
>>>>>>> bdb668845262a3cb1712ca4167fd9b43807c15fc
=======
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
>>>>>>> 5b327548b68bf0d9f3e333ad04e42fba0178b570

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Betting;
import kr.co.keypair.votingsystem.R;

import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

public class frag_my_acct extends Fragment{

    private String msPrikey;
    final BigInteger gasPrice = new BigInteger("5000000000"); // in Wei
    final BigInteger gasLimit = new BigInteger("200000");
    private TextView address,balance;
    private Spinner spinner;
    private RemoteCall<String> user_address;
    private String u_a_s;
    private String str_balance;
    private BigDecimal Default_num = new BigDecimal(1000);
    private ArrayList<String> list = new ArrayList<>();
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("내 계좌");
        v= inflater.inflate(R.layout.fragment_frag_my_acct, container, false);

        //msPrikey = "627c3cced38c0068f8ac17b989fc166551dd061400998585e80fd4ef6251be07";
        msPrikey = "b9d45277dca6b27efccb6cf8497c6036a4ccb339bc6ae5ddc9bd6a2127e5cbc4";
        final String msContractAddr = "0x2a2a63fa747be16f3493690adf7213bfd551f729";
        if (msPrikey ==null){
            Log.d("sd","asda");
        }
        final Credentials credentials = Credentials.create(msPrikey);

        final Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/swGGKC97MU0pqiKuFUpA"));

        Betting contract = Betting.load(msContractAddr, web3, credentials, gasPrice, gasLimit);

        user_address = contract.getAddress();

        try {
            //u_b_s = user_balance.sendAsync().get();
            u_a_s = user_address.sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        EthGetBalance ethGetBalance = null;

        try {
            ethGetBalance = web3.ethGetBalance(u_a_s,LATEST).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final BigInteger user_balance = ethGetBalance.getBalance();
        str_balance = "" + user_balance;

        address =(TextView)v.findViewById(R.id.address);
        balance = (TextView)v.findViewById(R.id.balance);
        BigDecimal BD = new BigDecimal(user_balance);
        address.setText(u_a_s);
        balance.setText(str_balance);

        list.add("Wei");
        list.add("Kwei");
        list.add("Ada");
        list.add("Femtoether");
        list.add("Gwei");
        list.add("Shannon");
        list.add("Nanoether");
        list.add("Nano");
        list.add("Szabo");
        list.add("Microether");
        list.add("Micro");
        list.add("Finney");
        list.add("Milliether");
        list.add("Milli");
        list.add("Ether");
        list.add("Kether");
        list.add("Kether");
        list.add("Grand");
        list.add("Einstein");
        list.add("Mether");
        list.add("Gether");
        list.add("Tether");

        //spinner와 비교하여 그에 따른 잔액 표시
        spinner = (Spinner)v.findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spinner.getSelectedItem().toString();
                if(text == "Kwei"){
                    //double user_balance_d = user_balance.doubleValue() / 1000;
                    //str_balance = "" + user_balance_d;
                    balance.setText("aaa");
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

