package kr.co.keypair.votingsystem.fragmentation;

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
import kr.co.keypair.votingsystem.Adapter.Item_game;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter3;
import kr.co.keypair.votingsystem.R;

public class frag_game extends Fragment {

    private RecyclerAdapter3 adapter;
    private RecyclerView recyclerView;
    private ArrayList<Item_game> items = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("경기 일정");
        View v = inflater.inflate(R.layout.fragment_frag_game, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_game);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter3(getContext(),items);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //db에서 정보를 가져와다가 나라에 맞는 정보를 넣어주어야함
        //달력에서 날짜를 받고 날짜를 이용해 경기 디비에서 나라 정보 받는다
        //items.add(new Item_plan(R.drawable.ic_notice,"  공지사항"));

    }

}


