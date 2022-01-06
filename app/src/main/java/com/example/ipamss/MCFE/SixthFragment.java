package com.example.ipamss.MCFE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipamss.R;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;
import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class SixthFragment extends Fragment {

    //<editor-fold desc="MCFE">
    ActivityBottomNav activityBottomNav;
    String urladdress = "http://" + IPCONFIG + "/MCFE/SearchEngr.php";
    String []projectname;
    String []code;
    String []locatiom;
    String []engr;
    String []path;
    ListView listView;
    BufferedInputStream is;
    String line = null;
    String result = null;
    TextView codes;
    String ide = "NOPE";
    ImageView imageview58;
    NotificationBadge mBadge;
    private int count = 1;
    //</editor-fold>
    public SixthFragment(){

    }
    private static View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.sixth_fragment, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
       // activityBottomNav = (ActivityBottomNav) this.getActivity();

        final ScrollView sc_fifth = view.findViewById(R.id.sv_fifth);
        sc_fifth.setVisibility(View.INVISIBLE);
        final ImageView img8 = view.findViewById(R.id.imageView8);
        final ImageView img10= view.findViewById(R.id.imageView10);
        final TextView txt20 = view.findViewById(R.id.textView20);
//        activityBottomNav = (ActivityBottomNav) this.getActivity();
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MabiniActivity) getActivity()).setViewPager(2);
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MabiniActivity) getActivity()).setViewPager(0);
            }
        });
        txt20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}

