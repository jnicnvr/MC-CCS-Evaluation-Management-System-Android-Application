package com.example.ipamss.MCFE;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;

import org.json.JSONObject;

public class EvaluationResultFragment extends Fragment {

    final int notifyId = 26327;
    private NotificationManager mn;
    ActivityBottomNav activityBottomNav;
    private static View view;

    public float _overall_rating = 0;
    float compute_rating = 0;
    float converted_rating;
    float sum_rating = 0;

    String[] ratings1;
    String[] ratings2;
    String[] ratings3;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mn = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE) ;

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_evaluation_result, container, false);
        } catch (InflateException e) {
            e.printStackTrace();
        }

        activityBottomNav = (ActivityBottomNav) this.getActivity();
        final ScrollView sv_3 = view.findViewById(R.id.sv_new);
        sv_3.setVisibility(View.GONE);

        //<editor-fold desc="TexView Initialization">
        //Management Skills Questionnaire and Rating

        final TextView rm_q1 = view.findViewById(R.id.rm_q1);
        final TextView rm_q2 = view.findViewById(R.id.rm_q2);
        final TextView rm_q3 = view.findViewById(R.id.rm_q3);
        final TextView rm_q4 = view.findViewById(R.id.rm_q4);
        final TextView rm_q5 = view.findViewById(R.id.rm_q5);
        final TextView rm_q6 = view.findViewById(R.id.rm_q6);
        final TextView rm_q7 = view.findViewById(R.id.rm_q7);
        final TextView rm_q8 = view.findViewById(R.id.rm_q8);
        final TextView rm_q9 = view.findViewById(R.id.rm_q9);
        final TextView rm_q10 = view.findViewById(R.id.rm_q10);

        //Effectiveness Questionnaire and Rating
        final TextView re_q1 = view.findViewById(R.id.re_q1);
        final TextView re_q2 = view.findViewById(R.id.re_q2);
        final TextView re_q3 = view.findViewById(R.id.re_q3);
        final TextView re_q4 = view.findViewById(R.id.re_q4);
        final TextView re_q5 = view.findViewById(R.id.re_q5);
        final TextView re_q6 = view.findViewById(R.id.re_q6);
        final TextView re_q7 = view.findViewById(R.id.re_q7);
        final TextView re_q8 = view.findViewById(R.id.re_q8);
        final TextView re_q9 = view.findViewById(R.id.re_q9);
        final TextView re_q10 = view.findViewById(R.id.re_q10);

        //PROFESSIONAL ETHICS AND PERSONAL QUALITIES Questionnaire and Rating
        final TextView rpepq_q1 = view.findViewById(R.id.rpepq_q1);
        final TextView rpepq_q2 = view.findViewById(R.id.rpepq_q2);
        final TextView rpepq_q3 = view.findViewById(R.id.rpepq_q3);
        final TextView rpepq_q4 = view.findViewById(R.id.rpepq_q4);
        final TextView rpepq_q5 = view.findViewById(R.id.rpepq_q5);
        final TextView rpepq_q6 = view.findViewById(R.id.rpepq_q6);
        final TextView rpepq_q7 = view.findViewById(R.id.rpepq_q7);
        final TextView rpepq_q8 = view.findViewById(R.id.rpepq_q8);
        final TextView rpepq_q9 = view.findViewById(R.id.rpepq_q9);
        final TextView rpepq_q10 = view.findViewById(R.id.rpepq_q10);
        //</editor-fold>

        final EditText et_faculty = view.findViewById(R.id.et_faculty_name);
        final EditText et_comment = view.findViewById(R.id.et_feedback);
        final Button btn_comment = view.findViewById(R.id.btn_comment);
        final Button btn_view = view.findViewById(R.id.btn_view);

        btn_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(activityBottomNav.stat == "good") {
                    et_faculty.setText(activityBottomNav.faculty);

                    //<editor-fold desc="Fetch Management">
                    Response.Listener<String> res = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                Log.e("my_asset Management", response);
                                if(success){
                                    String rating1 = jsonResponse.getString("rating1");
                                    String rating2 = jsonResponse.getString("rating2");
                                    String rating3 = jsonResponse.getString("rating3");
                                    String rating4 = jsonResponse.getString("rating4");
                                    String rating5 = jsonResponse.getString("rating5");
                                    String rating6 = jsonResponse.getString("rating6");
                                    String rating7 = jsonResponse.getString("rating7");
                                    String rating8 = jsonResponse.getString("rating8");
                                    String rating9 = jsonResponse.getString("rating9");
                                    String rating10 = jsonResponse.getString("rating10");

                                    rm_q1.setText(rating1);
                                    rm_q2.setText(rating2);
                                    rm_q3.setText(rating3);
                                    rm_q4.setText(rating4);
                                    rm_q5.setText(rating5);
                                    rm_q6.setText(rating6);
                                    rm_q7.setText(rating7);
                                    rm_q8.setText(rating8);
                                    rm_q9.setText(rating9);
                                    rm_q10.setText(rating10);

                                    ratings1 = new String[]{rating1, rating2, rating3, rating4, rating5, rating6, rating7, rating8, rating9, rating10};
                                    //onConvertToFloat(ratings);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    };
                    String code_mgmnt="MGMNT";
                    Log.e("my_asset SID", activityBottomNav.SID);
                    Log.e("my_asset fid", activityBottomNav.fid);
                    Log.e("my_asset sy_id", activityBottomNav.sy_id);
                    Log.e("my_asset class_id", activityBottomNav.class_id);
                    Log.e("my_asset subject_id",activityBottomNav.subject_id);
                    Log.e("my_asset code", code_mgmnt);
                    QueryRatingFetch fetchRating = new QueryRatingFetch(activityBottomNav.SID, activityBottomNav.fid, activityBottomNav.sy_id, activityBottomNav.class_id, activityBottomNav.subject_id, code_mgmnt, res);
                    RequestQueue q = Volley.newRequestQueue(getActivity());
                    q.add(fetchRating);
                    //</editor-fold>

                    //<editor-fold desc="Effectiveness"
                    Response.Listener<String> resp = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                Log.e("my_asset Effect",response);
                                if(success){
                                    String rating1 = jsonResponse.getString("rating1");
                                    String rating2 = jsonResponse.getString("rating2");
                                    String rating3 = jsonResponse.getString("rating3");
                                    String rating4 = jsonResponse.getString("rating4");
                                    String rating5 = jsonResponse.getString("rating5");
                                    String rating6 = jsonResponse.getString("rating6");
                                    String rating7 = jsonResponse.getString("rating7");
                                    String rating8 = jsonResponse.getString("rating8");
                                    String rating9 = jsonResponse.getString("rating9");
                                    String rating10 = jsonResponse.getString("rating10");

                                    re_q1.setText(rating1);
                                    re_q2.setText(rating2);
                                    re_q3.setText(rating3);
                                    re_q4.setText(rating4);
                                    re_q5.setText(rating5);
                                    re_q6.setText(rating6);
                                    re_q7.setText(rating7);
                                    re_q8.setText(rating8);
                                    re_q9.setText(rating9);
                                    re_q10.setText(rating10);

                                    ratings2 = new String[]{rating1, rating2, rating3, rating4, rating5, rating6, rating7, rating8, rating9, rating10};
                                   // onConvertToFloat(ratings);
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    };
                    String code_effectiveness="EFFECT";
                    QueryRatingFetch e_fetch = new QueryRatingFetch(activityBottomNav.SID, activityBottomNav.fid, activityBottomNav.sy_id, activityBottomNav.class_id, activityBottomNav.subject_id,code_effectiveness, resp);
                    RequestQueue que = Volley.newRequestQueue(getActivity());
                    que.add(e_fetch);
                    //</editor-fold>

                    //<editor-fold desc="PEPQ"
                    Response.Listener<String> response = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                Log.e("my_asset PEPQ",response);
                                if(success){
                                    String rating1 = jsonResponse.getString("rating1");
                                    String rating2 = jsonResponse.getString("rating2");
                                    String rating3 = jsonResponse.getString("rating3");
                                    String rating4 = jsonResponse.getString("rating4");
                                    String rating5 = jsonResponse.getString("rating5");
                                    String rating6 = jsonResponse.getString("rating6");
                                    String rating7 = jsonResponse.getString("rating7");
                                    String rating8 = jsonResponse.getString("rating8");
                                    String rating9 = jsonResponse.getString("rating9");
                                    String rating10 = jsonResponse.getString("rating10");

                                    rpepq_q1.setText(rating1);
                                    rpepq_q2.setText(rating2);
                                    rpepq_q3.setText(rating3);
                                    rpepq_q4.setText(rating4);
                                    rpepq_q5.setText(rating5);
                                    rpepq_q6.setText(rating6);
                                    rpepq_q7.setText(rating7);
                                    rpepq_q8.setText(rating8);
                                    rpepq_q9.setText(rating9);
                                    rpepq_q10.setText(rating10);
                                    sv_3.setVisibility(View.VISIBLE);
                                    btn_view.setVisibility(View.INVISIBLE);

                                    ratings3 = new String[]{rating1, rating2, rating3, rating4, rating5, rating6, rating7, rating8, rating9, rating10};
                                   // onConvertToFloat(ratings);

                                    onDisplayRate();

                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    };
                    String code_pepq="PEPQ";
                    Log.e("PEPQ SID", activityBottomNav.SID);
                    Log.e("PEPQ fid", activityBottomNav.fid);
                    Log.e("PEPQ sy_id", activityBottomNav.sy_id);
                    Log.e("PEPQ class_id", activityBottomNav.class_id);
                    Log.e("PEPQ subject_id",activityBottomNav.subject_id);
                    QueryRatingFetch pepq_fetch = new QueryRatingFetch(activityBottomNav.SID, activityBottomNav.fid, activityBottomNav.sy_id, activityBottomNav.class_id, activityBottomNav.subject_id,code_pepq, response);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(pepq_fetch);
                    //</editor-fold>

                    onSubmitComment();
                }
                else
                    {
                   //     Intent i = new Intent(getActivity(), EvaluationSuccess.class);
                     //   getActivity().startActivity(i);
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("YOU NEED TO CONDUCT EVALUATION FIRST TO VIEW EVALUATION REPORT");
                    builder1.setCancelable(true);

                    builder1.setNegativeButton(
                            "OKAY",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                 //   activityBottomNav.bottomNavigationView.setSelectedItemId(R.id.evaluation);
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
        return view;
    }
    public void onConvertToFloat(String[] ratings){

        for (int i=0; i<ratings.length; i++){
            converted_rating =Float.parseFloat(ratings[i]);
            sum_rating+=converted_rating;
        }
        Log.e("Nico sum float", String.valueOf(sum_rating));
        _overall_rating = sum_rating;

    }
    public void onDisplayRate(){
        onConvertToFloat(ratings1);
        onConvertToFloat(ratings2);
        onConvertToFloat(ratings3);

        final TextView tv_rating_sum = view.findViewById(R.id.tv_rating_sum);
        Log.e("Nico _overall_rating",String.valueOf(_overall_rating));
        String rate ="";
        compute_rating = (_overall_rating / 150) * 100;
        //compute_rating = compute_rating * 100;
        Log.e("Nico compute_rating", String.valueOf(compute_rating));

        if(_overall_rating>97 ){
            rate = "EXCELLENT";
            tv_rating_sum.setText("Rating: "+rate+" ("+compute_rating+"%)");
        }else if(_overall_rating > 94 && _overall_rating <=97){
            rate = "ABOVE AVERAGE";
            tv_rating_sum.setText("Rating: "+rate+" ("+compute_rating+"%)");
        }else if(_overall_rating > 89 && _overall_rating <=94){
            rate = "AVERAGE";
            tv_rating_sum.setText("Rating: "+rate+" ("+compute_rating+"%)");
        }else if(_overall_rating > 84 && _overall_rating <=89){
            rate = "BELOW AVERAGE";
            tv_rating_sum.setText("Rating: "+rate+" ("+compute_rating+"%)");
        }else if(_overall_rating <=84){
            rate = "UNSATISFACTORY ";
            tv_rating_sum.setText("Rating: "+rate+" ("+compute_rating+"%)");
        }

    }

    public void onSubmitComment(){
        final EditText et_comment = view.findViewById(R.id.et_feedback);
        final Button btn_comment = view.findViewById(R.id.btn_comment);

        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> response = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Log.e("Nico comment",response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Toast.makeText(getActivity().getApplicationContext(), "Comment Submitted!", Toast.LENGTH_SHORT).show();
                                onRefreshData();
                                //onLoadNofication();
                                //activityBottomNav.bottomNavigationView.setSelectedItemId(R.id.evaluation);
                                Intent i = new Intent(getActivity(), EvaluationSuccess.class);
                                getActivity().startActivity(i);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                String comment = et_comment.getText().toString();
                QueryInsertComment cmt = new QueryInsertComment(activityBottomNav.SID, activityBottomNav.fid, activityBottomNav.sy_id, activityBottomNav.class_id, activityBottomNav.subject_id, comment,response);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.add(cmt);
            }
        });

    }
    private void onLoadNofication(){
        NotificationManager mn;
        mn = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE) ;

        //Intent notificationIntent = new Intent(getApplicationContext(), NotificationActivity.class);
        // PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        String NOTIFICATION_CHANNEL_ID = "com.adsoph.cneas_adsoph";
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("", "myV > O ");
            String channelName = "Notification Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_HIGH);
            chan.setLightColor(Color.BLUE);
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            assert manager != null;
            manager.createNotificationChannel(chan);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getContext(), NOTIFICATION_CHANNEL_ID);

            Notification notification = notificationBuilder.setOngoing(true)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("Test Context")
                    .setPriority(NotificationManager.IMPORTANCE_HIGH)
                    .setCategory(Notification.CATEGORY_MESSAGE)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setVibrate(new long[] { 1000, 1000})
                    // .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            //  startForeground(1, notification);
            mn.notify(1234, notification);
        }else{

        }
    }

    public void onRefreshData(){
        activityBottomNav.fid = null;
        activityBottomNav.sy_id = null;
        activityBottomNav.class_id = null;
        activityBottomNav.subject_id = null;
        activityBottomNav.stat = null;
    }

}