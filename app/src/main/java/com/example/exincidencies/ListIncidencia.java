package com.example.exincidencies;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import DB.IncidenciaDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */

public class ListIncidencia extends Fragment {
    protected IncidenciaDBHelper dbHelper;
    protected SQLiteDatabase db;
    public ListIncidencia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View listIncidencia = inflater.inflate(R.layout.fragment_list_incidencia, container, false);
        dbHelper = ((MainActivity)getActivity()).dbHelper;
        db = ((MainActivity)getActivity()).db;

        RecyclerView recyclerView = (RecyclerView)listIncidencia.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(listIncidencia.getContext()));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dbHelper.getAllIncidencias(db));


        recyclerView.setAdapter(adapter);


        return listIncidencia;
    }
}
