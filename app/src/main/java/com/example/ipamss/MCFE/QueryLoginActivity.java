package com.example.ipamss.MCFE;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryLoginActivity extends StringRequest {
//    private static final String Login_URL = "http://" + IPCONFIG + "/MCFE/userslogin.php";
//    private static final String Login_URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/userslogin.php";
    private static final String Login_URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/REST/StudentLogin.php";
    private static final String REST_url = "http://45.76.152.7:8080/api/auth/login";
    private Map<String, String> params;

    public QueryLoginActivity(String username, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, Login_URL, listener, null);

        params = new HashMap<>();
        params.put("endpoint",REST_url);
        params.put("username", username);
        params.put("password", password);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
