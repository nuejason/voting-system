package kr.co.keypair.votingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db ;
    private ContentValues cv = null;
    private String NUM = "num";
    private String MEMNAME = "name";
    private String MEMBERID = "memid";
    private String MEMPWD = "pwd";

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MEMBER (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, id TEXT, pwd TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String name, String id, String pwd) {
        db = getWritableDatabase();
        Log.d("insert",name);
        db.execSQL("INSERT INTO MEMBER VALUES(null, '" + name + "', '" + id + "', '" + pwd + "');");
        db.close();
    }

    public void update(String id, int pwd) {
        db = getWritableDatabase();
        db.execSQL("UPDATE MEMBER SET pwd='" + pwd + "' WHERE id='" + id + "';");
        db.close();
    }

    public void delete(String id) {
        db = getWritableDatabase();
        db.execSQL("DELETE FROM MEMBER WHERE id='" + id + "';");
        db.close();
    }

    public Boolean confirm(String id) {
        db = getReadableDatabase();
        Boolean result = false;
        if(id==null) return result;

        Cursor cursor = db.rawQuery("select id from MEMBER where id='"+id.trim()+"'", null);

        if(cursor.getCount()==0){
            result=true;
        }
        return result;
    }

    public Boolean login(String id, String pwd) {

        db = getReadableDatabase();
        Boolean result = false;

        if(id==null||pwd==null) return result;

        Cursor cursor = db.rawQuery("select id,pwd from MEMBER where id='"+id.trim()+"'and pwd='"+pwd.trim()+"'", null);
        if(cursor.getCount()==1){
            result=true;
        }
        return result;
    }
}

