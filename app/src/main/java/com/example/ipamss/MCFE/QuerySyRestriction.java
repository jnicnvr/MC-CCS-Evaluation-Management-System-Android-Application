package com.example.ipamss.MCFE;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;


public class QuerySyRestriction extends StringRequest {

    //    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/FetchSyJson.php";
        private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/REST/FetchSy.php";

    private Map<String, String> params;

    public QuerySyRestriction(String status, Response.Listener<String> listener) {
        super(Request.Method.POST,URL, listener, null);
        params = new HashMap<>();
        params.put("status",status);
    }

    @Override
    public Map<String, String> getParams() { return params;
    }
}




