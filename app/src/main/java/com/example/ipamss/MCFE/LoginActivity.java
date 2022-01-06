package com.example.ipamss.MCFE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;
import com.google.android.material.snackbar.Snackbar;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    NotificationBadge mBadge;
    private int count = 1;
    final String STATUS = "Starting";
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences.Editor editor = getSharedPreferences("token", MODE_PRIVATE).edit();
        SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);

        SharedPreferences.Editor editor_student = getSharedPreferences("student_data", MODE_PRIVATE).edit();
        SharedPreferences pref_student = getSharedPreferences("student_data", MODE_PRIVATE);

        SharedPreferences.Editor editor_sy = getSharedPreferences("school_year", MODE_PRIVATE).edit();
        SharedPreferences pref_sy = getSharedPreferences("school_year", MODE_PRIVATE);

        final Button btn_login = findViewById(R.id.btn_logins);
        final EditText et_username = findViewById(R.id.et_userName);
        final EditText et_password  = findViewById(R.id.et_pass);
        final EditText et_username1 = findViewById(R.id.et_username);
        final EditText et_password1  = findViewById(R.id.et_password);
        final TextView changepassword = findViewById(R.id.change_password);
        final TextView txt_hidden = findViewById(R.id.textViewhidden);
        final TextView txt_createAccount = findViewById(R.id.txt_createAccount);
        final ImageView progressBar = findViewById(R.id.loadinggif);
        progressBar.setVisibility(View.INVISIBLE);
        txt_hidden.setVisibility(View.INVISIBLE);
        changepassword.setVisibility(View.INVISIBLE);
        et_password1.setVisibility(View.INVISIBLE);
        et_username1.setVisibility(View.INVISIBLE);
        final ScrollView sv0 = findViewById(R.id.sv0);
        sv0.setVisibility(View.GONE);
         statusCheck();

        changepassword.setPaintFlags(changepassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_createAccount.setPaintFlags(txt_createAccount.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txt_createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
              /*  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
              //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                LoginActivity.this.startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(haveNetworkConnection()){

                        final String username = et_username.getText().toString().trim();
                        final String password = et_password.getText().toString().trim();


                        if (username.isEmpty() || password.isEmpty()) {
                            et_username.setError("Please Insert Username");
                            et_password.setError("Please Insert Password");
                           // onLoadNofication();
//                            if(pref.getString("username","") != null && pref.getString("password","")!=null){
//                                Log.d("Nico", pref.getString("username", ""));
//                                Log.d("Nico pass", pref.getString("password", ""));
//
//                            }else{
//                                Log.d("Nico pass", "Nothing to show");
//                            }
                        } else {
                            progressBar.setVisibility(View.VISIBLE);
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            Log.e("nico res",response);
                                          JSONObject jsonResponse = new JSONObject(response);
                                           Log.e("jsonResponse", String.valueOf(jsonResponse));

                                              boolean success = jsonResponse.getBoolean("success");

                                            if (success) {
                                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                                                final String student_name = jsonResponse.getString("student_name");
                                                final String class_id = jsonResponse.getString("class_id");
                                                final String SID = jsonResponse.getString("SID");

                                                intent.putExtra("SID", SID);
                                                intent.putExtra("class_id", class_id);
                                                intent.putExtra("student_name",student_name);

                                                editor_student.putString("SID", SID);
                                                editor_student.putString("class_id", class_id);
                                                editor_student.putString("student_name", student_name);
                                                editor_student.apply();

                                                editor.putString("auth_token", username +"Nico");
                                                editor.apply();

                                                Response.Listener<String> responses = new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String response) {
                                                        try {
                                                           //for logs
                                                            //if login is successful
                                                            JSONObject jsonResponse = new JSONObject(response);
                                                            boolean success = jsonResponse.getBoolean("success");
                                                            if (success) {
                                                                View parentLayout = findViewById(android.R.id.content);
                                                                Snackbar.make(parentLayout, "Successfully Login", Snackbar.LENGTH_LONG).show();


                                                                 // *********************** School Year Restrictions **********************//
                                                                Response.Listener<String> resp = new Response.Listener<String>() {

                                                                    @Override
                                                                    public void onResponse(String response) {
                                                                        try
                                                                        {
                                                                            JSONObject jsonResponseSy = new JSONObject(response);
                                                                            boolean success = jsonResponseSy.getBoolean("success");
                                                                            if(success){
                                                                                String sy_id = jsonResponseSy.getString("sy_id");
                                                                                final Handler mHandler = new Handler();
                                                                                //Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                                                                mHandler.postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        progressBar.setVisibility(View.INVISIBLE);
                                                                                        intent.putExtra("sy_id", sy_id);

                                                                                       // editor_sy.putString("current_sy", sy_id);
                                                                                       // editor_sy.apply();

                                                                                        LoginActivity.this.startActivity(intent);
                                                                                    }
                                                                                }, 3000); // 4 seconds

                                                                            }
                                                                            else {
                                                                                //login failed
                                                                                final Handler mHandler = new Handler();
                                                                                //Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                                                                mHandler.postDelayed(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        progressBar.setVisibility(View.INVISIBLE);
                                                                                       // View parentLayout = findViewById(android.R.id.content);
                                                                                        et_username.setText("");
                                                                                        et_password.setText("");
                                                                                       // Intent i = new Intent(LoginActivity.this, NoEvaluationActivity.class);
                                                                                        //LoginActivity.this.startActivity(i);
                                                                                    }
                                                                                }, 3000); // 4 seconds
                                                                            }
                                                                        }catch (Exception err){
                                                                            err.printStackTrace();
                                                                        }
                                                                    }
                                                                };
                                                                QuerySyRestriction sy_restriction = new QuerySyRestriction(STATUS ,resp);
                                                                RequestQueue q = Volley.newRequestQueue(LoginActivity.this);
                                                                q.add(sy_restriction);

                                                            } else {
                                                                //login failed
                                                                final Handler mHandler = new Handler();
                                                                //Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                                                mHandler.postDelayed(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        progressBar.setVisibility(View.INVISIBLE);
                                                                        progressBar.setVisibility(View.INVISIBLE);
                                                                        View parentLayout = findViewById(android.R.id.content);
                                                                        Snackbar.make(parentLayout, "LOGIN FAILED", Snackbar.LENGTH_LONG).show();
                                                                    }
                                                                }, 3000); // 4 seconds

                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                };
                                                QuerySaveLogs logs = new QuerySaveLogs(student_name, responses);
                                                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                                                queue.add(logs);

                                            } else {
                                                final Handler mHandler = new Handler();
                                                //Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                                mHandler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        View parentLayout = findViewById(android.R.id.content);
                                                        Snackbar.make(parentLayout, "LOGIN FAILED", Snackbar.LENGTH_LONG).show();
                                                    }
                                                }, 3000); // 4 seconds

                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                };

                                QueryLoginActivity login = new QueryLoginActivity(username, password, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                                //Log.e("nico login", String.valueOf(login));
                                queue.add(login);

                        }
                    }
                    else if(!haveNetworkConnection()){
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginActivity.this);
                        builder1.setMessage("Internet Connection is not available please make sure you have a stable internet connection");
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Okay",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                }
        });

    }

  /*  @Override
    public void onBackPressed() {
        progressDialog.dismiss();
    }*/
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

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}
