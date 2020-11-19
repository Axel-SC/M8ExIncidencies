package com.example.exincidencies;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import DB.IncidenciaDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddIncidencia extends Fragment{
    protected IncidenciaDBHelper dbHelper;
    protected SQLiteDatabase db;

    public AddIncidencia() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View addIncidencia = inflater.inflate(R.layout.fragment_add_incidencia, container, false);
        final Button btnafegirIncidencia = addIncidencia.findViewById(R.id.btnafegirIncidencia);
        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText txtIncidencia = addIncidencia.findViewById(R.id.txtincidencia);
                String txtIncidenciaForm = txtIncidencia.getText().toString();
                //Lo almaceno en la database, falta añadir el spinner
                Incidencia incidencia = new Incidencia(txtIncidenciaForm, "Alta");
                dbHelper = ((MainActivity)getActivity()).dbHelper;
                db = ((MainActivity)getActivity()).db;
                dbHelper.insertIncidencia(db, incidencia);
                /*Enseñar a Marta, no me deja usar el findView
                Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Incidencia Añadida",
                        Snackbar.LENGTH_SHORT).show();

                 */
            }
        });

        return addIncidencia;
    }

}
