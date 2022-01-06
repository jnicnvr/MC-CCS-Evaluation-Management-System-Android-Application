package com.example.ipamss.MCFE;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.core.app.ActivityOptionsCompat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.ipamss.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import static android.app.Activity.RESULT_OK;
import static com.example.ipamss.MCFE.AAConstants.IPCONFIG;

public class FifthFragment1 extends Fragment {
    //<editor-fold desc="IPAMMS">
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
    //</editor-fold>

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
    public FifthFragment1(){

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
            view = inflater.inflate(R.layout.fifth_fragment_1, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        activityBottomNav = (ActivityBottomNav) this.getActivity();
        listView = (ListView) view.findViewById(R.id.lview);

            StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
            collectData();
            final CustomListView customListView = new CustomListView(getContext(),projectname, code, locatiom, engr, path);
            listView.setAdapter(customListView);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
//                activityBottomNav.bottomNavigationView.setSelectedItemId(R.id.nav_bug);
//                activityBottomNav.fullname = code[position] + " " + locatiom[position];
//                activityBottomNav.dept = engr[position];
//                activityBottomNav.fid = projectname[position];
//
//            }
//        });
        return view;
    }

    public void collectData(){
        //Connection
        try{
            URL url = new URL(urladdress);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());

        }catch (Exception ex){

            ex.printStackTrace();
        }
//content
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null){
                sb.append(line+ "\n");


            }
            is.close();
            result =sb.toString();

        }catch (Exception ex){
            ex.printStackTrace();
        }
//JSON
        try{
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            projectname = new String[ja.length()];
            code = new String[ja.length()];
            locatiom = new String[ja.length()];
            engr = new String[ja.length()];
            path = new String[ja.length()];

            for(int i=0; i<=ja.length(); i++){
                jo = ja.getJSONObject(i);
                projectname[i] = jo.getString("TID");
                code[i] = jo.getString("Fname");
                locatiom[i] = jo.getString("Lname");
                engr[i] = jo.getString("Department");
                path[i] = jo.getString("photo");

            }

        }catch (Exception ex){
            ex.printStackTrace();

        }

    }
    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMG_REQUEST);
    }
    private void askCameraPermissions(){
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},REQUEST_CODE);
        }
        else {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

                Toast.makeText(getActivity(), "asdjkasdljksksa", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void openCamera(){
        Intent intent = new Intent();

        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, CAMERA_CODE);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == CAMERA_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            if(asdf.equals("img1")){
                img1.setImageBitmap(image);
            } else if(asdf.equals("img2")){
                img2.setImageBitmap(image);
            }else if(asdf.equals("img3")){
                img3.setImageBitmap(image);
            }else if(asdf.equals("img4")){
                img4.setImageBitmap(image);
            }else if(asdf.equals("img5")){
                img5.setImageBitmap(image);
            }else if(asdf.equals("img6")){
                img6.setImageBitmap(image);
            }


        }

        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                bitmap2 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                bitmap3 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                bitmap4 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                bitmap5 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);
                bitmap6 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),path);

                if(asdf.equals("img1")){
                    img1.setImageBitmap(bitmap);
                } else if(asdf.equals("img2")){
                    img2.setImageBitmap(bitmap2);
                }else if(asdf.equals("img3")){
                    img3.setImageBitmap(bitmap3);
                }else if(asdf.equals("img4")){
                    img4.setImageBitmap(bitmap4);
                }else if(asdf.equals("img5")){
                    img5.setImageBitmap(bitmap5);
                }else if(asdf.equals("img6")){
                    img6.setImageBitmap(bitmap6);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private class LoadImage  extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public LoadImage(ImageView selectedImage) {
            this.imageView = selectedImage;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new java.net.URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            img1.setImageBitmap(bitmap);
        }
    }

}

