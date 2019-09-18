package com.codejunk1e.studybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import static com.codejunk1e.studybuddy.SignUp.DEPARTMENT;
import static com.codejunk1e.studybuddy.SignUp.EMAIL;
import static com.codejunk1e.studybuddy.SignUp.LOGIN;
import static com.codejunk1e.studybuddy.SignUp.NAME;
import static com.codejunk1e.studybuddy.SignUp.PASSWORD;
import static com.codejunk1e.studybuddy.SignUp.REGISTRATION_NUMBER;

public class LogIn extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText e_address;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

    }


    public void openDashboard(View view) {
        e_address = findViewById(R.id.edit_address);
        password = findViewById(R.id.edit_password);
        sharedPreferences = getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        String sEmail = sharedPreferences.getString(EMAIL, "N/A");
        String sPassword = sharedPreferences.getString(PASSWORD, "N/A");


        if(e_address.getText().toString().equals(sEmail) && password.getText().toString().equals(sPassword)){
            Intent intent = new Intent(this, ScrollingActivity.class);
            intent.putExtra("Login class", true);
            startActivity(intent);
        }
        else {
            displaySnack();
        }


    }
    private void displaySnack() {
        Snackbar.make(findViewById(R.id.login_layout), "Wrong Email Address or Password :-(", Snackbar.LENGTH_LONG).show();
    }
}
