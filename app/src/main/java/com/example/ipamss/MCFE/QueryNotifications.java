package com.example.ipamss.MCFE;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryNotifications extends StringRequest {

    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/FetchUserNotifications.php";
    private Map<String, String> params;

    public QueryNotifications(String SID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        params = new HashMap<>();
        params.put("SID",SID);
    }
    @Override
    public Map<String, String> getParams() { return params; }
}
