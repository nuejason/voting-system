package kr.co.keypair.votingsystem.fragmentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kr.co.keypair.votingsystem.R;

public class frag_game extends Fragment {

    public frag_game() {
        // Required empty public constructor
    }

    public static frag_game newInstance() {
        frag_game fragment = new frag_game();
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

        getActivity().setTitle("경기 일정");

        return inflater.inflate(R.layout.fragment_frag_game, container, false);
    }

}
