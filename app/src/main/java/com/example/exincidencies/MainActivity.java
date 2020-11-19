package com.example.exincidencies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

import DB.IncidenciaDBHelper;

public class MainActivity extends AppCompatActivity implements ComunicaMenu{
    protected ArrayList<Incidencia> arrayIncidencies;

    protected Fragment [] menuFragments;
    private Context mContext;
    public IncidenciaDBHelper dbHelper;
    public SQLiteDatabase db;

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos el dbHelper y la base de datos
        dbHelper = new IncidenciaDBHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        //Creamos el array donde estaran todas las incidencias listadas
        arrayIncidencies = new ArrayList<Incidencia>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("arrayIncidencies", arrayIncidencies);



    }

    @Override
    public void menu(int queBoton) {
    }
}
