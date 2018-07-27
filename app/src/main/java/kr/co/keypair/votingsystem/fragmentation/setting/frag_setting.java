package kr.co.keypair.votingsystem.fragmentation.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kr.co.keypair.votingsystem.Adapter.Item;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter;
import kr.co.keypair.votingsystem.R;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class frag_setting extends Fragment{

    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("설정");
        View v = inflater.inflate(R.layout.fragment_frag_setting, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_setting);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter(getContext(),R.layout.item);
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

    public void dataset() {
        adapter.add(R.drawable.ic_notice, "  공지사항");
        adapter.add(R.drawable.ic_version, "  버전정보");
        adapter.add(R.drawable.ic_team, "  My team");
    }
}


