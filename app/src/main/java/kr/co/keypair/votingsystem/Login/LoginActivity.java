package kr.co.keypair.votingsystem.Login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kr.co.keypair.votingsystem.DBHelper;
import kr.co.keypair.votingsystem.MainActivity;
import kr.co.keypair.votingsystem.R;

public class LoginActivity extends AppCompatActivity {

    private LoginActivity.MyAlertDialog alert= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "MEMBER.db", null, 1);
        alert = new LoginActivity.MyAlertDialog(this);

        final EditText  edit_id = (EditText)findViewById(R.id.login_id);
        final EditText edit_pwd = (EditText)findViewById(R.id.login_pwd);
        Button login = (Button)findViewById(R.id.login_btn);
        Button sign_up = (Button)findViewById(R.id.signup_btn);

        //여기 다시봐야함
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edit_id.getText().toString();
                String pwd = edit_pwd.getText().toString();
                String new_pwd = SHA256(pwd);

                if(dbHelper.login(id,new_pwd)) {
                    Intent registerIntent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(registerIntent);
                }
                else alert.setMessage("로그인 정보를 다시 확인해주세요.");;
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });

    }

    class MyAlertDialog extends AlertDialog.Builder {
        MyAlertDialog(Context ctx) {
            super(ctx);
            this.setNeutralButton("확인", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //기본적으로 창이 닫히고, 추가 작업은 없다.
                }
            });
        }

        @Override
        public AlertDialog.Builder setMessage(CharSequence msg) {
            super.setMessage(msg);
            this.show();
            return this;
        }
    }

    private String SHA256(String str){

        String SHA = "";

        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-256");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();

            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }

            SHA = sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            SHA = null;
        }

        return SHA;

    }

}
