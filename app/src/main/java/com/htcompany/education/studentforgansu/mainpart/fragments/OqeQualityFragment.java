package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htcompany.education.studentforgansu.R;

/**
 * 综合素质评价-身心素质
 * Created by wrb on 2016/11/7.
 */
public class OqeQualityFragment extends Fragment{
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oqehealth_fragment,container,false);
        return view;
    }
}
