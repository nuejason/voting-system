package kr.co.keypair.votingsystem.fragmentation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kr.co.keypair.votingsystem.R;

public class frag_my_acct extends Fragment {

    public static frag_my_acct newInstance(String param1, String param2) {
        frag_my_acct fragment = new frag_my_acct();
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
        getActivity().setTitle("내 계좌");
        return inflater.inflate(R.layout.fragment_frag_my_acct, container, false);
    }

}
