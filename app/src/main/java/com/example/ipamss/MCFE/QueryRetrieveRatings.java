package com.example.ipamss.MCFE;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryRetrieveRatings extends StringRequest {
    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/FetchRating.php";
    private Map<String, String> params;

    public QueryRetrieveRatings(Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
    }
}
