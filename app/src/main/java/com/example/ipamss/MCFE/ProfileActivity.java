package com.example.ipamss.MCFE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ipamss.R;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences.Editor expire_token;
    SharedPreferences student_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        expire_token = getSharedPreferences("token", MODE_PRIVATE).edit();
        student_data = getSharedPreferences("student_data", MODE_PRIVATE);


        TextView tv_student_name = (TextView) findViewById(R.id.tv_student_name);
        TextView tv_student_course = (TextView) findViewById(R.id.tv_student_course);
        Button btn_logout = (Button) findViewById(R.id.btn_logout);

        String student_name = student_data.getString("student_name",null);
        String SID = student_data.getString("SID",null);
        tv_student_name.setText(student_name);
        tv_student_course.setText(SID);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expire_token.putString("auth_token", null);
                expire_token.apply();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                ProfileActivity.this.startActivity(intent);
            }
        });

    }
}