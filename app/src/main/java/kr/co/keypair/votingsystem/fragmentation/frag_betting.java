package kr.co.keypair.votingsystem.fragmentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Betting;
import kr.co.keypair.votingsystem.R;
import kr.co.keypair.votingsystem.fragmentation.my_betting.betting1;
import kr.co.keypair.votingsystem.fragmentation.my_betting.betting2;

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

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("배팅하기");
        v = inflater.inflate(R.layout.fragment_frag_betting, container, false);

        msPrikey = "627c3cced38c0068f8ac17b989fc166551dd061400998585e80fd4ef6251be07";
        final String msContractAddr = "0xd53cd226a573de91fa2897b004bb6b2a9d3117be";
        final Credentials credentials = Credentials.create(msPrikey);
        final Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/swGGKC97MU0pqiKuFUpA"));
        final Betting contract = Betting.load(msContractAddr, web3, credentials, gasPrice, gasLimit);

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

        contract.addGame(BigInteger.valueOf(0),"URU","POR","2018/06/30");
        total_money = contract.getBettingMoneyByGameid(BigInteger.valueOf(game_id));

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
                RemoteCall<TransactionReceipt> remoteCall = contract.betting(BigInteger.valueOf(0),Bet_money,BigInteger.valueOf(1),Bet_money);

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
