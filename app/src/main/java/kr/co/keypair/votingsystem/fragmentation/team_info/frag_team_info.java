package kr.co.keypair.votingsystem.fragmentation.team_info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.keypair.votingsystem.R;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_country;

public class frag_team_info extends Fragment {

    private RecyclerAdapter_country adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("팀 정보");
        View v = inflater.inflate(R.layout.fragment_frag_team_info, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_team_info);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_country(getContext(),R.layout.item_team);
        dataset();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        return v;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void dataset(){
        adapter.add(R.drawable.den,"덴마크");
        adapter.add(R.drawable.rus,"러시아");
        adapter.add(R.drawable.mex,"멕시코");
        adapter.add(R.drawable.bel,"벨기에");
        adapter.add(R.drawable.bra,"브라질");
        adapter.add(R.drawable.swe,"스웨덴");
        adapter.add(R.drawable.sui,"스위스");
        adapter.add(R.drawable.esp,"스페인");
        adapter.add(R.drawable.arg,"아르헨티나");
        adapter.add(R.drawable.uru,"우루과이");
        adapter.add(R.drawable.jpn,"일본");
        adapter.add(R.drawable.eng,"잉글랜드");
        adapter.add(R.drawable.col,"콜롬비아");
        adapter.add(R.drawable.cro,"크로아티아");
        adapter.add(R.drawable.por,"포르투갈");
        adapter.add(R.drawable.fra,"프랑스");

    }

}
