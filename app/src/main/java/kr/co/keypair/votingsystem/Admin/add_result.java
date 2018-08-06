package kr.co.keypair.votingsystem.Admin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.MainActivity;
import kr.co.keypair.votingsystem.R;

public class add_result extends Fragment {

    private EditText editText_gameID, editText_HomeTeam_Score, editText_AwayTeam_Score;
    private EditText editText_HomeTeam_Score_Penalty, editText_AwayTeam_Score_Penalty;
    private Button button2, button3;
    private BigInteger gameID;

    View v;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_result, container, false);


        editText_gameID = v.findViewById(R.id.editText9);
        editText_HomeTeam_Score = v.findViewById(R.id.editText5);
        editText_AwayTeam_Score = v.findViewById(R.id.editText6);
        editText_HomeTeam_Score_Penalty = v.findViewById(R.id.editText7);
        editText_AwayTeam_Score_Penalty = v.findViewById(R.id.editText8);

        button2 = v.findViewById(R.id.button2);
        button3 = v.findViewById(R.id.button3);


        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameID = new BigInteger(editText_gameID.getText().toString());
                //gameID가 존재하는지 검사해야함
            }
        });

        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigInteger HomeTeam = new BigInteger(editText_HomeTeam_Score.getText().toString());
                BigInteger AwayTeam = new BigInteger(editText_AwayTeam_Score.getText().toString());
                BigInteger HomeTeam_Penalty = new BigInteger(editText_HomeTeam_Score_Penalty.getText().toString());
                BigInteger AwayTeam_Penalty = new BigInteger(editText_AwayTeam_Score_Penalty.getText().toString());

                RemoteCall<TransactionReceipt> remoteCall;
                if(HomeTeam == AwayTeam){
                    remoteCall = MainActivity.contract.ResultGame(gameID, HomeTeam, AwayTeam,HomeTeam_Penalty,AwayTeam_Penalty);
                }else{
                    remoteCall = MainActivity.contract.ResultGame(gameID, HomeTeam, AwayTeam,BigInteger.valueOf(0),BigInteger.valueOf(0));
                }

                try {
                    TransactionReceipt receipt = remoteCall.sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "결과 입력 완료", Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

}
