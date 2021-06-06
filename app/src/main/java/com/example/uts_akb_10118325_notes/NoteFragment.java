package com.example.uts_akb_10118325_notes;
// Tanggal Pengerjaan = 6/6/2021
// NIM = 10118325
// NAMA = WISNU MURFADILAH ROKHSAN
// KELAS = IF 8 2018
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteFragment extends Fragment {
    String[] daftar,id;
    ListView ListView01;
    Button btn1;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbCenter;
    public static NoteFragment ma;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        ListView01 = view.findViewById(R.id.listView);
        btn1 = view.findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), CreateNoteActivity.class);
                startActivity(intent);
            }
        });
        ma = this;
        dbCenter = new DataHelper(view.getContext());
        RefreshList(view);
        return view;
    }
    public void RefreshList(View view){
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM note",null);
        daftar = new String[cursor.getCount()];
        id = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc< cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
            id[cc] = cursor.getString(0).toString();
        }

        ListView01.setAdapter(new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1,daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
                final String selection = id[arg2];
                final CharSequence[] dialogitem = {"View Note", "Edit Note","Delete Note"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getActivity().getApplicationContext(), ViewNoteActivity.class);
                                i.putExtra("id",selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getActivity().getApplicationContext(), EditNoteActivity.class);
                                in.putExtra("id",selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM note WHERE id ='"+selection+"'");
                                RefreshList(view);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }

    public void RefreshList(){
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM note",null);
        daftar = new String[cursor.getCount()];
        id = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc< cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
            id[cc] = cursor.getString(0).toString();
        }

        ListView01.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
                final String selection = id[arg2];
                final CharSequence[] dialogitem = {"View Note", "Edit Note","Delete Note"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getActivity().getApplicationContext(), ViewNoteActivity.class);
                                i.putExtra("id",selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent in = new Intent(getActivity().getApplicationContext(), EditNoteActivity.class);
                                in.putExtra("id",selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbCenter.getWritableDatabase();
                                db.execSQL("DELETE FROM note WHERE id ='"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }
}
