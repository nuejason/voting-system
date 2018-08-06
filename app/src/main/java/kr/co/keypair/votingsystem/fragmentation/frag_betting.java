package kr.co.keypair.votingsystem.fragmentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Betting;
import kr.co.keypair.votingsystem.MainActivity;
import kr.co.keypair.votingsystem.R;

public class frag_betting extends Fragment {

    View v;
    private String msPrikey, total,my_money;
    final BigInteger gasPrice = new BigInteger("5000000000"); // in Wei
    final BigInteger gasLimit = new BigInteger("200000");
    private RadioButton country1_name, country2_name;
    private Button betting;
    private ImageView country1_image, country2_image;
    private TextView text_total_money;
    private EditText edit_my_money;
    private RemoteCall<BigInteger> total_money;
    private BigInteger flag = null;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // this is test code from kyu

        getActivity().setTitle("배팅하기");
        v = inflater.inflate(R.layout.fragment_frag_betting, container, false);
        Bundle extra = getArguments();
        int image1 = extra.getInt("image1");
        int image2 = extra.getInt("image2");
        String country1 = extra.getString("country1");
        String country2 = extra.getString("country2");
        final int game_id = extra.getInt("game_id");

        country1_image = (ImageView)v.findViewById(R.id.image1);
        country2_image = (ImageView)v.findViewById(R.id.image2);

        country1_image.setImageResource(image1);
        country2_image.setImageResource(image2);

        country1_name = (RadioButton) v.findViewById(R.id.country1);
        country2_name = (RadioButton) v.findViewById(R.id.country2);

        country1_name.setText(country1);
        country2_name.setText(country2);

        text_total_money = (TextView)v.findViewById(R.id.current_money);


        total_money = MainActivity.contract.getBettingMoneyByGameid(BigInteger.valueOf(game_id));

        try {
            total = "" + total_money.sendAsync().get() + "  WEI";

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        text_total_money.setText(total);

        betting = (Button)v.findViewById(R.id.button);
        betting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_my_money = (EditText)v.findViewById(R.id.money);
                BigInteger Bet_money = null;
                Bet_money = new BigInteger(edit_my_money.getText().toString());
               //button클릭 되면 선택되도록 고치기
                RadioGroup rg = (RadioGroup)v.findViewById(R.id.radioGroup1);
                RadioButton country1 = (RadioButton)v.findViewById(R.id.country1);
                RadioButton country2 = (RadioButton)v.findViewById(R.id.country2);

                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch(checkedId){
                            case R.id.country1 : flag = BigInteger.valueOf(1);
                            case R.id.country2 : flag = BigInteger.valueOf(2);
                        }
                    }
                });

                //나중에 고치기
                RemoteCall<TransactionReceipt> remoteCall = MainActivity.contract.betting(BigInteger.valueOf(0),Bet_money,flag,Bet_money);

                try {
                    TransactionReceipt receipt = remoteCall.sendAsync().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getContext(),"배팅되었습니다",Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(frag_betting.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
