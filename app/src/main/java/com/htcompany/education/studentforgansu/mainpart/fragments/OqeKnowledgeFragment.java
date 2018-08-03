package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.adapter.OqeKnowledgeFragmentAdapter;

/**
 * 综合素质评价-知识水平
 * Created by wrb on 2016/11/7.
 */
public class OqeKnowledgeFragment extends Fragment{
    private View view;
    private ListView oqehealth_lv;
    private OqeKnowledgeFragmentAdapter knowledgeFragmentAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oqehealth_fragment,container,false);
        initViews();
        return view;
    }
    public void initViews(){
        oqehealth_lv=(ListView)view.findViewById(R.id.oqehealth_lv);
        knowledgeFragmentAdapter = new OqeKnowledgeFragmentAdapter(getActivity());
        oqehealth_lv.setAdapter(knowledgeFragmentAdapter);
    }
}
