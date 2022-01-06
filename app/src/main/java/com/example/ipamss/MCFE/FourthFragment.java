package com.example.ipamss.MCFE;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class FourthFragment extends Fragment {
    TextView date;
    TextView time;

    // Declare globally
    private int position = -1;

ActivityBottomNav activityBottomNav;

    String ra1,ra2,ra3,ra4,ra5,ra6,ra7,ra8,ra9,ra10;
    RatingBar ratingBar1;
    RatingBar ratingBar2;
    RatingBar ratingBar3;
    RatingBar ratingBar4;
    RatingBar ratingBar5;
    RatingBar ratingBar6;
    RatingBar ratingBar7;
    RatingBar ratingBar8;
    RatingBar ratingBar9;
    RatingBar ratingBar10;

   // public String SID;
    public FourthFragment(){

    }
    private static View view2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      //  Intent get = getActivity().getIntent();
       // SID = get.getStringExtra("id");

        if (view2 != null) {
            ViewGroup parent = (ViewGroup) view2.getParent();
            if (parent != null)
                parent.removeView(view2);
        }
        try {
            view2 = inflater.inflate(R.layout.fourth_fragment, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }

        //<editor-fold desc="DECLARATION OF TEXTVIEW">
        final TextView tv1 = view2.findViewById(R.id.tv_1);
        final TextView tv2 = view2.findViewById(R.id.tv_2);
        final TextView tv3 = view2.findViewById(R.id.tv_3);
        final TextView tv4 = view2.findViewById(R.id.tv_4);
        final TextView tv5 = view2.findViewById(R.id.tv_5);
        final TextView tv6 = view2.findViewById(R.id.tv_6);
        final TextView tv7 = view2.findViewById(R.id.tv_7);
        final TextView tv8 = view2.findViewById(R.id.tv_8);
        final TextView tv9 = view2.findViewById(R.id.tv_9);
        final TextView tv10 = view2.findViewById(R.id.tv_10);
      //  final EditText et_facultyname = view2.findViewById(R.id.et_facultynamef);
      //  final EditText et_dept = view2.findViewById(R.id.et_deptf);

        final ScrollView sv_evaluate = view2.findViewById(R.id.scrollView2);
        date = (TextView)view2.findViewById(R.id.textView56);
        time = (TextView)view2.findViewById(R.id.textView50);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        final String dt = sdf.format(new Date());
        date.setText(sdf.format(new Date()));
        SimpleDateFormat sdf1 = new SimpleDateFormat("K:mm a");
        String currentime = sdf1.format(new Date());
        time.setText(currentime);

        //ratingBar.setRating(0.0);
        ratingBar1 = view2.findViewById(R.id.ratingBar1);
        ratingBar2 = view2.findViewById(R.id.ratingBar2);
        ratingBar3 = view2.findViewById(R.id.ratingBar3);
        ratingBar4 = view2.findViewById(R.id.ratingBar4);
        ratingBar5 = view2.findViewById(R.id.ratingBar5);
        ratingBar6 = view2.findViewById(R.id.ratingBar6);
        ratingBar7 = view2.findViewById(R.id.ratingBar7);
        ratingBar8 = view2.findViewById(R.id.ratingBar8);
        ratingBar9 = view2.findViewById(R.id.ratingBar9);
        ratingBar10 = view2.findViewById(R.id.ratingBar10);
        final LinearLayout linearLayout = view2.findViewById(R.id.linearLayout);

        linearLayout.setVisibility(View.GONE);
        final Button btn_view = view2.findViewById(R.id.button);
        btn_view.setVisibility(View.VISIBLE);

        //</editor-fold>

        final Button btn_next = view2.findViewById(R.id.btn_next);
        final ImageView progressBar = view2.findViewById(R.id.loadinggif);
        final TextView  tv_category = view2.findViewById(R.id.tv_category);
        final TextView et_subject = view2.findViewById(R.id.et_subject);
        final TextView et_namef = view2.findViewById(R.id.et_facultynamef);
        final TextView et_deptf = view2.findViewById(R.id.et_deptf);
        progressBar.setVisibility(View.INVISIBLE);
        btn_next.setText("NEXT");
       // btn_next.setText("SUBMIT");
        activityBottomNav = (ActivityBottomNav) this.getActivity();
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), ""+activityBottomNav.fid, Toast.LENGTH_SHORT).show();
                // uya na
                if(activityBottomNav.fid == null){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("PLEASE SELECT THE FACULTY TO BE EVALUATED.");
                    builder1.setCancelable(true);

                    builder1.setNegativeButton(
                            "OKAY",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    activityBottomNav.bottomNavigationView.setSelectedItemId(R.id.evaluation);
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
                else
                    {
                    progressBar.setVisibility(View.VISIBLE);
                    final Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.INVISIBLE);
                            et_namef.setText(activityBottomNav.faculty);
                            et_deptf.setText(activityBottomNav.department);
                            et_subject.setText(activityBottomNav.code);
                            linearLayout.setVisibility(View.VISIBLE);
                            btn_view.setVisibility(View.GONE);
                        }
                    }, 3000); // 4 seconds
                }
            }
        });

        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r1 = (float) ratingBar1.getRating();
                ra1 = String.valueOf(r1);
            }
        });
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r2 = (float) ratingBar2.getRating();
                ra2 = String.valueOf(r2);
            }
        });
        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r3 = (float) ratingBar3.getRating();
                ra3 = String.valueOf(r3);
            }
        });
        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r4 = (float) ratingBar4.getRating();
                ra4 = String.valueOf(r4);
            }
        });
        ratingBar5.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r5 = (float) ratingBar5.getRating();
                ra5 = String.valueOf(r5);
            }
        });
        ratingBar6.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r6 = (float) ratingBar6.getRating();
                ra6 = String.valueOf(r6);
            }
        });
        ratingBar7.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r7 = (float) ratingBar7.getRating();
                ra7 = String.valueOf(r7);
            }
        });
        ratingBar8.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r8 = (float) ratingBar8.getRating();
                ra8 = String.valueOf(r8);
            }
        });
        ratingBar9.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r9 = (float) ratingBar9.getRating();
                ra9 = String.valueOf(r9);
            }
        });
        ratingBar10.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float r10 = (float) ratingBar10.getRating();
                ra10 = String.valueOf(r10);
            }
        });

       // final String datesq = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        //next button
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(btn_next.getText().toString().trim() == "NEXT"){
                            sv_evaluate.scrollTo(0,0);
                            Log.e("my_asset btn: ",btn_next.getText().toString().trim());
                            Response.Listener<String> res = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("my_asset resdown","");
                                    try {
                                        Log.e("my_asset string res",response);
                                        JSONObject jsonResponse = new JSONObject(response);
                                        Log.e("my_asset jsonResponse",String.valueOf(jsonResponse));
                                        boolean success = jsonResponse.getBoolean("success");

                                        if (success) {
                                            tv_category.setText(getResources().getString(R.string.e_title));
                                            tv1.setText(getResources().getString(R.string.e_q1));
                                            tv2.setText(getResources().getString(R.string.e_q2));
                                            tv3.setText(getResources().getString(R.string.e_q3));
                                            tv4.setText(getResources().getString(R.string.e_q4));
                                            tv5.setText(getResources().getString(R.string.e_q5));
                                            tv6.setText(getResources().getString(R.string.e_q6));
                                            tv7.setText(getResources().getString(R.string.e_q7));
                                            tv8.setText(getResources().getString(R.string.e_q8));
                                            tv9.setText(getResources().getString(R.string.e_q9));
                                            tv10.setText(getResources().getString(R.string.e_q10));
                                             //tv10.setVisibility(View.INVISIBLE);
                                           // ratingBar10.setVisibility(View.INVISIBLE);
                                            onRefreshRatingBar();
                                            btn_next.setText("CONTINUE");

                                        }
                                    } catch (JSONException e) {
                                        Log.e("Error",String.valueOf(e));
                                        e.printStackTrace();
                                    }
                                }
                            };

                            String code = "MGMNT";
                            String description = "Management";
                        //   Log.e("my_asset fid",activityBottomNav.fid);

                            QueryRatingManagement management = new QueryRatingManagement(code,description,activityBottomNav.SID,activityBottomNav.fid,activityBottomNav.sy_id,activityBottomNav.class_id,activityBottomNav.subject_id,ra1,ra2,ra3,ra4,ra5,ra6,ra7,ra8,ra9,ra10, res);
                            RequestQueue qq = Volley.newRequestQueue(getActivity());
                            qq.add(management);
                            Log.e("my_asset ra1",ra1);
                            Log.e("my_asset sy-id",activityBottomNav.sy_id);
                            Log.e("my_asset class_id",activityBottomNav.class_id);
                            Log.e("my_asset subject_id",activityBottomNav.subject_id);
                           // onStop();

                        }
//Continue button
                        else if(btn_next.getText().toString().trim() == "CONTINUE"){
                            sv_evaluate.scrollTo(0,0);
                            Log.e("my_asset btn: ",btn_next.getText().toString().trim());
                            Response.Listener<String> res = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");
                                        if (success) {
                                            tv_category.setText(getResources().getString(R.string.pepq_title));
                                            tv1.setText(getResources().getString(R.string.pepq_q1));
                                            tv2.setText(getResources().getString(R.string.pepq_q2));
                                            tv3.setText(getResources().getString(R.string.pepq_q3));
                                            tv4.setText(getResources().getString(R.string.pepq_q4));
                                            tv5.setText(getResources().getString(R.string.pepq_q5));
                                            tv6.setText(getResources().getString(R.string.pepq_q6));
                                            tv7.setText(getResources().getString(R.string.pepq_q7));
                                            tv8.setText(getResources().getString(R.string.pepq_q8));
                                            tv9.setText(getResources().getString(R.string.pepq_q9));
                                            tv10.setText(getResources().getString(R.string.pepq_q10));

                                           // tv9.setVisibility(View.GONE);
                                           // ratingBar9.setVisibility(View.GONE);
                                          //  tv10.setVisibility(View.GONE);
                                          //  ratingBar10.setVisibility(View.GONE);
                                            onRefreshRatingBar();
                                            btn_next.setText("SUBMIT");
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            String code = "EFFECT";
                            String description = "Effectiveness";
                           // Log.e("my_asset fid",activityBottomNav.fid);
                            QueryRatingManagement effect = new QueryRatingManagement(code,description,activityBottomNav.SID,activityBottomNav.fid,activityBottomNav.sy_id,activityBottomNav.class_id,activityBottomNav.subject_id,ra1,ra2,ra3,ra4,ra5,ra6,ra7,ra8,ra9,ra10, res);
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.add(effect);
                            //onStop();
                        }
//Submit btn
                        else if(btn_next.getText().toString().trim() == "SUBMIT"){
                           // onRefreshRatingBar();
                            sv_evaluate.scrollTo(0,0);
                            Log.e("my_asset btn: ",btn_next.getText().toString().trim());

                            Response.Listener<String> res = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");
                                        if (success) {
                                            InsertEvaluatedFaculty(activityBottomNav.SID,activityBottomNav.fid,activityBottomNav.sy_id,activityBottomNav.class_id,activityBottomNav.subject_id);

                                            Toast.makeText(getActivity(), "EVALUATION SAVED", Toast.LENGTH_SHORT).show();
                                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                                            builder1.setMessage("FACULTY EVALUATION COMPLETE \n DO YOU WANT TO VIEW EVALUATION REPORT?");
                                            builder1.setCancelable(true);

                                            builder1.setPositiveButton(
                                                    "Yes",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                            activityBottomNav.stat = "good";
                                                            activityBottomNav.bottomNavigationView.setSelectedItemId(R.id.nav_profile);
                                                        }
                                                    });
                                            builder1.setNegativeButton(
                                                    "No",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                            activityBottomNav.stat = "good";
                                                            activityBottomNav.bottomNavigationView.setSelectedItemId(R.id.nav_favorites);
                                                        }
                                                    });
                                            AlertDialog alert11 = builder1.create();
                                            alert11.show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            String code = "PEPQ";
                            String description = "Professional Ethics and Personal Qualities";
                            QueryRatingManagement effect = new QueryRatingManagement(code,description,activityBottomNav.SID,activityBottomNav.fid,activityBottomNav.sy_id,activityBottomNav.class_id,activityBottomNav.subject_id,ra1,ra2,ra3,ra4,ra5,ra6,ra7,ra8,ra9,ra10, res);
                            RequestQueue q = Volley.newRequestQueue(getActivity());
                            q.add(effect);
                        }
                    }
                }, 3000); // 4 seconds
            }
        });
        return view2;
    }

    public void onRefreshRatingBar(){
        float reset = 0.0f;
        ratingBar1.setRating(reset);
        ratingBar2.setRating(reset);
        ratingBar3.setRating(reset);
        ratingBar4.setRating(reset);
        ratingBar5.setRating(reset);
        ratingBar6.setRating(reset);
        ratingBar7.setRating(reset);
        ratingBar8.setRating(reset);
        ratingBar9.setRating(reset);
        ratingBar10.setRating(reset);

    }
    public void InsertEvaluatedFaculty(String SID,String fid, String sy_id, String class_id, String subject_id){
        Response.Listener<String> resp = new Response.Listener<String>() {
            @Override
                public void onResponse(String response) {
                    try
                    {   JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if(success){
                       //     Toast.makeText(getActivity().getApplicationContext(), "Comment Added Successfully!", Toast.LENGTH_SHORT).show();
                         //  onRefreshData();
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            };
            QueryEvaluatedFaculty evaluatedFaculty = new QueryEvaluatedFaculty(SID, fid, sy_id, class_id, subject_id, resp);
            RequestQueue queued = Volley.newRequestQueue(getActivity());
            queued.add(evaluatedFaculty);
    }

}
