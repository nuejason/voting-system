package kr.co.keypair.votingsystem.Login;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import kr.co.keypair.votingsystem.DBHelper;
import kr.co.keypair.votingsystem.R;

public class RegisterActivity extends AppCompatActivity {

    private MyAlertDialog alert= null;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "MEMBER.db", null, 1);
        alert = new MyAlertDialog(this);

        final EditText edit_name = (EditText)findViewById(R.id.reg_name);
        final EditText edit_id = (EditText)findViewById(R.id.reg_id);
        final EditText edit_pwd1 = (EditText)findViewById(R.id.reg_pwd1);
        final EditText edit_pwd2 = (EditText)findViewById(R.id.reg_pwd2);
        Button id_cnf = (Button)findViewById(R.id.reg_cnf);
        Button sign_up = (Button)findViewById(R.id.reg_sign_btn);


        //변경해야함
        id_cnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edit_id.getText().toString();

                if(id==null)alert.setMessage("ID를 입력해주세요.");
                else if(!dbHelper.confirm(id)) {
                    alert.setMessage("중복된 ID가 있습니다!\n다른 ID를 사용해주세요.");
                }
                else {
                    alert.setMessage("사용하셔도 됩니다!");
                    flag = true;
                }
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_name.getText().toString();
                String id = edit_id.getText().toString();
                String pwd1 = edit_pwd1.getText().toString();
                String pwd2 = edit_pwd2.getText().toString();

                if(flag==false) alert.setMessage("중복 확인을 해주세요.");
                else if(pwd1==pwd2) alert.setMessage("비밀번호가 일치하지않습니다.");
                else {
                    String new_pwd = SHA256(pwd1);
                    dbHelper.insert(name, id, new_pwd);
                    alert.setMessage("회원가입 되었습니다!");
                    Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(registerIntent);
                }
            }
        });
    }
    class MyAlertDialog extends AlertDialog.Builder {
        MyAlertDialog(Context ctx) {
            super(ctx);
            this.setNeutralButton("확인", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
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
