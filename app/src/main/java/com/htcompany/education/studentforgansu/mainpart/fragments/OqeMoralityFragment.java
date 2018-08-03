package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.adapter.OqeMoralityFragmentAdapter;

/**
 * 综合素质评价-德育发展
 * Created by wrb on 2016/11/7.
 */
public class OqeMoralityFragment extends Fragment{
    private View view;
    private ListView oqemorality_lv;
    private OqeMoralityFragmentAdapter oqeMoralityFragmentAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oqemorality_fragment,container,false);
        initViews();
        return view;
    }
    public void initViews(){
        oqemorality_lv=(ListView)view.findViewById(R.id.oqemorality_lv);
        oqeMoralityFragmentAdapter = new OqeMoralityFragmentAdapter(getActivity());
        oqemorality_lv.setAdapter(oqeMoralityFragmentAdapter);
    }
}
