package kr.co.keypair.votingsystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
    private static String TAG = "DataBaseHelper"; //Logcat에 출력할 태그이름
    private static String DB_PATH = "/data/data/kr.co.keypair.votingsystem/databases/";
    private static String DB_NAME ="database.db"; // 데이터베이스 이름
    private final Context mContext;
    SQLiteDatabase db;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDataBase() throws IOException
    {
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            try {
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            }
            catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }


    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public Cursor Country(String country) {
        db = getReadableDatabase();
        Cursor country_info = db.rawQuery("select * from TEAM_INFO where FIFA_CODE = "+"'"+country+"'",null);
        return country_info;
    }

    public Cursor Player(String country) {
        db = getReadableDatabase();
        Cursor player_info = db.rawQuery("select * from " +"'"+country+ "'" + "order by NAME", null);
        return player_info;
    }

    public Cursor Game(String date){
        db = getReadableDatabase();
        Cursor game_date = db.rawQuery("select * from GAME_INFO where DATE = "+"'"+date+"'", null);

        return game_date;
    }

    public Cursor Game_info(int game_id){
        db= getReadableDatabase();
        Cursor game_info = db.rawQuery("select * from GAME_INFO where GAMEID = "+"'"+game_id+"'", null);

        return game_info;
    }



}
