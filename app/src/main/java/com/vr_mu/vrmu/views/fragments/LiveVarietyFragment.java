package com.vr_mu.vrmu.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vr_mu.vrmu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveVarietyFragment extends Fragment {


    public LiveVarietyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_variety, container, false);
    }

}
