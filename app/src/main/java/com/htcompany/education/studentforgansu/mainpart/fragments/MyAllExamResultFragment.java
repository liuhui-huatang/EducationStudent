package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htcompany.education.studentforgansu.R;

/**
 * 综合成绩
 * Created by wrb on 2016/10/20.
 */
public class MyAllExamResultFragment extends Fragment {
   private  View allViews;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        allViews= inflater.inflate(R.layout.myexamresultall_fragment,null);
        return allViews;
    }
}
