package com.example.ipamss.MCFE;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.ipamss.R;

import java.io.InputStream;

public class CustomListView<View> extends ArrayAdapter<String> {
    private String[] projectname;
    private String[] code;
    private String[] location;
    private String[] path;
    private String[] engr;
    private Context context;
    Bitmap bitmap;

    public CustomListView(Context context, String[] projectname, String [] code, String [] location, String [] engr, String [] path) {
        super(context, R.layout.layout, projectname);
        this.context = context;
        this.projectname = projectname;
        this.code = code;
        this.location = location;
        this.engr = engr;
        this.path = path;


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
            viewHolder.tvw3.setText(projectname[position]);
            viewHolder.tvw4.setText(code[position]);
            viewHolder.tvw1.setText(code[position]+" "+location[position]);
            viewHolder.tvw2.setText(engr[position]);
            new GetImageFromURL(viewHolder.ivw).execute(path[position]);


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
