package kr.co.keypair.votingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import kr.co.keypair.votingsystem.Login.LoginActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));

        finish();
    }
}
