package com.asgar.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import java.util.Collections;

public class FirstPage extends AppCompatActivity {

    Button btnadd;
    RecyclerView rec1;
    ArrayList<String> title,txttime,txnote;
    MyAdapter adp;
    MySQLhelper ob;
    SQLiteDatabase db;

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
                    startActivity(new Intent(getApplicationContext(), Secondpage.class));
                    overridePendingTransition(0, 0);
                }
            }
        });
    }
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
        Collections.reverse(title);
        Collections.reverse(txttime);
        Collections.reverse(txnote);
        adp=new MyAdapter(this.title,txttime,txnote);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        rec1.setLayoutManager(layoutManager);
        rec1.setAdapter(adp);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}