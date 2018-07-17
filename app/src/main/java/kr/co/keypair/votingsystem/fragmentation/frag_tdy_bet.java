package kr.co.keypair.votingsystem.fragmentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.co.keypair.votingsystem.R;

public class frag_tdy_bet extends Fragment {


    public frag_tdy_bet() {
        // Required empty public constructor
    }

    public static frag_tdy_bet newInstance(String param1, String param2) {
        frag_tdy_bet fragment = new frag_tdy_bet();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("오늘의 경기");

        return inflater.inflate(R.layout.fragment_frag_tdy_bet, container, false);
    }


}
