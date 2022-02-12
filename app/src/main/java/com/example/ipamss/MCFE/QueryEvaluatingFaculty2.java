package com.example.ipamss.MCFE;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryEvaluatingFaculty2 extends StringRequest {

//    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/FetchEvaluatingFaculty2.php";
    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/REST/FetchEvaluatingFaculty2.php";

    private Map<String, String> params;

    public QueryEvaluatingFaculty2(String SID, String sy_id, String class_id,Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        params = new HashMap<>();

        params.put("SID", SID);
        params.put("sy_id", sy_id);
        params.put("class_id", class_id);
        //params.put("class_id2", class_id2);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
