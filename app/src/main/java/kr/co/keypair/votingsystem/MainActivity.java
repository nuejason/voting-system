package kr.co.keypair.votingsystem;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.widget.TextView;


import com.kenai.jffi.Main;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import kr.co.keypair.votingsystem.Admin.Admin;
import kr.co.keypair.votingsystem.fragmentation.*;
import kr.co.keypair.votingsystem.fragmentation.my_betting.frag_my_bet;
import kr.co.keypair.votingsystem.fragmentation.setting.frag_setting;
import kr.co.keypair.votingsystem.fragmentation.team_info.frag_team_info;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private final String msContractAddr = "0x253d74d9ece7ccdabc74129c27ec85b1f708b570";

    private Fragment my_bet_frag;
    private Fragment game_frag;
    private Fragment my_acct_frag;
    private Fragment setting_frag;
    private Fragment tdy_bet_frag;
    private Fragment team_info_frag;
    private DataBaseHelper mDbHelper = new DataBaseHelper(this);
    private RemoteCall<String> user_address;
    public static String u_a_s;
    public static Betting contract;
    public static Web3j web3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BigInteger gasPrice = new BigInteger("5000000000"); // in Wei
        final BigInteger gasLimit = new BigInteger("3000000");
        //final String msPrikey = getIntent().getStringExtra("pwd");
        final String msPrikey = "627c3cced38c0068f8ac17b989fc166551dd061400998585e80fd4ef6251be07";
        Credentials credentials = Credentials.create(msPrikey);
        web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/swGGKC97MU0pqiKuFUpA"));
        contract = Betting.load(msContractAddr, web3, credentials, gasPrice, gasLimit);
        user_address = contract.getAddress();

        String pwd = getIntent().getStringExtra("pwd");
        if(Integer.parseInt(pwd)==123){
            Intent Intent = new Intent(MainActivity.this, Admin.class);
            MainActivity.this.startActivity(Intent);
        }
        try {
            u_a_s = user_address.sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


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
