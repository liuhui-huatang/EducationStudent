package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.adapter.OqeSkillsFragmentAdapter;

/**
 * 综合素质评价-职业技能
 * Created by wrb on 2016/11/7.
 */
public class OqeSkillsFragment extends Fragment{
    private View view;
    private OqeSkillsFragmentAdapter skillsFragmentAdapter;
    private ListView oqeskills_lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.oqeskills_fragment,container,false);
        initViews();
        return view;
    }

    public void initViews(){
        oqeskills_lv = (ListView)view.findViewById(R.id.oqeskills_lv);
        skillsFragmentAdapter = new OqeSkillsFragmentAdapter(getActivity());
        oqeskills_lv.setAdapter(skillsFragmentAdapter);
    }
}
