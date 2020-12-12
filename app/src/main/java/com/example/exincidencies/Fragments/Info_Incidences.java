
package com.example.exincidencies.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exincidencies.Clases.LoginActivity;
import com.example.exincidencies.Clases.MainActivity;
import com.example.exincidencies.R;

import DB.IncidenciaDBHelper;

/**
 * A simple {@link android.app.Fragment} subclass.
 */

public class Info_Incidences extends Fragment{
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    String incTitle, incStatus, incPrio, incDesc, incDate;
    int incID;


    public Info_Incidences() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewInfo = inflater.inflate(R.layout.fragment_info__incidences, container, false);
        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        //Getting the values of the bundle
        incTitle = getArguments().getString("INC_TITLE");
        incPrio = getArguments().getString("INC_PRIO");
        incID = getArguments().getInt("INC_ID");
        incStatus = getArguments().getString("INC_STATUS");
        incDesc = getArguments().getString("INC_DESC");
        incDate = getArguments().getString("INC_DATE");


        putInfo(viewInfo, incTitle, incID, incPrio, incStatus, incDesc, incDate);


        final Button btn_status = viewInfo.findViewById(R.id.btnChangeStatus);
        btn_status.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(incStatus) {
                    case "UNFIXED":
                        incStatus="FIXED";
                        refreshBtn(viewInfo, btn_status);

                        break;
                    case "ASIGNED":
                        incStatus="UNFIXED";
                        refreshBtn(viewInfo, btn_status);
                        break;
                    case "FIXED":
                        incStatus="ASIGNED";
                        refreshBtn(viewInfo, btn_status);

                        break;
                }

            }
        }));


        return viewInfo;
    }
    public void refreshBtn(View v, Button statusBtn){
        statusBtn = v.findViewById(R.id.btnChangeStatus);
        switch(incStatus) {
            case "UNFIXED":
                statusBtn.setText(getResources().getString(R.string.UnfixedStatus));
                statusBtn.setBackgroundColor(getResources().getColor(R.color.soft_pinky));

                break;
            case "ASIGNED":
                statusBtn.setText(getResources().getString(R.string.AsignedStatus));
                statusBtn.setBackgroundColor(getResources().getColor(R.color.soft_orange));
                break;
            case "FIXED":
                statusBtn.setText(getResources().getString(R.string.FixedStatus));
                statusBtn.setBackgroundColor(getResources().getColor(R.color.soft_turquqise));
                break;
        }

    }public void putInfo(View v, String title,  int id, String prio, String status, String desc, String date) {
        TextView titlev = v.findViewById(R.id.txt_Inc_Name);
        titlev.setText(getResources().getString(R.string.IncTitulo)+" " +incTitle);
        TextView priov = v.findViewById(R.id.txt_Inc_Prio);
        priov.setText(getResources().getString(R.string.IncPrio)+" "+incPrio);
        TextView idv = v.findViewById(R.id.txt_Inc_ID);
        idv.setText(getResources().getString(R.string.IncID)+" "+String.valueOf(incID));
        TextView statusv = v.findViewById(R.id.txt_Inc_Status);
        statusv.setText(getResources().getString(R.string.IncStatus)+" "+incStatus);
        TextView descv = v.findViewById(R.id.txt_Inc_Desc);
        descv.setText(getResources().getString(R.string.IncDesc)+" "+incDesc);
        TextView datev = v.findViewById(R.id.txt_Inc_Date);
        datev.setText(getResources().getString(R.string.IncTitulo)+" "+incDate);
    }
    public void refresh(){
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

}
