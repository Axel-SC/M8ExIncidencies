package com.example.exincidencies.Clases;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exincidencies.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import DB.Incidence;
import DB.IncidenciaDBHelper;

public class MainActivity extends AppCompatActivity{
    protected ArrayList<Incidence> arrayIncidences;
    public IncidenciaDBHelper dbHelper;
    public SQLiteDatabase db;
    SharedPreferences sPreferences;
    private static MainActivity myContext;

    public MainActivity(){
        myContext = this;
    }
    public static MainActivity getInstance(){
        return myContext;
    }
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //Creation of dbHelper and database
        dbHelper = new IncidenciaDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();

        //Creation of the array with all the incidences
        arrayIncidences = new ArrayList<Incidence>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("arrayIncidencies", arrayIncidences);

        //User logged
        sPreferences = this.getSharedPreferences("App_Settings", Context.MODE_PRIVATE);
        String username = sPreferences.getString("User_Name", "");

        //Custom Snackbar
        customSnackBar(this.findViewById(android.R.id.content),(getString(R.string.UserLogged)+" "+username), Snackbar.LENGTH_SHORT);
        /*
        Snackbar sbLogged = Snackbar.make(this.findViewById(android.R.id.content), (getString(R.string.UserLogged)+" "+username), Snackbar.LENGTH_SHORT);
        View snackView = sbLogged.getView();
        TextView tv = snackView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(Color.parseColor("#191970"));
        tv.setTextSize(18);
        sbLogged.getView().setBackgroundColor(Color.parseColor("#00CFBC"));
        sbLogged.show();*/

    }
    public static void customSnackBar(View view, String text, int duration) {
        Snackbar sbLogged = Snackbar.make(MainActivity.getInstance().findViewById(android.R.id.content),text, duration);
        View snackView = sbLogged.getView();
        TextView tv = snackView.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setTextColor(Color.parseColor("#191970"));
        tv.setTextSize(18);
        sbLogged.getView().setBackgroundColor(Color.parseColor("#00CFBC"));
        sbLogged.show();
    }
}
