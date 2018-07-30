package kr.co.keypair.votingsystem.fragmentation.my_betting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_betting1;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_betting2;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_country;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_setting;
import kr.co.keypair.votingsystem.R;

public class betting2 extends Fragment {

    private RecyclerAdapter_betting2 adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_betting2, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.betting_recycler2);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_betting2(getContext(),R.layout.item_betting2);
        dataset();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        return v;
    }

    public void dataset() {


    }
}
