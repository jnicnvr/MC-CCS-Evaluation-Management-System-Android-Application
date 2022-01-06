package com.example.ipamss.MCFE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONObject;

import java.io.BufferedInputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class NineFragment extends Fragment {
    //<editor-fold desc="MCFE">
    ActivityBottomNav activityBottomNav;
    String urladdress = "http://" + IPCONFIG + "/MCFE/SearchEngr.php";
    String []projectname;
    String []code;
    String []locatiom;
    String []engr;
    String []path;
    ListView listView;
    BufferedInputStream is;
    String line = null;
    String result = null;
    TextView codes;
    String ide = "NOPE";
    ImageView imageview58;
    NotificationBadge mBadge;
    private int count = 1;
    //</editor-fold>
    public NineFragment(){

    }
    private static View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.nine_fragment, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        activityBottomNav = (ActivityBottomNav) this.getActivity();
      //  final ScrollView sv_fifth = view.findViewById(R.id.sv_fifth);
       // sv_fifth.setVisibility(View.INVISIBLE);

        final ScrollView sv_3 = view.findViewById(R.id.sv_new);
        sv_3.setVisibility(View.GONE);

        //<editor-fold desc="TexView">
        final TextView m1 = view.findViewById(R.id.m1);
        final TextView r1 = view.findViewById(R.id.r1);
        final TextView m2 = view.findViewById(R.id.m2);
        final TextView r2 = view.findViewById(R.id.r2);
        final TextView m3 = view.findViewById(R.id.m3);
        final TextView r3 = view.findViewById(R.id.r3);
        final TextView m4 = view.findViewById(R.id.m4);
        final TextView r4 = view.findViewById(R.id.r4);
        final TextView m5 = view.findViewById(R.id.m_q3);
        final TextView r5 = view.findViewById(R.id.rm_q4);
        final TextView m6 = view.findViewById(R.id.m_q1);
        final TextView r6 = view.findViewById(R.id.rm_q1);
        final TextView m7 = view.findViewById(R.id.m7);
        final TextView r7 = view.findViewById(R.id.r7);
        final TextView m8 = view.findViewById(R.id.m8);
        final TextView r8 = view.findViewById(R.id.r8);
        final TextView m9 = view.findViewById(R.id.m9);
        final TextView r9 = view.findViewById(R.id.r9);
        final TextView m10 = view.findViewById(R.id.m_q2);
        final TextView r10 = view.findViewById(R.id.rm_q2);

        final TextView ef1 = view.findViewById(R.id.ef1);
        final TextView efr1 = view.findViewById(R.id.efr1);
        final TextView ef2 = view.findViewById(R.id.ef2);
        final TextView efr2 = view.findViewById(R.id.efr2);
        final TextView ef3 = view.findViewById(R.id.ef3);
        final TextView efr3 = view.findViewById(R.id.efr3);
        final TextView ef4 = view.findViewById(R.id.ef4);
        final TextView efr4 = view.findViewById(R.id.efr4);
        final TextView ef5 = view.findViewById(R.id.ef5);
        final TextView efr5 = view.findViewById(R.id.efr5);
        final TextView ef6 = view.findViewById(R.id.ef6);
        final TextView efr6 = view.findViewById(R.id.efr6);
        final TextView ef7 = view.findViewById(R.id.ef7);
        final TextView efr7 = view.findViewById(R.id.efr7);
        final TextView ef10 = view.findViewById(R.id.ef10);
        final TextView efr10 = view.findViewById(R.id.efr10);
        final TextView ef9 = view.findViewById(R.id.ef9);
        final TextView efr9 = view.findViewById(R.id.efr9);

        final TextView et1 = view.findViewById(R.id.et1);
        final TextView etr1 = view.findViewById(R.id.etr1);
        final TextView et2 = view.findViewById(R.id.et2);
        final TextView etr2 = view.findViewById(R.id.etr2);
        final TextView et3 = view.findViewById(R.id.et3);
        final TextView etr3 = view.findViewById(R.id.etr3);
        final TextView et4 = view.findViewById(R.id.et4);
        final TextView etr4 = view.findViewById(R.id.etr4);
        final TextView et5 = view.findViewById(R.id.et5);
        final TextView etr5 = view.findViewById(R.id.etr5);
        final TextView et6 = view.findViewById(R.id.et6);
        final TextView etr6 = view.findViewById(R.id.etr6);
        final TextView et7 = view.findViewById(R.id.et7);
        final TextView etr7 = view.findViewById(R.id.etr7);
        final TextView et8 = view.findViewById(R.id.et8);
        final TextView etr8 = view.findViewById(R.id.etr8);
        final TextView et9 = view.findViewById(R.id.et9);
        final TextView etr9 = view.findViewById(R.id.etr9);
        final EditText et_faculty = view.findViewById(R.id.et_faculty_name);
        final  EditText et_student = view.findViewById(R.id.et_student_name);
        //</editor-fold>

        final Button btn_view = view.findViewById(R.id.btn_view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityBottomNav.stat == "good"){
                    et_faculty.setText(activityBottomNav.faculty);
                    et_student.setText(activityBottomNav.student_name);
                    //<editor-fold desc="Retrieve Effective">
                    Log.e("my_asset 9frag", "im here in 9frag");
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    //Toast.makeText(getActivity(), "POteks", Toast.LENGTH_SHORT).show();
                                    String rating1 = jsonResponse.getString("rating1");
                                    String rating2 = jsonResponse.getString("rating2");
                                    String rating3 = jsonResponse.getString("rating3");
                                    String rating4 = jsonResponse.getString("rating4");
                                    String rating5 = jsonResponse.getString("rating5");
                                    String rating6 = jsonResponse.getString("rating6");
                                    String rating7 = jsonResponse.getString("rating7");
                                    String rating8 = jsonResponse.getString("rating8");
                                    String rating9 = jsonResponse.getString("rating9");

                                    efr1.setText(rating1);
                                    efr2.setText(rating2);
                                    efr3.setText(rating3);
                                    efr4.setText(rating4);
                                    efr5.setText(rating5);
                                    efr6.setText(rating6);
                                    efr7.setText(rating7);
                                    efr9.setText(rating8);
                                    efr10.setText(rating9);

                                } else {

                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    };
                    QueryRetrieveActivity retrieve = new QueryRetrieveActivity(activityBottomNav.id, activityBottomNav.fid, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(retrieve);
                    //</editor-fold>
                    //<editor-fold desc="Retrieve Ethics">
                    Response.Listener<String> responseListener1 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    //Toast.makeText(getActivity(), "POteks", Toast.LENGTH_SHORT).show();
                                    String rating1 = jsonResponse.getString("rating1");
                                    String rating2 = jsonResponse.getString("rating2");
                                    String rating3 = jsonResponse.getString("rating3");
                                    String rating4 = jsonResponse.getString("rating4");
                                    String rating5 = jsonResponse.getString("rating5");
                                    String rating6 = jsonResponse.getString("rating6");
                                    String rating7 = jsonResponse.getString("rating7");
                                    String rating8 = jsonResponse.getString("rating8");
                                    String rating9 = jsonResponse.getString("rating9");

                                    etr1.setText(rating1);
                                    etr2.setText(rating2);
                                    etr3.setText(rating3);
                                    etr4.setText(rating4);
                                    etr5.setText(rating5);
                                    etr6.setText(rating6);
                                    etr7.setText(rating7);
                                    etr8.setText(rating8);
                                    etr9.setText(rating9);

                                } else {

                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    };
                    QueryRetrieve1Activity retrieve1 = new QueryRetrieve1Activity(activityBottomNav.id, activityBottomNav.fid, responseListener1);
                    RequestQueue queue1 = Volley.newRequestQueue(getActivity());
                    queue1.add(retrieve1);
                    //</editor-fold>
                    //<editor-fold desc="Management">
                    Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                JSONObject jsonResponse = new JSONObject(response);
                                Log.e("my_asset 9Frag v Eval", response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    //Toast.makeText(getActivity(), "POteks", Toast.LENGTH_SHORT).show();
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

                                    r1.setText(rating1);
                                    r2.setText(rating2);
                                    r3.setText(rating3);
                                    r4.setText(rating4);
                                    r5.setText(rating5);
                                    r6.setText(rating6);
                                    r7.setText(rating7);
                                    r8.setText(rating8);
                                    r9.setText(rating9);
                                    r10.setText(rating10);
                                    sv_3.setVisibility(View.VISIBLE);
                                    btn_view.setVisibility(View.INVISIBLE);
                                } else {

                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    };
                    QueryRetrieve2Activity retrieve2 = new QueryRetrieve2Activity(activityBottomNav.id, activityBottomNav.fid, responseListener2);
                    RequestQueue queue2 = Volley.newRequestQueue(getActivity());
                    queue2.add(retrieve2);
                    //</editor-fold>

                    //<editor-fold desc="Setting Content">
                    m1.setText(""+getResources().getString(R.string.m_1));
                    m2.setText(""+getResources().getString(R.string.m_2));
                    m3.setText(""+getResources().getString(R.string.m_3));
                    m4.setText(""+getResources().getString(R.string.m_4));
                    m5.setText(""+getResources().getString(R.string.m_5));
                    m6.setText(""+getResources().getString(R.string.m_6));
                    m7.setText(""+getResources().getString(R.string.m_7));
                    m8.setText(""+getResources().getString(R.string.m_8));
                    m9.setText(""+getResources().getString(R.string.m_9));
                    m10.setText(""+getResources().getString(R.string.m_10));

                    ef1.setText(""+getResources().getString(R.string.ef_1));
                    ef2.setText(""+getResources().getString(R.string.ef_2));
                    ef3.setText(""+getResources().getString(R.string.ef_3));
                    ef4.setText(""+getResources().getString(R.string.ef_4));
                    ef5.setText(""+getResources().getString(R.string.ef_5));
                    ef6.setText(""+getResources().getString(R.string.ef_6));
                    ef7.setText(""+getResources().getString(R.string.ef_7));
                    ef9.setText(""+getResources().getString(R.string.ef_8));
                    ef10.setText(""+getResources().getString(R.string.ef_9));

                    et1.setText(""+getResources().getString(R.string.et_1));
                    et2.setText(""+getResources().getString(R.string.et_2));
                    et3.setText(""+getResources().getString(R.string.et_3));
                    et4.setText(""+getResources().getString(R.string.et_4));
                    et5.setText(""+getResources().getString(R.string.et_5));
                    et6.setText(""+getResources().getString(R.string.et_6));
                    et7.setText(""+getResources().getString(R.string.et_7));
                    et8.setText(""+getResources().getString(R.string.et_8));
                    et9.setText(""+getResources().getString(R.string.et_9));
                    //</editor-fold>
                }
                else
                    {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setMessage("YOU NEED TO CONDUCT EVALUATION FIRST TO VIEW EVALUATION REPORT");
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
            }
        });
        return view;
    }

}

