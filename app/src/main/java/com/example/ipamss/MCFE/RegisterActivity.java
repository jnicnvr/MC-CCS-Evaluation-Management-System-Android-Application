package com.example.ipamss.MCFE;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;
import com.google.android.material.snackbar.Snackbar;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class
RegisterActivity extends AppCompatActivity {
   // boolean doubleBackToExitPressedOnce = false;
   DatePickerDialog.OnDateSetListener dateSetListener;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button btn_register = findViewById(R.id.btn_reg);
        final EditText et_SID = findViewById(R.id.et_SID);
        final EditText et_fname = findViewById(R.id.et_firstName);
        final EditText et_lname = findViewById(R.id.et_lastName);
        final EditText et_age = findViewById(R.id.et_age);
      //  final EditText et_sex = findViewById(R.id.et_sex);
        final EditText et_course = findViewById(R.id.et_course);
        final EditText et_username = findViewById(R.id.et_reg_username);
        final EditText et_password = findViewById(R.id.et_reg_password);

        et_course.setInputType(InputType.TYPE_NULL);
        et_course.setKeyListener(null);

        et_age.setInputType(InputType.TYPE_NULL);
        et_age.setKeyListener(null);
        // konics - added on 10-16-21
        // final EditText et_year_level = findViewById(R.id.et_year_level);
        //final EditText et_section = findViewById(R.id.et_section);
        Spinner et_year_level = (Spinner) findViewById(R.id.et_year_level);
        Spinner et_section = (Spinner) findViewById(R.id.et_section);
        Spinner et_sex = (Spinner) findViewById(R.id.et_sex);

        final ImageView progressBar = findViewById(R.id.loadinggif);
        final TextView hidden = findViewById(R.id.textView);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        progressBar.setVisibility(View.INVISIBLE);

        et_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterActivity.this
                        ,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,dateSetListener
                        ,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            String picked_date;
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                picked_date = dayOfMonth +"/" + month +"/" + year;

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                String current_date = simpleDateFormat1.format(Calendar.getInstance().getTime());
                try {
                    Date birthdate = simpleDateFormat1.parse(picked_date);
                    Date date_now = simpleDateFormat1.parse(current_date);

                    long startDate = birthdate.getTime();
                    long endDate = date_now.getTime();

                    if(startDate<=endDate){
                        Period period = new Period(startDate,endDate, PeriodType.yearMonthDay());
                        int years = period.getYears();
                        et_age.setText(years+"");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = et_username.getText().toString().trim();
                final String password = et_password.getText().toString().trim();
                final String SID = et_SID.getText().toString().trim();
                final String Fname = et_fname.getText().toString().trim();
                final String Lname = et_lname.getText().toString().trim();
                final String Age = et_age.getText().toString().trim();
                final String Sex = et_sex.getSelectedItem().toString().trim();
                final String Course = et_course.getText().toString().trim();

                final String year_level = et_year_level.getSelectedItem().toString().trim();
                final String section = et_section.getSelectedItem().toString().trim();

                if (username.isEmpty()) {
                    et_username.setError("Required Username");
                }
//                else if(password.isEmpty()){
//                    et_password.setError("Required Password");
//                }else if(SID.isEmpty()){
//                    et_SID.setError("Required Student ID");
//                }else if(Fname.isEmpty()){
//                    et_fname.setError("Required First Name");
//                }else if(Lname.isEmpty()){
//                    et_lname.setError("Required Last Name");
//                }else if(Age.isEmpty()){
//                    et_age.setError("Required Age");
//                }else if(Sex.isEmpty()){
//                   // ((TextView)et_sex.getSelectedView()).setError("Required Sex");
//                   // et_sex.setError("Required Sex");
//                }else if(Course.isEmpty()){
//                    et_course.setError("Required Course");
//                }
                else
                    {
                    progressBar.setVisibility(View.VISIBLE);
                    Response.Listener<String> responses = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                Log.d("NICO",String.valueOf(jsonResponse));
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    final Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                    final Handler mHandler = new Handler();
                                    mHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            View parentLayout = findViewById(android.R.id.content);
                                            Snackbar.make(parentLayout, "SUCCESSFULLY REGISTERED", Snackbar.LENGTH_LONG).show();
                                            RegisterActivity.this.startActivity(intent);
                                        }
                                    }, 3000); // 4 seconds

                                } else {
                                    View parentLayout = findViewById(android.R.id.content);
                                    Snackbar.make(parentLayout, "REGISTRATION FAILED", Snackbar.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    };
                    QueryRegister register = new QueryRegister(SID,Fname,Lname,Age,Sex,Course,username,password, year_level, section, responses);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(register);

                }
            }
        });

    }



   /* public void onBackPressed() {
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
    }*/
}
