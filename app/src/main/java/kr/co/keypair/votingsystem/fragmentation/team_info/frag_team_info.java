package kr.co.keypair.votingsystem.fragmentation.team_info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import kr.co.keypair.votingsystem.Adapter.Item;
import kr.co.keypair.votingsystem.R;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter2;


public class frag_team_info extends Fragment {

    private RecyclerAdapter2 adapter;
    private RecyclerView recyclerView;
    private ArrayList<Item> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("팀 정보");
        View v = inflater.inflate(R.layout.fragment_frag_team_info, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_team_info);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter2(getContext(),items);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return v;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items.add(new Item(R.drawable.denmark,"덴마크"));
        items.add(new Item(R.drawable.russia,"러시아"));
        items.add(new Item(R.drawable.mexico,"멕시코"));
        items.add(new Item(R.drawable.belgium,"벨기에"));
        items.add(new Item(R.drawable.brasil,"브라질"));
        items.add(new Item(R.drawable.sweden,"스웨덴"));
        items.add(new Item(R.drawable.swiss,"스위스"));
        items.add(new Item(R.drawable.spain,"스페인"));
        items.add(new Item(R.drawable.argentina,"아르헨티나"));
        items.add(new Item(R.drawable.uruguay,"우루과이"));
        items.add(new Item(R.drawable.japan,"일본"));
        items.add(new Item(R.drawable.england,"잉글랜드"));
        items.add(new Item(R.drawable.colombia,"콜롬비아"));
        items.add(new Item(R.drawable.croatia,"크로아티아"));
        items.add(new Item(R.drawable.portugal,"포르투갈"));
        items.add(new Item(R.drawable.france,"프랑스"));
    }

}
