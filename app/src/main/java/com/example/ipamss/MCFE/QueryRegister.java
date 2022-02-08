package com.example.ipamss.MCFE;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryRegister extends StringRequest {

    //private static final String Login_URL = "http://" + IPCONFIG + "/MCFE/StudentRegister.php";

//    private static final String Login_URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/RegisterStudent.php";
    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/RESTPostAPI.php";
        private static final String REST_url = "http://45.76.152.7:8080/api/student/";

    private Map<String, String> params;

    public QueryRegister(String SID,String Fname,String Lname,String Age,String Sex,String Course,String username,String password, String year_level, String section, Response.Listener<String>listener) {
        super(Request.Method.POST,URL,listener,null);

        params = new HashMap<>();
        params.put("endpoint",REST_url);
        params.put("SID",SID);
        params.put("Fname",Fname);
        params.put("Lname",Lname);
        params.put("Age",Age);
        params.put("Sex",Sex);
        params.put("Course",Course);
        params.put("username",username);
        params.put("password",password);

//        Addtional column in db..   10-16-21
        params.put("year_level",year_level);
        params.put("section",section);
    }

    public Map<String, String> getParams() { return params; }
}
