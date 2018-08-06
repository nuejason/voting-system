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
import org.web3j.tuples.generated.Tuple5;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_betting1;
import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_betting2;
import kr.co.keypair.votingsystem.DataBaseHelper;
import kr.co.keypair.votingsystem.MainActivity;
import kr.co.keypair.votingsystem.R;

public class betting2 extends Fragment {

    private RecyclerAdapter_betting2 adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_betting2, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.betting_recycler2);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_betting2(getContext(),R.layout.item_betting2);
        try {
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
        String date, time, hometeam, awayteam, my_country, home ,away, my_money, result, result_money;
        BigInteger game_id, money, country,resmoney= null;
        BigInteger homegoals, awaygoals, homepenalty, awaypenalty, gameresult;
        BigInteger lens = null;
        BigInteger total_money = null;
        final DataBaseHelper mDbHelper= new DataBaseHelper(getContext());

        try {
            lens = MainActivity.contract.getBetByAddressLength().sendAsync().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int len = lens.intValue();
        for (int i=0;i<len;i++) {

            RemoteCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> tuples1 =
                    MainActivity.contract.getBetByAddressInfo(BigInteger.valueOf(i));
            Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> tuple1 = tuples1.sendAsync().get();
            game_id = tuple1.getValue1();
            money = tuple1.getValue2();
            country = tuple1.getValue3();
            resmoney = tuple1.getValue4();

            RemoteCall<Tuple5<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger>> tuples2 =
                    MainActivity.contract.getGameInfo(game_id);
            Tuple5<BigInteger,BigInteger, BigInteger, BigInteger, BigInteger> tuple2 = tuples2.sendAsync().get();
            homegoals =tuple2.getValue1();
            awaygoals =tuple2.getValue2();
            homepenalty=tuple2.getValue3();
            awaypenalty=tuple2.getValue4();
            gameresult=tuple2.getValue5();

            if(!resmoney.equals(BigInteger.ZERO)){

                total_money =  MainActivity.contract.getBettingMoneyByGameid(game_id).sendAsync().get();
                total = "" + total_money + "  WEI";

                Cursor game = mDbHelper.Game_info(game_id.intValue());
                game.moveToFirst();
                date = game.getString(1);
                time = game.getString(2);
                hometeam = game.getString(3);
                awayteam = game.getString(4);
                game.close();

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

                if (country.equals(BigInteger.ONE)) my_country = home;
                else my_country = away;

                my_money = "" + money + "  WEI";

                if(gameresult.equals(BigInteger.ONE)){
                    if(homegoals != awaygoals){
                            result = "" +home + " 우승 ("+ homegoals + " : " + awaygoals + ")";
                    }
                    else result = "" +home + " 우승 ("+ homegoals + "("+homepenalty+")"+" : "
                            + awaygoals + "(" + awaypenalty + ")";

                }else {
                    if(homegoals != awaygoals){
                        result = "" +away + " 우승 ("+ homegoals + " : " + awaygoals + ")";
                    }
                    else result = "" +away + " 우승 ("+ homegoals + "("+homepenalty+")"+" : "
                            + awaygoals + "(" + awaypenalty + ")";
                }

                if(gameresult == country){
                    result_money = "+" + resmoney;
                }
                else result_money = "-" + money;

                adapter.add(id1, id2, home, away, date, time, my_country, total, my_money, result, result_money);
            }
        }

    }

}
