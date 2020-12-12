package com.example.exincidencies.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

import com.example.exincidencies.Clases.MainActivity;
import com.example.exincidencies.R;

import DB.IncidenciaDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class Configuration extends Fragment {
    protected IncidenciaDBHelper dbHelper;
    protected SQLiteDatabase db;
    protected View opcionesView;
    protected Context context;
    protected String nivel_prioridad;
    protected static SharedPreferences sPreferences;

    public Configuration() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //shared prefrerences
        sPreferences = MainActivity.getInstance().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);

        //Creation of the dbHelper
        dbHelper = new IncidenciaDBHelper(MainActivity.getInstance());
        db = dbHelper.getWritableDatabase();
        // Inflate the layout for this fragment
        opcionesView = inflater.inflate(R.layout.fragment_configuracion, container, false);
        final Button btnToSpanish = opcionesView.findViewById(R.id.btnToSpanish);
        final Button btnToUk = opcionesView.findViewById(R.id.btnToUk);
        final Button btnToFrench = opcionesView.findViewById(R.id.btnToFrench);
        final Button btnToJapanese = opcionesView.findViewById(R.id.btnToJapanese);
        final Button btn_removePref = opcionesView.findViewById(R.id.btnRemovePref);

        btnToSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocaleLanguaje("es");
                Toast.makeText(MainActivity.getInstance(), R.string.toastCambiar, Toast.LENGTH_SHORT).show();
            }
        });
        btnToUk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocaleLanguaje("en");
                Toast.makeText(MainActivity.getInstance(), R.string.toastCambiar, Toast.LENGTH_SHORT).show();
            }
        });
        btnToJapanese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocaleLanguaje("ja");
                Toast.makeText(MainActivity.getInstance(), R.string.toastCambiar, Toast.LENGTH_SHORT).show();
            }
        });
        btnToFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocaleLanguaje("fr");
                Toast.makeText(MainActivity.getInstance(), R.string.toastCambiar, Toast.LENGTH_SHORT).show();
            }
        });
        btn_removePref.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sPreferences.edit().remove("Language").commit();
                sPreferences.edit().remove("User_Stored").commit();
                sPreferences.edit().remove("User_Name").commit();
                sPreferences.edit().remove("User_Psw").commit();
                refresh();
            }
        });
        return opcionesView;
    }

    public void setLocaleLanguaje(String localeCode) {
        Locale locale = new Locale(localeCode);
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = locale;
        sPreferences.edit().putString("Language", localeCode).commit();
        MainActivity.getInstance().getResources().updateConfiguration(config, MainActivity.getInstance().getResources().getDisplayMetrics());
        refresh();
    }
    public void refresh (){
        Intent i = (getActivity().getIntent());
        startActivity(i);
        Fragment currentFragment = new Configuration();
        FragmentManager menuManager = getFragmentManager();
        FragmentTransaction menuTransaction = menuManager.beginTransaction();
        menuTransaction.replace(R.id.fragmentID,currentFragment);
        menuTransaction.commit();
    }
}
