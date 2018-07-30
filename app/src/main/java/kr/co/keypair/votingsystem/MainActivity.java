package kr.co.keypair.votingsystem;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.widget.TextView;


import java.io.IOException;

import kr.co.keypair.votingsystem.fragmentation.*;
import kr.co.keypair.votingsystem.fragmentation.my_betting.frag_my_bet;
import kr.co.keypair.votingsystem.fragmentation.setting.frag_setting;
import kr.co.keypair.votingsystem.fragmentation.team_info.frag_team_info;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private final String msContractAddr = "0x0101010101010101010101010101010101010101";

    private Fragment my_bet_frag;
    private Fragment game_frag;
    private Fragment my_acct_frag;
    private Fragment setting_frag;
    private Fragment tdy_bet_frag;
    private Fragment team_info_frag;
    private DataBaseHelper mDbHelper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final String msPrikey = getIntent().getStringExtra("pwd");
        final String msPrikey = "b9d45277dca6b27efccb6cf8497c6036a4ccb339bc6ae5ddc9bd6a2127e5cbc4";
        Credentials credentials = Credentials.create(msPrikey);
        Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/swGGKC97MU0pqiKuFUpA"));

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            // call your contract here
                            // http calls should be run on a different thread
                        } catch (Exception e) {
                            e.toString();
                        }
                    }
                }.start();
            }
        });


        try {
            mDbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        my_bet_frag = new frag_my_bet();
        transaction.replace(R.id.content, my_bet_frag);
        transaction.addToBackStack(null);
        transaction.commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton home = (FloatingActionButton) findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                my_bet_frag = new frag_my_bet();
                transaction.replace(R.id.content, my_bet_frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        //로그인한 유저의 닉네임
        String name = getIntent().getStringExtra("name");
        TextView text = (TextView)headerView.findViewById(R.id.drawer_name);
        text.append(name + "  님");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tdy_bet) {
            tdy_bet_frag = new frag_tdy_bet();
            transaction.replace(R.id.content, tdy_bet_frag);
        } else if (id == R.id.nav_game_plan) {
            game_frag = new frag_game();
            transaction.replace(R.id.content, game_frag);
        } else if (id == R.id.nav_team_info) {
            team_info_frag = new frag_team_info();
            transaction.replace(R.id.content, team_info_frag);
        } else if (id == R.id.nav_my_acnt) {
            my_acct_frag = new frag_my_acct();
            transaction.replace(R.id.content, my_acct_frag);
        } else if (id == R.id.nav_setting) {
            setting_frag = new frag_setting();
            transaction.replace(R.id.content, setting_frag);
        }

        transaction.addToBackStack(null);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
