package kr.co.keypair.votingsystem.fragmentation.my_betting;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kenai.jffi.Main;

import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_betting1;
import kr.co.keypair.votingsystem.DataBaseHelper;
import kr.co.keypair.votingsystem.MainActivity;
import kr.co.keypair.votingsystem.R;

public class betting1 extends Fragment {

    private RecyclerAdapter_betting1 adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_betting1, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.betting_recycler1);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_betting1(getContext(),R.layout.item_betting1);
        try {
            Log.d("abcd","d");
            dataset();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        return v;
    }

    public void dataset() throws ExecutionException, InterruptedException {

        String total;
        String date, time, hometeam, awayteam, result, home ,away, my_money;
        BigInteger game_id, money, country,resmoney= null;
        BigInteger lens = null;
        BigInteger total_money = null;
        final DataBaseHelper mDbHelper= new DataBaseHelper(getContext());

        try {
            lens = MainActivity.contract.getBetByAddressLength().sendAsync().get();
            Log.d("abcd",String.valueOf(lens));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int len = lens.intValue();
        for (int i=0;i<len;i++) {

            RemoteCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> tuples =
                    MainActivity.contract.getBetByAddressInfo(BigInteger.valueOf(i));
            Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> tuple = tuples.sendAsync().get();
            game_id = tuple.getValue1();
            money = tuple.getValue2();
            country = tuple.getValue3();
            resmoney = tuple.getValue4();

            Log.d("abcd",String.valueOf(resmoney));
            if(resmoney.equals(BigInteger.ZERO)){
                total_money =  MainActivity.contract.getBettingMoneyByGameid(game_id).sendAsync().get();
                total = "" + total_money + "  WEI";

                Cursor game = mDbHelper.Game_info(game_id.intValue());
                game.moveToFirst();
                date = game.getString(1);
                time = game.getString(2);
                hometeam = game.getString(3);
                awayteam = game.getString(4);

                int id1 = getContext().getResources().getIdentifier(hometeam.toLowerCase(), "drawable", getContext().getPackageName());
                int id2 = getContext().getResources().getIdentifier(awayteam.toLowerCase(), "drawable", getContext().getPackageName());

                Cursor country_name = mDbHelper.Country(hometeam);
                country_name.moveToFirst();
                home = country_name.getString(1);
                country_name.close();

                country_name = mDbHelper.Country(awayteam);
                country_name.moveToFirst();
                away = country_name.getString(1);
                country_name.close();

                if (country == BigInteger.ONE) result = home;
                else result = away;

                my_money = "" + money + "  WEI";

                adapter.add(id1, id2, home, away, date, time,result, total, my_money);
            }
        }

    }

}
