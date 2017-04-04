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
public class ServerEduFragment extends Fragment {


    public ServerEduFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_server_home, null);
    }

}
