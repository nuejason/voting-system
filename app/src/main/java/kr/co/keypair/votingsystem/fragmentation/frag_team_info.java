package kr.co.keypair.votingsystem.fragmentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.keypair.votingsystem.R;

public class frag_team_info extends Fragment {

    public frag_team_info() {
        // Required empty public constructor
    }

    public static frag_team_info newInstance(String param1, String param2) {
        frag_team_info fragment = new frag_team_info();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("팀 정보");
        return inflater.inflate(R.layout.fragment_frag_team_info, container, false);
    }


}
