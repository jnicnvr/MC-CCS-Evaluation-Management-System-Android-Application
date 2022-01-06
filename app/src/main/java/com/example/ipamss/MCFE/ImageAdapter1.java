package com.example.ipamss.MCFE;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipamss.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter1 extends PagerAdapter {

    private Context mContext;
//    private int[]mImageIds = new int[]{R.drawable.covidcrop1,R.drawable.maskpng,R.drawable.aaa,R.drawable.temppng,R.drawable.spitpng,R.drawable.coughpng};
    public String[] texts = {"Social Distancing",
            "Wearing of Face mask",
            "Washing Area with anti-bacterial soap is available for the workers",
            "Temperature check before entering the site",
            "Prohibition of Spitting",
            "Practice Proper coughing etiquette"};


    ImageAdapter1(Context context){
        mContext = context;
    }

    //@Override
    //public int getCount() {
       //return mImageIds.length;
 //   }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
     //   imageView.setImageResource(mImageIds[position]);
        TextView txt2 = new TextView(mContext);
        //txt2.setText(texts[position]+1);
        container.addView(imageView,0);
        //Toast.makeText(mContext, ""+texts[position], Toast.LENGTH_SHORT).show();
        return imageView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
