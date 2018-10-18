package kr.co.keypair.votingsystem;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import kr.co.keypair.votingsystem.Admin.Admin;
import kr.co.keypair.votingsystem.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText edit_name = (EditText) findViewById(R.id.login_name);
                final EditText edit_pwd = (EditText) findViewById(R.id.login_pwd);
                final String name = edit_name.getText().toString();
                final String pwd = edit_pwd.getText().toString();

                //progress bar
                Dialog mprogress = new Dialog(LoginActivity.this, R.style.MyDialog);
                mprogress.setCancelable(true);
                mprogress.addContentView(new ProgressBar(LoginActivity.this),
                        new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                mprogress.show();

                Intent Intent = new Intent(LoginActivity.this, MainActivity.class);
                Intent.putExtra("name", name);
                Intent.putExtra("pwd", pwd);
                LoginActivity.this.startActivity(Intent);
            }
        });
    }

}
