package com.example.exincidencies.Fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.exincidencies.Fragments.AddIncidencia;
import com.example.exincidencies.Fragments.Configuration;
import com.example.exincidencies.Fragments.ListIncidencia;
import com.example.exincidencies.R;
import com.google.android.material.snackbar.Snackbar;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import com.example.exincidencies.Clases.MainActivity;
import DB.IncidenciaDBHelper;


/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Menu extends Fragment {
    protected IncidenciaDBHelper dbHelper;
    protected SQLiteDatabase db;

    private final int[] BTNMENU = {R.id.Añadir, R.id.Listar, R.id.Eliminar, R.id.BtnSettings};

    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View menu = inflater.inflate(R.layout.fragment_menu, container, false);

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

        final Button btnEliminar = menu.findViewById(R.id.Eliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = ((MainActivity) getActivity()).dbHelper;
                db = ((MainActivity) getActivity()).db;
                new FancyGifDialog.Builder(MainActivity.getInstance())
                        .setTitle("WARNING")
                        .setMessage("Do you want to remove all the incidences? ")
                        .setNegativeBtnText("Cancel")
                        .setPositiveBtnBackground(R.color.soft_turquqise)
                        .setPositiveBtnText("Ok")
                        .setNegativeBtnBackground(R.color.pinky)
                        .setGifResource(R.drawable.check_circle2)
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                dbHelper.deleteEntries(db);
                                MainActivity.customSnackBar(menu.findViewById(R.id.Menu), getString(R.string.RemoveIncidences), Snackbar.LENGTH_SHORT);
                            }
                        })
                        .OnNegativeClicked(new FancyGifDialogListener() {
                            @Override
                            public void OnClick() {
                                MainActivity.customSnackBar(menu.findViewById(R.id.Menu), "Canceled", Snackbar.LENGTH_SHORT);

                            }
                        })
                        .build();


                //Custom Fancy Dialog


            }

        });


        final Button btnSettings = menu.findViewById(R.id.BtnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment fragmentSettings = new Configuration();
                menuTransaction.replace(R.id.fragmentID, fragmentSettings);

                menuTransaction.commit();
            }
        });


        return menu;
    }
}
