package com.example.ipamss.MCFE;

import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ipamss.R;

public class FifthFragment extends Fragment {

    ActivityBottomNav activityBottomNav;
    public FifthFragment(){

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
            view = inflater.inflate(R.layout.fifth_fragment, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        final Button btn_getStart = view.findViewById(R.id.btn_feedback);
        activityBottomNav = (ActivityBottomNav) this.getActivity();

        btn_getStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityBottomNav.bottomNavigationView.setSelectedItemId(R.id.evaluation);
            }
        });
        return view;
    }


}
