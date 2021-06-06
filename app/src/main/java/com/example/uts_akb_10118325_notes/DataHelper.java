package com.example.uts_akb_10118325_notes;
// Tanggal Pengerjaan = 6/6/2021
// NIM = 10118325
// NAMA = WISNU MURFADILAH ROKHSAN
// KELAS = IF 8 2018
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "note.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE note(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT NULL, category TEXT NULL, desc TEXT NULL, date DATE NULL);";
        Log.d("Data","onCreate: "+ sql);
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0,int arg1,int arg2){

    }
}
