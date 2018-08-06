package kr.co.keypair.votingsystem.Admin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import kr.co.keypair.votingsystem.R;

public class add_game extends Fragment {

    View v;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v =inflater.inflate(R.layout.fragment_add_game, container, false);
        //EditText abc = (getActivity().findViewById(R.id.current_money);
     return v;
    }

}
