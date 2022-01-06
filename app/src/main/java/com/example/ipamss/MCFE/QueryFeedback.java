package com.example.ipamss.MCFE;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryFeedback extends StringRequest {
    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/InsertFeedbacks.php";
    private Map<String, String> params;

    public QueryFeedback(String rates, String feedbacks, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("rates",rates);
        params.put("feedbacks",feedbacks);
    }

    @Override
    public Map<String, String> getParams() { return params; }
}