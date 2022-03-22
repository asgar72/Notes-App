package com.asgar.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FirstPage extends AppCompatActivity {

    FloatingActionButton btnadd;
    RecyclerView rec1;
    ArrayList<String> title,txttime,txnote;
    MyAdapter adp;
    MySQLhelper ob;
    SQLiteDatabase db;
    void LoadRecord()
    {
        db=ob.getReadableDatabase();
        Cursor cr=db.rawQuery("select * from notes",null);
        title=new ArrayList<String>();
        txttime=new ArrayList<String>();
        txnote=new ArrayList<String>();
        while(cr.moveToNext())
        {
            title.add(cr.getString(0));
            txttime.add(cr.getString(1));
            txnote.add(cr.getString(2));
        }
        adp=new MyAdapter(this.title,txttime,txnote);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        rec1.setLayoutManager(layoutManager);
        rec1.setAdapter(adp);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        rec1=(RecyclerView) findViewById(R.id.rec1);
        ob=new com.asgar.notes.MySQLhelper(this);
        LoadRecord();

        btnadd= findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Intent ob=new Intent(getApplicationContext(),Secondpage.class);
                    startActivity(ob);
                }
            }
        });
    }
}