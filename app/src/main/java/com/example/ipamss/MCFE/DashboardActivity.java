package com.example.ipamss.MCFE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;

import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity {
    public String id;
    public String fid;
    public String student_name;
    public String class_id;
    public String sy_id;
    public String SID;
    boolean doubleBackToExitPressedOnce = false;

    SharedPreferences.Editor editor_sy;
    SharedPreferences pref_sy;
    final String STATUS = "Starting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        editor_sy = getSharedPreferences("school_year", MODE_PRIVATE).edit();
        pref_sy = getSharedPreferences("school_year", MODE_PRIVATE);

        final CardView card_home = findViewById(R.id.card_home);
        final CardView card_evaluation = findViewById(R.id.card_evaluation);
        final CardView card_notification = findViewById(R.id.card_notifications);
        final CardView card_contact = findViewById(R.id.card_contact);
        final CardView card_profile = findViewById(R.id.card_profile);
        final CardView card_about = findViewById(R.id.card_about);

        Intent get = getIntent();
        class_id = get.getStringExtra("class_id"); //null
        sy_id = get.getStringExtra("id"); //null
        id = get.getStringExtra("SID"); //null
        SID = get.getStringExtra("SID"); //null
        student_name = get.getStringExtra("student_name"); //null

        onCheckSy();
        card_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MabiniActivity.class);
                DashboardActivity.this.startActivity(intent);
            }
        });

        //Evaluation
        card_evaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckSy();
                final Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String prefs_sy = pref_sy.getString("current_sy",null);
                        if(prefs_sy==null){
                            Intent i = new Intent(DashboardActivity.this, NoEvaluationActivity.class);
                            DashboardActivity.this.startActivity(i);
                        }else{
                            Intent intent = new Intent(DashboardActivity.this, ActivityBottomNav.class);
                            intent.putExtra("SID", SID);
                            intent.putExtra("student_name", student_name);
                            intent.putExtra("class_id", class_id);
                            intent.putExtra("sy_id", sy_id);
                            DashboardActivity.this.startActivity(intent);
                        }
                    }
                },800);
            }
        });
        //Notifications
        card_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, NotificationsActivity.class);
                DashboardActivity.this.startActivity(intent);
            }
        });
        //Contact
        card_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ContactActivity.class);
                DashboardActivity.this.startActivity(intent);
            }
        });
        //Profile
        card_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, ProfileActivity.class);
                DashboardActivity.this.startActivity(i);
            }
        });
        // About
        card_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, AboutActivity.class);
                DashboardActivity.this.startActivity(i);
            }
        });
    }

    public void onCheckSy(){
        Response.Listener<String> resp = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try
                    {
                        JSONObject jsonResponseSy = new JSONObject(response);
                        boolean success = jsonResponseSy.getBoolean("success");
                        if(success){
                            String sy_id = jsonResponseSy.getString("id");
                            editor_sy.putString("current_sy", sy_id);
                            editor_sy.apply();
                        }
                        else{
                            editor_sy.putString("current_sy", null);
                            editor_sy.apply();
                        }
                    }catch (Exception err){
                        err.printStackTrace();
                    }
                }
            };
            QuerySyRestriction sy_restriction = new QuerySyRestriction(STATUS ,resp);
            RequestQueue q = Volley.newRequestQueue(DashboardActivity.this);
            q.add(sy_restriction);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click Back again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}