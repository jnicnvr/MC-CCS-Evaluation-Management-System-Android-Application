package com.example.ipamss.MCFE;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.ipamss.R;

import java.io.InputStream;



public class CustomListViewTest<View> extends ArrayAdapter<String>  {

    private String[] curriculum;
    private String[] year_level;
    private String[] section;
    private String[] code;
    private String[] subject;
    private String[] name;
    private String[] department;


    private String[] path;

    private Context context;
    Bitmap bitmap;

    public CustomListViewTest(Context context, String[] curriculum, String [] year_level, String [] section, String [] code, String [] subject, String [] name, String [] department, String []fid, String []_class_id, String []subject_id) {
        super(context, R.layout.layout, curriculum);
        Log.e("my_asset Custview","");
        this.context = context;
        this.curriculum = curriculum;
        this.year_level = year_level;
        this.section = section;
        this.code = code;
        this.subject = subject;
        this.name = name;
        this.department = department;

    }
    @NonNull
    @Override

    public android.view.View getView(int position, @NonNull android.view.View convertView, @NonNull ViewGroup parent){

        android.view.View r = convertView;
        ViewHolder viewHolder = null;

        if(r==null){
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            r = layoutInflater.inflate(R.layout.layout,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);

        }
        else{
            viewHolder = (ViewHolder)r.getTag();
        }
        viewHolder.tvw3.setText(department[position]);
        viewHolder.tvw4.setText(curriculum[position]+" "+year_level[position]+" "+section[position]);
        viewHolder.tvw2.setText(name[position]);
        viewHolder.tvw1.setText(curriculum[position]+year_level[position]+section[position]+" "+code[position]);

//        Log.e("nico",code[position]);
//        Log.e("nico", String.valueOf(position));
        //new CustomListViewTest.GetImageFromURL(viewHolder.ivw).execute(code[position]);

        return r;
    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        TextView tvw4;
        ImageView ivw;

        ViewHolder(android.view.View v){
            tvw1 = (TextView)v.findViewById(R.id.tvProject);
            tvw2 = (TextView)v.findViewById(R.id.tvLocation);
            tvw3 = (TextView)v.findViewById(R.id.tvEngr);
            tvw4 = (TextView)v.findViewById(R.id.tvcode);
            ivw = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap>
    {
        ImageView imgView;
        public GetImageFromURL(ImageView imgv){

            this.imgView = imgv;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay = url[0];
            bitmap = null;

            try{
                InputStream ist = new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(ist);

            }catch (Exception ex){
                ex.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void  onPostExecute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }

    }


}
