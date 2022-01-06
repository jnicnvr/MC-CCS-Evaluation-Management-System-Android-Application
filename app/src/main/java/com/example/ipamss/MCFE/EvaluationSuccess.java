package com.example.ipamss.MCFE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;
import com.hsalf.smileyrating.SmileyRating;

import org.json.JSONObject;

public class EvaluationSuccess extends AppCompatActivity {
    String rates = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_success);

        final Button btn_feedback = findViewById(R.id.btn_feedback);
        final EditText et_feedback = findViewById(R.id.et_feedback);

        SmileyRating smileyRating = (SmileyRating)findViewById(R.id.smile_rating);
        smileyRating.setSmileySelectedListener(new SmileyRating.OnSmileySelectedListener() {
            @Override
            public void onSmileySelected(SmileyRating.Type type) {

                // You can compare it with rating Type
                if (SmileyRating.Type.GREAT == type) {
                    Log.i("Nico", "Wow, the user gave high rating");
                    rates = "GREAT";
                }else if(SmileyRating.Type.GOOD == type){
                    Log.i("Nico", "Wow, the user gave GOOD rating");
                    rates = "GOOD";
                }else if(SmileyRating.Type.OKAY == type){
                    Log.i("Nico", "Wow, the user gave OKAY rating");
                    rates = "OKAY";
                }else if(SmileyRating.Type.BAD == type){
                    Log.i("Nico", "Wow, the user gave BAD rating");
                    rates = "BAD";
                }else if(SmileyRating.Type.TERRIBLE == type){
                    Log.i("Nico", "Wow, the user gave TERRIBLE rating");
                    rates = "TERRIBLE";
                }
                // You can get the user rating too
                // rating will between 1 to 5
                int rating = type.getRating();
            }
        });
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String feedbacks = et_feedback.getText().toString();
                Response.Listener<String> res = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(EvaluationSuccess.this, "Feedback Submitted!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EvaluationSuccess.this, DashboardActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                EvaluationSuccess.this.startActivity(intent);
                            } else {

                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                };
                QueryFeedback q_feedback = new QueryFeedback(rates,feedbacks,res);
                RequestQueue q = Volley.newRequestQueue(EvaluationSuccess.this);
                q.add(q_feedback);

                Log.e("Nico Rates", rates);
            }
        });

    }
}