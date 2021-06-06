package com.example.uts_akb_10118325_notes;
// Tanggal Pengerjaan = 6/6/2021
// NIM = 10118325
// NAMA = WISNU MURFADILAH ROKHSAN
// KELAS = IF 8 2018
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditNoteActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1, btn2;
    EditText text1,text2,text3;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editText1);
        text2 = (EditText) findViewById(R.id.editText2);
        text3 = (EditText) findViewById(R.id.editText3);
        id = getIntent().getStringExtra("id");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM note WHERE id = '" +
                id+"'",null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(3).toString());
        }

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                db.execSQL("UPDATE note SET title='"+
                        text1.getText().toString() +"', category ='"+
                        text2.getText().toString() +"', desc ='"+
                        text3.getText().toString() +"', date = '"+
                        date +"' where id='"+ id +"'");
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                NoteFragment.ma.RefreshList();
                finish();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                finish();
            }
        });
    }
}