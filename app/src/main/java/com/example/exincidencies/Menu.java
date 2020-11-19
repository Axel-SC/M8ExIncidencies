package com.example.exincidencies;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.exincidencies.AddIncidencia.*;

import DB.IncidenciaDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Menu extends Fragment {
    protected IncidenciaDBHelper dbHelper;
    protected SQLiteDatabase db;

    private final int[] BTNMENU = {R.id.Añadir, R.id.Listar, R.id.eliminar};

    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View menu = inflater.inflate(R.layout.fragment_menu, container, false);

        final Button btnAñadir = menu.findViewById(R.id.Añadir);
        btnAñadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment fragmentAddIncidencia = new AddIncidencia();
                menuTransaction.replace(R.id.fragmentID, fragmentAddIncidencia );

                menuTransaction.commit();
            }
        });

        final Button btnListar = menu.findViewById(R.id.Listar);
        btnListar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment fragmentList = new ListIncidencia();
                menuTransaction.replace(R.id.fragmentID, fragmentList);

                menuTransaction.commit();
            }
        });

        final Button btnEliminar = menu.findViewById(R.id.eliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dbHelper = ((MainActivity)getActivity()).dbHelper;
                db = ((MainActivity)getActivity()).db;
                dbHelper.deleteEntries(db);

                int numIncidencies = ((MainActivity)getActivity()).arrayIncidencies.size();
                Toast toast = Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), String.valueOf(numIncidencies), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return menu;
    }
}
