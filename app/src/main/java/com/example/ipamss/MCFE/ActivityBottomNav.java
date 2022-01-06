package com.example.ipamss.MCFE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;


public class ActivityBottomNav extends AppCompatActivity {

    public String fullname = "John Doe";
    public String dept = "BSCS";


    public String stat;

    public String class_id;
    public String student_name;
    public String id;
    public String SID;
    public String sy_id;

    public String faculty;
    public String department;
    public String _class;
    public String code;
    public String subject_id;
    public String fid;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setSelectedItemId(R.id.nav_favorites);

        Intent get = getIntent();

        id = get.getStringExtra("SID");
        SID = get.getStringExtra("SID");
        class_id = get.getStringExtra("class_id");
        sy_id = get.getStringExtra("sy_id");
        student_name = get.getStringExtra("student_name");

    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedfragment = new FourthFragment();
                switch (item.getItemId()){
                    case R.id.nav_favorites: //home
                        selectedfragment = new FifthFragment();
                        break;
                    case R.id.nav_bug: //evaluation
                        selectedfragment = new FourthFragment();
                        break;
                    case R.id.evaluation: //the faculty
                        Intent intent = new Intent(ActivityBottomNav.this, SubjectsFragment.class);
                        intent.putExtra("id", id);
                        intent.putExtra("SID", SID);
                        intent.putExtra("class_id", class_id);
                        intent.putExtra("sy_id", sy_id);
                        selectedfragment = new SubjectsFragment();
                        break;
                    case R.id.nav_profile: //results
                        selectedfragment = new EvaluationResultFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedfragment).commit();
                    return true;
                }
            };
    public void changeMenu(int menuId){
        bottomNavigationView.setSelectedItemId(menuId);
    }
}
