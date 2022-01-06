package com.example.ipamss.MCFE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipamss.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import static android.app.Activity.RESULT_OK;

public class EightFragment extends Fragment {
    TextView date;
    TextView time;
    Button Camera;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    // Declare globally
    private int position = -1;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    public static String asdf = "";
    public static String asdfs = "";
    public static final int REQUEST_CODE = 101;
    public static final int CAMERA_CODE = 102;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private Bitmap bitmap5;
    private Bitmap bitmap6;
    public int clickcount=0;
    public int clickcount1=0;
    ActivityBottomNav activityBottomNav;
    public EightFragment(){

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
            view = inflater.inflate(R.layout.eight_fragment, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
     //  final TextView txt2 = view.findViewById(R.id.textView27);
   //   final ViewPager viewPager = view.findViewById(R.id.viewPager1);
   //     final Button observed = view.findViewById(R.id.btn_observed);
   //     date = (TextView)view.findViewById(R.id.tv_date2);
    //    time = (TextView)view.findViewById(R.id.tv_time2);
     //   SimpleDateFormat sdf = new SimpleDateFormat("MM MM dd, yyyy");
     //   final String dt = sdf.format(new Date());
     //   date.setText(sdf.format(new Date()));
     //   SimpleDateFormat sdf1 = new SimpleDateFormat("K:mm a");
      //  String currentime = sdf1.format(new Date());
     //   time.setText(currentime);
//        final ScrollView sv_fifth = view.findViewById(R.id.sv_fifth);
        final ImageView img8 = view.findViewById(R.id.imageView8);
        final TextView txt21 = view.findViewById(R.id.textView21);
//        activityBottomNav = (ActivityBottomNav) this.getActivity();
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MabiniActivity) getActivity()).setViewPager(1);
            }
        });
        txt21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(intent);
            }
        });

//sv_fifth.setVisibility(View.GONE);

        return view;
    }

}
