package com.example.ipamss.MCFE;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryEvaluatingFaculty extends StringRequest {

    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/FetchEvaluatingFaculty.php";
    private Map<String, String> params;

    public QueryEvaluatingFaculty(String class_id, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);

        params = new HashMap<>();
        params.put("class_id", class_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
