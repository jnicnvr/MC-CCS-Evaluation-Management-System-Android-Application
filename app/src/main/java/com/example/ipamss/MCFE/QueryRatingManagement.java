package com.example.ipamss.MCFE;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryRatingManagement extends StringRequest {
    //private static final String URL = "http://" + IPCONFIG + "/MCFE/ManagementEvaluation.php";
//    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/RatingManageEvaluation.php";
    private static final String URL = "http://" + IPCONFIG + "/MCFE/mc_evaluation/REST/RatingManageEvaluation.php";

    private Map<String, String> params;

    public QueryRatingManagement(String code, String description, String SID, String fid, String sy_id, String class_id, String subject_id, String rating1,String rating2,String rating3,String rating4,String rating5,String rating6,String rating7,String rating8,String rating9,String rating10,String end_at, Response.Listener<String> listener) {

        super(Method.POST,URL,listener,null);
        params = new HashMap<>();

        params.put("code",code);
        params.put("description",description);
        params.put("SID",SID);
        params.put("fid", fid);
        params.put("sy_id",sy_id);
        params.put("class_id",class_id);
        params.put("subject_id",subject_id);
        params.put("rating1",rating1);
        params.put("rating2",rating2);
        params.put("rating3",rating3);
        params.put("rating4",rating4);
        params.put("rating5",rating5);
        params.put("rating6",rating6);
        params.put("rating7",rating7);
        params.put("rating8",rating8);
        params.put("rating9",rating9);
        params.put("rating10",rating10);
        params.put("end_at",end_at);


    }

    @Override
    public Map<String, String> getParams() { return params; }
}
