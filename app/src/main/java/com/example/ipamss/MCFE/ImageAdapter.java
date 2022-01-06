package com.example.ipamss.MCFE;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipamss.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter {

    private Context mContext;
//    private int[]mImageIds = new int[]{R.drawable.garbagetrycknewpng,R.drawable.environmentnewpng,R.drawable.safetypersonnelnewpng,R.drawable.heavyequipnewpng,
//            R.drawable.safetypersonnelnewpng,R.drawable.firstaidkitnewpng};
    public String[] texts = {"Waster Materials are collected and transported to approved dumps.",
            "Compliance to Environmental Clearance Certificate requirements",
            "Qualified Safety and Health personnel deployed and detailed at site",
            "Heavy Equipment operated by certified/qualified operators only",
            "Appropriate personal protective equipment like helmets, safety shoes, eye protection provided and worn by all worker staff",
            "First aid equipments are fully equipped and ready to use"};


    ImageAdapter (Context context){
        mContext = context;
    }

    //@Override
//    public int getCount() {
//        return mImageIds.length;
//    }

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
//        imageView.setImageResource(mImageIds[position]);
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
