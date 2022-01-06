package com.example.ipamss.MCFE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;


public class SubjectsFragment extends Fragment {
    //    String urladdress = "http://" + IPCONFIG + "/MCFE/SearchEngr.php";
    String urladdress = "http://" + IPCONFIG + "/MCFE/mc_evaluation/FetchEvaluatingFaculty.php";
    //   String urladdress = "http://" + IPCONFIG + "/MCFE/mc_evaluation/FetchEvaluatingFacultyTest.php";



    public String[] curriculum;
    public String[] year_level;
    public String[] section;
    public String[] code;
    public String[] subject;
    public String[] name;
    public String[] department;

    public String []fid;
    public String []_class_id;
    public String []subject_id;



    BufferedInputStream is;
    String line = null;
    String result = null;

    ActivityBottomNav activityBottomNav;
    ListView listView;
    private static View view;

    public String SID;
    public String class_id;
    public String sy_id;

    SharedPreferences sy_pref;
    SharedPreferences student_pref;
    String pref_SID;
    String sy_prefs;
    String pref_class_id;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        sy_pref =  this.getActivity().getSharedPreferences("school_year", MODE_PRIVATE);
        student_pref = this.getActivity().getSharedPreferences("student_data", MODE_PRIVATE);

        sy_prefs = sy_pref.getString("current_sy",null);
        pref_SID = student_pref.getString("SID",null);
        pref_class_id = student_pref.getString("class_id",null);

        Intent get = getActivity().getIntent();

        SID = get.getStringExtra("SID");
        class_id = get.getStringExtra("class_id");
        sy_id = get.getStringExtra("sy_id");

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_subjects, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        activityBottomNav = (ActivityBottomNav) this.getActivity();
        listView = (ListView) view.findViewById(R.id.lview);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        onLoadFaculty(new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(JSONArray response) {
                Log.e("my_asset onResponse",String.valueOf(response));
              //  Toast.makeText(getContext(), "Something wrong"+response, Toast.LENGTH_SHORT).show();
                 JSONObject jo = null;
               // String[] _res = new String[response.length()];
               boolean success = false;
                try{
                    jo = response.getJSONObject(0);
//                    Log.e("Nico jo",String.valueOf(jo));
                    success = jo.getBoolean("success");
                }catch (Exception err){
                    err.printStackTrace();
                }
                Log.e("Nico success", String.valueOf(success));
                 if(success){
                        curriculum = new String[response.length()];
                        year_level = new String[response.length()];
                        section = new String[response.length()];
                        code = new String[response.length()];
                        subject = new String[response.length()];
                        name = new String[response.length()];
                        department = new String[response.length()];
                        fid = new String[response.length()];
                        _class_id = new String[response.length()];
                        subject_id = new String[response.length()];

                        try{
                            for(int i=0; i<=response.length() - 1; i++){
                                jo = response.getJSONObject(i);

                                curriculum[i] = jo.getString("curriculum");
                                year_level[i] = jo.getString("year_level");
                                section[i] = jo.getString("section");
                                code[i] = jo.getString("code");
                                subject[i] = jo.getString("subject");
                                name[i] = jo.getString("name");
                                department[i] = jo.getString("department");
                                fid[i] = jo.getString("fid");
                                _class_id[i] = jo.getString("class_id");
                                subject_id[i] = jo.getString("subject_id");
                                //ok nalalamanan
                                // passData(curriculum);
                            }

                        }catch (Exception err){
                            err.printStackTrace();
                        }
                     onLoadListFaculty(curriculum, year_level, section,code,subject,name,department,fid,_class_id,subject_id);
                    }
                 else{
                     Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Found no Data!",Toast.LENGTH_LONG);
                     toast.setGravity(Gravity.CENTER, 0, 0);
                     toast.show();
                    }
            }
        });

        return view;

    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(JSONArray response);
    }

        //load list of faculty
    public void onLoadListFaculty(String []curriculum,String []year_level, String []section, String []code, String []subject, String []name, String []department, String []fid, String []_class_id, String []subject_id){
        final CustomListViewTest customListViewTest = new CustomListViewTest(getContext(),curriculum, year_level, section, code, subject, name, department, fid, _class_id, subject_id);
        listView.setAdapter(customListViewTest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Log.e("Nico position",String.valueOf(position));

                activityBottomNav.bottomNavigationView.setSelectedItemId(R.id.nav_bug);
                activityBottomNav._class = ""+curriculum[position]+year_level[position]+section[position];
                activityBottomNav.department = department[position];
                activityBottomNav.faculty = name[position];
                activityBottomNav.code = code[position];
                activityBottomNav.sy_id = sy_prefs;
                activityBottomNav.fid = fid[position];
                activityBottomNav.class_id = _class_id[position];
                activityBottomNav.subject_id = subject_id[position];
                activityBottomNav.SID = pref_SID;
               // onStop();
                //set new val in activity bottom
            }
        });
    }
 /*   public void onStop() {
        super.onStop();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {

            fm.popBackStack();
        }
    }*/

    public void onLoadFaculty(final VolleyResponseListener volleyResponseListener){
        Response.Listener<String> res = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    Log.e("Nico Res", response);
                    JSONArray ja = new JSONArray(response);
                    Log.e("Nico onLoadFaculty res",String.valueOf(ja));
                    volleyResponseListener.onResponse(ja);
                }catch (Exception err){
                    err.printStackTrace();
                }
            }
        };


        //String pref_student_name = student_pref.getString("student_name",null);

        Log.e("Nico pref sy_id", sy_prefs);
        Log.e("Nico pref SID", pref_SID);
        Log.e("Nico pref class_id", pref_class_id);

        QueryEvaluatingFaculty2 evaluatingFaculty = new QueryEvaluatingFaculty2(pref_SID, sy_prefs, pref_class_id, res);
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(evaluatingFaculty);
    }
}