package com.codejunk1e.studybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class SignUp extends AppCompatActivity {

    public static final String LOGIN = "Login";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String NAME = "Name";
    public static final String REGISTRATION_NUMBER = "Registration Number";
    public static final String DEPARTMENT = "Department";
    EditText editText_address;
    EditText editText_password;
    EditText editText_full_name;
    EditText editText_reg_no;
    EditText editText_department;
    private AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

     editText_address = findViewById(R.id.edit_address);
     editText_password = findViewById(R.id.edit_password);
     editText_full_name = findViewById(R.id.edit_full_name);
     editText_reg_no = findViewById(R.id.edit_reg_no);
     editText_department = findViewById(R.id.edit_department);
     awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    }

    public void openLogIn(View view) {
        awesomeValidation.addValidation(this, R.id.edit_address, Patterns.EMAIL_ADDRESS, R.string.email_error);
        validatePassword();
        validateName();
        validateRegNo();
        validateDepartment();

        if (awesomeValidation.validate() && validatePassword() && validateName() && validateRegNo() && validateDepartment()) {
            openSesame();
            saveToSharedPref();
        }
        else {
            Snackbar.make(findViewById(R.id.sign_up_layout), "Please fill all the boxes :-(", Snackbar.LENGTH_LONG).show();
        }
    }

    private void saveToSharedPref() {

        SharedPreferences sharedPreferences = getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, editText_address.getText().toString());
        editor.putString(PASSWORD, editText_password.getText().toString());
        editor.putString(NAME, editText_full_name.getText().toString());
        editor.putString(REGISTRATION_NUMBER, editText_reg_no.getText().toString());
        editor.putString(DEPARTMENT, editText_department.getText().toString());
        editor.apply();
    }

    private boolean validatePassword() {

        if (editText_password.getText().toString().trim().matches("")) {
            editText_password.setError("Enter a Password");
            return false;
        }
        else{
            editText_password.setError(null);
            return true;
        }
    }

    private Boolean validateDepartment() {
        if (editText_department.getText().toString().trim().matches("")) {
            editText_department.setError("Enter a Department");
            return false;
        }
        else{
            editText_department.setError(null);
            return true;
        }
    }

    private Boolean validateRegNo() {
        if (editText_reg_no.getText().toString().trim().matches("")) {
        editText_reg_no.setError("Enter a Number");
            return false;
        }
        else{
        editText_reg_no.setError(null);
            return true;
        }

    }

    private Boolean validateName() {
        if (editText_full_name.getText().toString().trim().matches("")) {
            editText_full_name.setError("Enter a name");
            return false;

        }
        else {
            editText_full_name.setError(null);
            return true;
        }
    }

    private void openSesame() {

        Intent intent = new Intent(SignUp.this, ScrollingActivity.class);
        intent.putExtra("Signup class", true);
        startActivity(intent);
    }
}

