package com.example.ipamss.MCFE;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.ipamss.MCFE.Service.NotificationService;
import com.example.ipamss.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

public class SplashScreenMCFE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_mcfe);
        SharedPreferences pref = getSharedPreferences("token", MODE_PRIVATE);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                            android.R.anim.fade_in, android.R.anim.fade_out).toBundle();

                    String auth_token = pref.getString("auth_token",null);
                    if(auth_token !=null){
                        Intent i = new Intent(SplashScreenMCFE.this, DashboardActivity.class);
                        SplashScreenMCFE.this.startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }else{
                        Intent i = new Intent(SplashScreenMCFE.this, LoginActivity.class);
                        SplashScreenMCFE.this.startActivity(i);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        Log.d("Nico pass", "Nothing to show");
                    }
                    onStartService();
                    finish();
                }
                catch (Exception err){
                    err.printStackTrace();
                }
            }
        };    thread.start();
    }
    private void onStartService() {
        if(!isMyServiceRunning(NotificationService.class)) {
            Log.i("isMyServiceRunning", "running");
            try{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.i("ServiceGate", "Oreo");
                    startForegroundService(new Intent(this, NotificationService.class));
                }else{
                    Log.i("ServiceGate", "Below Oreo");
                    startService(new Intent(this, NotificationService.class));
                }
            }catch (Exception ex){Log.e("ServiceGate Error", "Error");}
        }
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    Log.i("isMyServiceRunning?", true + "");
                    return true;
                }
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }

}