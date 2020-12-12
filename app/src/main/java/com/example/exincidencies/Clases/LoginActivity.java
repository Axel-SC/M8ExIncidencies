package com.example.exincidencies.Clases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exincidencies.R;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {


    private EditText textUsername;
    private EditText textPsw;
    private Button btnLogin;
    //Codigo Añadido
    SharedPreferences sPreferences;
    Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        // Check if there is a languaje stored, revisar app settings
        sPreferences = this.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        config = new Configuration(getResources().getConfiguration());
        config.locale = new Locale(sPreferences.getString("Language", ""));
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Login if there is a stored used
        if (sPreferences.getBoolean("User_Stored", false)) {
            goToMain();
        }


        textUsername = (EditText)findViewById(R.id.etUserName);
        textPsw = (EditText)findViewById(R.id.etPsw);
        btnLogin = this.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtLogin = textUsername.getText().toString();
                String txtPsw = textPsw.getText().toString();
                if(txtLogin.equals("admin") && txtPsw.equals("admin")) {

                    sPreferences.edit().putBoolean("User_Stored", true).commit();
                    sPreferences.edit().putString("User_Name", txtLogin).commit();
                    sPreferences.edit().putString("User_Psw", txtPsw).commit();
                    goToMain();
                } else  {
                    Toast.makeText(LoginActivity.this,R.string.IncorrectLogin, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void goToMain() {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }

}
