package com.example.exincidencies.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exincidencies.R;
import com.google.android.material.snackbar.Snackbar;

import com.example.exincidencies.Clases.MainActivity;
import DB.Incidence;
import DB.IncidenciaDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddIncidencia extends Fragment {
    protected IncidenciaDBHelper dbHelper;
    protected SQLiteDatabase db;
    protected View addIncidencia;
    protected Context context;
    protected String nivel_prioridad;

    public AddIncidencia() {
        // Required empty public constructor

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        addIncidencia = inflater.inflate(R.layout.fragment_add_incidencia, container, false);
        dbHelper = new IncidenciaDBHelper(MainActivity.getInstance());
        db = dbHelper.getWritableDatabase();

        Spinner spinner = addIncidencia.findViewById(R.id.spinner1);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.getInstance(),R.array.Prioridad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((adapter));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                nivel_prioridad=text;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final Button btnafegirIncidencia = addIncidencia.findViewById(R.id.btnAñadirIncidencia);
        btnafegirIncidencia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText txtIncidencia = addIncidencia.findViewById(R.id.et_Inc_Title);
                EditText txtDescripcion = addIncidencia.findViewById(R.id.et_Description);

                String inc_title = txtIncidencia.getText().toString();
                String inc_desc = txtDescripcion.getText().toString();
                //Lo almaceno en la database
                Incidence incidence = new Incidence(inc_title, nivel_prioridad);
                incidence.setDescription(inc_desc);
                incidence.setUnixDate(System.currentTimeMillis() / 1000);;
                dbHelper.insertIncidencia(db, incidence);
                Toast.makeText(MainActivity.getInstance(),"Incidencia Añadida", Toast.LENGTH_SHORT).show();
            }
        });

        return addIncidencia;
    }


}
