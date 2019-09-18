package com.codejunk1e.studybuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import static com.codejunk1e.studybuddy.SignUp.DEPARTMENT;
import static com.codejunk1e.studybuddy.SignUp.LOGIN;
import static com.codejunk1e.studybuddy.SignUp.NAME;
import static com.codejunk1e.studybuddy.SignUp.REGISTRATION_NUMBER;

public class ScrollingActivity extends AppCompatActivity {

    ArrayList<String> Descrption = new ArrayList<>(Arrays.asList("My result sheet", "Timetable", "Goals", "Campus Talk", "Portal", "Calendar", "Directory", "My File"));
    ArrayList<Integer> image = new ArrayList<>(Arrays.asList(R.drawable.ic_book_black_48dp, R.drawable.ic_timeline_black_48dp, R.drawable.ic_playlist_add_check_black_48dp, R.drawable.ic_chat_black_48dp, R.drawable.ic_account_circle_black_48dp, R.drawable.ic_perm_contact_calendar_black_48dp, R.drawable.ic_directions_black_48dp, R.drawable.ic_folder_black_48dp));
    SharedPreferences sharedPreferences;
    TextView nameView;
    TextView reg_NoView;
    TextView departmentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        populateRecyclerView();
        getThemPrefrences();
        checkCallingActivity();
    }

    private void checkCallingActivity() {

        Intent intent = getIntent();
        boolean state_logIn = intent.getBooleanExtra("Login class", false);
        boolean state_signUp = intent.getBooleanExtra("Signup class", false);

        if (state_logIn){
            displaySnack("You've successfully signed in :-)");
        }

        if  (state_signUp){
            displaySnack("You've successfully signed Up :-)");
        }
    }

    private void getThemPrefrences() {

        nameView = findViewById(R.id.name);
        reg_NoView = findViewById(R.id.regnumber);
        departmentView = findViewById(R.id.department);

        sharedPreferences = getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        String sName = sharedPreferences.getString(NAME, "N/A");
        String sReg_No = sharedPreferences.getString(REGISTRATION_NUMBER, "N/A");
        String sDepartment = sharedPreferences.getString(DEPARTMENT, "N/A");

        nameView.setText(sName.toUpperCase());
        reg_NoView.setText(sReg_No.toUpperCase());
        departmentView.setText(sDepartment.toUpperCase());
    }

    private void populateRecyclerView() {
        RecyclerView dashView = (RecyclerView) findViewById(R.id.dahView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        dashView.setLayoutManager(gridLayoutManager); // setting LayoutManager to RecyclerView
        final Adapter adapter = new Adapter(ScrollingActivity.this, Descrption, image);
        dashView.setAdapter(adapter);
    }

    private void displaySnack( String message) {
        Snackbar.make(findViewById(R.id.scroll), message, Snackbar.LENGTH_LONG).show();
    }
}
