package kr.co.keypair.votingsystem.fragmentation;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import kr.co.keypair.votingsystem.Adapter.RecyclerAdapter_game;
import kr.co.keypair.votingsystem.DataBaseHelper;
import kr.co.keypair.votingsystem.R;


public class frag_tdy_bet extends Fragment {

    private RecyclerAdapter_game adapter;
    private RecyclerView recyclerView;
    private CalendarView calendarView;
    private String hometeam, awayteam, time,home, away;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("오늘의 경기");
        View v = inflater.inflate(R.layout.fragment_frag_tdy_bet, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_tdy_bet);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_game(getContext(),R.layout.item_game);

        adapter.clearData();

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.KOREA);
        String Date = df.format(new Date());

        final DataBaseHelper mDbHelper= new DataBaseHelper(getContext());

        Cursor game = mDbHelper.Game(Date);
        game.moveToFirst();

        Cursor country_name;

        if(game.getCount() == 0){
            // 다이얼로그 바디
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(getActivity());
            // 다이얼로그 메세지
            alertdialog.setMessage("오늘 경기가 없습니다. ");

            // 확인버튼
            alertdialog.setPositiveButton("경기 일정", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
                    Fragment game_frag = new frag_game();
                    transaction.replace(R.id.content,game_frag);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
            alertdialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            // 메인 다이얼로그 생성
            AlertDialog alert = alertdialog.create();
            // 다이얼로그 보기
            alert.show();

        }
        else {
            for (int i = 0; i < game.getCount(); i++) {
                time = game.getString(3);
                hometeam = game.getString(4);
                awayteam = game.getString(5);

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

                adapter.add(id1, id2, home, away, time, hometeam, awayteam);
                game.moveToNext();
            }
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}


