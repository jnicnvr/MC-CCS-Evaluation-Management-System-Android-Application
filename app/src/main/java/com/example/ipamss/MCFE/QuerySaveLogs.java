package com.example.ipamss.MCFE;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QuerySaveLogs extends StringRequest {

//    private static final String Login_URL = "http://" + IPCONFIG + "/MCFE/InsertLogs.php";
    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/REST/InsertLogs.php";

    private Map<String, String> params;

    public QuerySaveLogs(String fullname, String activity, Response.Listener<String>listener) {
        super(Method.POST,URL,listener,null);

        params = new HashMap<>();
        params.put("project_user",fullname);
        params.put("activity",activity);

    }

    @Override
    public Map<String, String> getParams() { return params;
    }
}
