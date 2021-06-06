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
import android.widget.TextView;

public class ViewNoteActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btn1;
    TextView text1,text2,text3,text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM note WHERE id = '" + getIntent().getStringExtra("id")+"';";
        cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(4).toString());
            text4.setText(cursor.getString(3).toString());
        }

        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}