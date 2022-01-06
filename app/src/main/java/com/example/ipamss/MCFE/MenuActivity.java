package com.example.ipamss.MCFE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;

import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {
ActivityBottomNav activityBottomNav;
    public String fullname,id,dept,fid;
    public String student_name;
    public String class_id;
    public String sy_id;
    public String SID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        final Button btn_home = findViewById(R.id.btn_home);
        final Button btn_evaluation = findViewById(R.id.btn_evaluation);
        final Button btn_profile = findViewById(R.id.btn_profile);
        final Button btn_about = findViewById(R.id.button4);

        Intent get = getIntent();
        class_id = get.getStringExtra("class_id");
        sy_id = get.getStringExtra("sy_id");
        //fullname = get.getStringExtra("fullname");
        id = get.getStringExtra("SID");
        SID = get.getStringExtra("SID");
      //  dept = get.getStringExtra("dept");
       student_name = get.getStringExtra("student_name");

       // Log.e("my_asset class_id", class_id);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MabiniActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });
        btn_evaluation.setOnClickListener(new View.OnClickListener() { //evaluation
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ActivityBottomNav.class);
                //intent.putExtra("SID", id);
                intent.putExtra("SID", SID);
                intent.putExtra("student_name", student_name);
                intent.putExtra("class_id", class_id);
                intent.putExtra("sy_id", sy_id);
                MenuActivity.this.startActivity(intent);
            }
        });
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ContactActivity.class);
                MenuActivity.this.startActivity(intent);

            }
        });
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String status = "Starting";
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("nico",response);
                            JSONObject jsonResponse = new JSONObject(response);

                            //Log.e("jsonResponse", String.valueOf(jsonResponse));

                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Toast.makeText(MenuActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception err){
                            err.printStackTrace();
                        }
                    }
                };

                QuerySyRestriction sy_restriction = new QuerySyRestriction(status ,responseListener);
                RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);
                queue.add(sy_restriction);

                Toast.makeText(MenuActivity.this, "LOADING", Toast.LENGTH_SHORT).show();

            }
        });
    }
}