package com.example.ipamss.MCFE;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryRatingFetch extends StringRequest {

//    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/FetchFacultyRating.php";
    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/REST/FetchFacultyRating.php";

    private Map<String, String> params;

    public QueryRatingFetch(String SID, String fid, String sy_id, String class_id, String subject_id, String code, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);
        params = new HashMap<>();

        params.put("SID",SID);
        params.put("fid",fid);
        params.put("sy_id",sy_id);
        params.put("class_id",class_id);
        params.put("subject_id",subject_id);
        params.put("code",code);

    }

    @Override
    public Map<String, String> getParams() { return params; }
}
