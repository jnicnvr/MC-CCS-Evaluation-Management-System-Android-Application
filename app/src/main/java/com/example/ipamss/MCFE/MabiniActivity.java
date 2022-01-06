package com.example.ipamss.MCFE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.ipamss.R;

public class MabiniActivity extends AppCompatActivity {
    private SectionStatePageAdapter smSectionStatePageAdapter;
    private ViewPager smViewPager;
    private boolean enabled;
    //You need to add the Fragment here to add into the Activity Sub'

    public com.example.ipamss.MCFE.SixthFragment SixthFragment = new SixthFragment();
    public com.example.ipamss.MCFE.SevenFragment SevenFragment = new SevenFragment();
    public com.example.ipamss.MCFE.EightFragment EightFragment = new EightFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mabini);
        smSectionStatePageAdapter = new SectionStatePageAdapter(getSupportFragmentManager());
        smViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(smViewPager);

        smViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                smViewPager.setCurrentItem(smViewPager.getCurrentItem());
                return true;
            }
        });
    }
    private void setupViewPager(ViewPager viewPager){

        SectionStatePageAdapter adapter = new SectionStatePageAdapter(getSupportFragmentManager());
        adapter.addFragment(EightFragment, "EightFragment");
        adapter.addFragment(SixthFragment, "SixthFragment");
        adapter.addFragment(SevenFragment, "SevenFragment");
        // adapter.addFragment(fourthFragment, "FourthFragment");
        viewPager.setAdapter(adapter);
    }
    public void setViewPager(int fragmentnumber){
        smViewPager.setCurrentItem(fragmentnumber);
    }
}