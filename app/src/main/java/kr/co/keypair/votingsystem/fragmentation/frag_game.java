package kr.co.keypair.votingsystem.fragmentation;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import android.widget.Toast;

import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_game;
import kr.co.keypair.votingsystem.DataBaseHelper;
import kr.co.keypair.votingsystem.R;

public class frag_game extends Fragment {

    private RecyclerAdapter_game adapter;
    private RecyclerView recyclerView;
    private CalendarView calendarView;
    private String hometeam, awayteam, time,home, away;
    private int id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("경기 일정");
        final View v = inflater.inflate(R.layout.fragment_frag_game, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_game);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_game(getContext(),R.layout.item_game);

        calendarView = (CalendarView)v.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String Month,Day,Date;

                adapter.clearData();

                month = month+1;
                if((month/10)<1) Month = "0" + month;
                else Month = String.valueOf(month);
                if((dayOfMonth/10)<1) Day = "0" + dayOfMonth;
                else Day = String.valueOf(dayOfMonth);
                Date = String.valueOf(year) + "/" + Month +"/" + Day ;

                final DataBaseHelper mDbHelper= new DataBaseHelper(getContext());

                Cursor game = mDbHelper.Game(Date);
                game.moveToFirst();

                if(game.getCount() == 0){
                    Toast.makeText(getContext(),"경기가 없습니다",Toast.LENGTH_SHORT).show();
                }
                else {

                    Cursor country_name;

                    for(int i=0;i<game.getCount();i++) {
                        id = game.getInt(0);
                        time = game.getString(2);
                        hometeam = game.getString(3);
                        awayteam = game.getString(4);

                        int id1 = getContext().getResources().getIdentifier(hometeam.toLowerCase(), "drawable", getContext().getPackageName());
                        int id2 = getContext().getResources().getIdentifier(awayteam.toLowerCase(), "drawable", getContext().getPackageName());

                        country_name = mDbHelper.Country(hometeam);
                        country_name.moveToFirst();
                        home = country_name.getString(1);
                        country_name.close();

                        country_name = mDbHelper.Country(awayteam);
                        country_name.moveToFirst();
                        away = country_name.getString(1);
                        country_name.close();

                        adapter.add(id, id1,id2, home, away, time, hometeam, awayteam);
                        game.moveToNext();

                    }
                }
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        });


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}


