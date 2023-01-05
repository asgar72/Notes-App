package com.asgar.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Secondpage extends AppCompatActivity {

    Button btnsave;
    TextView txttime;
    EditText txtitle,txnote;
    SQLiteDatabase db;
    MySQLhelper ob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
//For current time show
        txttime=(TextView)findViewById(R.id.txttime);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM yyyy HH:mm a");
        String datetime = simpleDateFormat.format(calendar.getTime());
        txttime.setText(datetime);
//....//


        btnsave= findViewById(R.id.btnsave);
        txtitle= findViewById(R.id.txtitle);
        txnote= findViewById(R.id.txnote);
        txttime=findViewById(R.id.txttime);
        ob =new MySQLhelper(this);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txtitle.getText().toString();
                String time = txttime.getText().toString();
                String note = txnote.getText().toString();

                if (title.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter title", Toast.LENGTH_SHORT).show();
                    txtitle.requestFocus();
                }
                else if (note.equals(""))
                {


                   Toast.makeText(getApplicationContext(), "Please enter some text", Toast.LENGTH_SHORT).show();
                   txnote.requestFocus();
                }
                else
                {
                    db = ob.getWritableDatabase();
                    db.execSQL("insert into notes (Title,Time,Note)values('" + title + "','" + time + "','" + note + "')");
                    txtitle.setText("");
                    txttime.setText("");
                    txnote.setText("");
                    Toast.makeText(getApplicationContext(), "Your Notes is save", Toast.LENGTH_LONG).show();
                    Intent ob=new Intent(getApplicationContext(),FirstPage.class);
                    startActivity(ob);
                }
            }
        });
    }
}