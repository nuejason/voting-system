package kr.co.keypair.votingsystem.fragmentation.team_info;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_player;
import kr.co.keypair.votingsystem.DataBaseHelper;
import kr.co.keypair.votingsystem.R;

public class FRA extends Fragment {

    private RecyclerAdapter_player adapter;
    private RecyclerView recyclerView;
    private ImageView country_flag;
    private TextView country_name, fifa_code, league, age_avg, height_avg, price_total, price_avg, fifa_rank, coach, captain;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("프랑스");
        View v = inflater.inflate(R.layout.fragment_country_info, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.player_list);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_player(getContext(),R.layout.item_player);

        final DataBaseHelper mDbHelper= new DataBaseHelper(getContext());

        Cursor player = mDbHelper.Player("FRA");
        player.moveToFirst();
        for(int i=0;i< player.getCount();i++){
            adapter.add(player.getString(0),player.getString(1),player.getInt(2));
            player.moveToNext();
        }
        player.close();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        Cursor country = mDbHelper.Country("FRA");
        country.moveToFirst();

        country_flag = (ImageView)v.findViewById(R.id.country_flag);
        country_name = (TextView)v.findViewById(R.id.country);
        country_flag.setImageResource(R.drawable.fra);
        country_name.setText("프랑스");

        fifa_code = (TextView) v.findViewById(R.id.fifa_code);
        league = (TextView) v.findViewById(R.id.league);
        age_avg = (TextView) v.findViewById(R.id.age_avg);
        height_avg = (TextView) v.findViewById(R.id.height_avg);
        price_total = (TextView) v.findViewById(R.id.price_total);
        price_avg = (TextView) v.findViewById(R.id.price_avg);
        fifa_rank = (TextView) v.findViewById(R.id.fifa_ranking);
        coach = (TextView) v.findViewById(R.id.coach);
        captain = (TextView) v.findViewById(R.id.captain);

        fifa_code.setText(country.getString(2));
        league.setText(country.getString(3));
        int age = country.getInt(4);
        age_avg.setText(String.valueOf(age));
        int height = country.getInt(5);
        height_avg.setText(String.valueOf(height));
        price_total.setText(country.getString(6));
        price_avg.setText(country.getString(7));
        int rank = country.getInt(5);
        fifa_rank.setText(String.valueOf(rank));
        coach.setText(country.getString(9));
        captain.setText(country.getString(10));
        country.close();

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}

