package com.example.ipamss.MCFE;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class QueryEffectiveness extends StringRequest {

    private static final String Login_URL = "http://" + IPCONFIG + "/MCFE/Effectiveness.php";
    private Map<String, String> params;

    public QueryEffectiveness(String faculty, String acad_year, String rating1, String rating2, String rating3, String rating4, String rating5, String rating6, String rating7, String rating8, String rating9, String rating10
            ,String faculty_id,String stud_id, Response.Listener<String>listener) {
        super(Method.POST,Login_URL,listener,null);

        params = new HashMap<>();
        params.put("facultyname",faculty);
        params.put("acad_year",acad_year);
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
        params.put("stud_id",stud_id);
        params.put("faculty_id",faculty_id);


    }

    @Override
    public Map<String, String> getParams() { return params;
    }
}
