package kr.co.keypair.votingsystem.fragmentation.my_betting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_betting1;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_country;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_setting;
import kr.co.keypair.votingsystem.Betting;
import kr.co.keypair.votingsystem.R;

public class betting1 extends Fragment {

    private RecyclerAdapter_betting1 adapter;
    private RecyclerView recyclerView;
    private String msPrikey;
    final BigInteger gasPrice = new BigInteger("5000000000"); // in Wei
    final BigInteger gasLimit = new BigInteger("200000");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_betting1, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.betting_recycler1);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_betting1(getContext(),R.layout.item_betting1);
        dataset();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        return v;
    }

    public void dataset() {

        String total_money, money;

        ///contract를 통해서game id를 받아온다
        //BigInteger game_id = contract.

        //total_money =  MainActivity.contract.getBettingMoneyByGameid(BigInteger.valueOf(game_id));
        /*
        try {
            //total = "" + total_money.sendAsync().get() + "  WEI";

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */
        //adapter.add();

    }

}
