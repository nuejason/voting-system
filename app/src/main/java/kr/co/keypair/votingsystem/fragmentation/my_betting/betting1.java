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

        msPrikey = "b9d45277dca6b27efccb6cf8497c6036a4ccb339bc6ae5ddc9bd6a2127e5cbc4";
        final String msContractAddr = "0x2a2a63fa747be16f3493690adf7213bfd551f729";
        final Credentials credentials = Credentials.create(msPrikey);
        final Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/swGGKC97MU0pqiKuFUpA"));
        Betting contract = Betting.load(msContractAddr, web3, credentials, gasPrice, gasLimit);



        //adapter.add();

    }

}
