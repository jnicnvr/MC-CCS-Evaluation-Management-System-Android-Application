package com.example.ipamss.MCFE;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryRetrieve2Activity extends StringRequest {
    private static final String Login_URL = "http://" + IPCONFIG + "/MCFE/retrieveEvaluation2.php";
    private Map<String, String> params;

    public QueryRetrieve2Activity(String student_id, String faculty_id, Response.Listener<String> listener) {
        super(Method.POST, Login_URL, listener, null);
        params = new HashMap<>();

        params.put("student_id", student_id);
        params.put("faculty_id", faculty_id);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
