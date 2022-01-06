package com.example.ipamss.MCFE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.MCFE.VIewholder.CustomListViewNotifications;
import com.example.ipamss.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class NotificationsActivity extends AppCompatActivity {

    public String[] course;
    public String[] code;
    public String[] subject;
    public String[] name;
    public String[] department;
    public String[] created_at;

    ListView llv;

    SharedPreferences student_pref;
    String pref_SID;
    TextView tv_notifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        student_pref = getSharedPreferences("student_data", MODE_PRIVATE);
        pref_SID = student_pref.getString("SID",null);

        tv_notifications = (TextView) findViewById(R.id.tv_notification);
        llv = (ListView) findViewById(R.id.notification_lv);
        onLoadNotifications(new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(NotificationsActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(JSONArray response) {
                Log.e("my_asset onResponse",String.valueOf(response));
                JSONObject jo = null;
                boolean success = true;
                try{
                    jo = response.getJSONObject(0);
       //             success = jo.getBoolean("success");
                }catch (Exception err){
                    err.printStackTrace();
                }
                Log.e("Nico success", String.valueOf(success));
                if(success){
                    tv_notifications.setVisibility(View.GONE);
                    tv_notifications.setVisibility(View.INVISIBLE);
                    course = new String[response.length()];
                    code = new String[response.length()];
                    subject = new String[response.length()];
                    name = new String[response.length()];
                    department = new String[response.length()];
                    created_at = new String[response.length()];


                    try{
                        for(int i=0; i<=response.length() - 1; i++){
                            jo = response.getJSONObject(i);

                            course[i] = jo.getString("course");
                            code[i] = jo.getString("code");
                            subject[i] = jo.getString("subject");
                            name[i] = jo.getString("name");
                            department[i] = jo.getString("department");
                            created_at[i] = jo.getString("created_at");

                        }

                    }catch (Exception err){
                        err.printStackTrace();
                    }
                    onLoadNotificationsList(course,code,subject,name,department,created_at);
                }
                else{
                    Toast toast = Toast.makeText(NotificationsActivity.this, "Found no Data!",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }
    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(JSONArray response);
    }

    public void onLoadNotificationsList(String []course, String []code, String []subject, String []name, String []department, String []created_at){
        final CustomListViewNotifications customListViewNotifications = new CustomListViewNotifications(NotificationsActivity.this, course, code, subject, name, department, created_at);
        llv.setAdapter(customListViewNotifications);
    }


    public void onLoadNotifications(final VolleyResponseListener volleyResponseListener){
        Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    Log.e("Nico Res", response);
                    JSONArray ja = new JSONArray(response);
                    Log.e("Nico onload notif res",String.valueOf(ja));
                    volleyResponseListener.onResponse(ja);
                }catch (Exception err){
                    err.printStackTrace();
                }
            }
        };

        QueryNotifications notifications = new QueryNotifications(pref_SID,res);
        RequestQueue queue = Volley.newRequestQueue(NotificationsActivity.this);
        queue.add(notifications);
    }

}