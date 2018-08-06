package kr.co.keypair.votingsystem.Admin;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kenai.jffi.Main;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Betting;
import kr.co.keypair.votingsystem.MainActivity;
import kr.co.keypair.votingsystem.R;

public class add_game extends Fragment {
    private EditText editText_HomeTeam, editText_AwayTeam, editText_Date, editText_Time;
    private TextView textView_GameId;
    private Button button;
    public BigInteger[] GameIDs;        //이미 추가된 id
    public RemoteCall<BigInteger> numGameIDs;
    public BigInteger numGameIDs_B = null;
    public String GameID_s;
    View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_add_game, container, false);
        button = v.findViewById(R.id.button1);

        textView_GameId = v.findViewById(R.id.textView17);
        editText_HomeTeam = v.findViewById(R.id.editText2);
        editText_AwayTeam = v.findViewById(R.id.editText3);
        editText_Date = v.findViewById(R.id.editText4);
        editText_Time = v.findViewById(R.id.editText5);

        numGameIDs = MainActivity.contract.getGameIDsLen();
        numGameIDs_B = null;
        try {
            numGameIDs_B = numGameIDs.sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        GameID_s = "" + numGameIDs_B;
        textView_GameId.setText(GameID_s);

//        for (BigInteger bi = BigInteger.valueOf(0);
//             bi.compareTo(numGameIDs_B) < 0;
//             bi = bi.add(BigInteger.ONE)) {
//            RemoteCall<BigInteger> x = MainActivity.contract.getGameIDsByint(bi);
//            try {
//                BigInteger x_b = x.sendAsync().get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }


        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HomeTeam_s = editText_HomeTeam.getText().toString();
                String AwayTeam_s = editText_AwayTeam.getText().toString();
                String Date_s = editText_Date.getText().toString();
                String Time_s = editText_Time.getText().toString();
                String D_T_S = Date_s + Time_s;
                numGameIDs = MainActivity.contract.getGameIDsLen();
                try {
                    numGameIDs_B = numGameIDs.sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                GameID_s = "" + numGameIDs_B;
                textView_GameId.setText(GameID_s);
                RemoteCall<TransactionReceipt> remoteCall = MainActivity.contract.addGame(numGameIDs_B,HomeTeam_s,AwayTeam_s,D_T_S);
                try {
                    TransactionReceipt receipt = remoteCall.sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //현재 게임 수 리턴
                Toast.makeText(getContext(), "게임 추가 완료", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

}
