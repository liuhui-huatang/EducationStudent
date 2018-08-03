package com.htcompany.education.studentforgansu.mainpart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htcompany.education.studentforgansu.R;

/**
 * 没用
 * Created by Administrator on 2016/11/4.
 */
public class MainLeftMenuFragment extends Fragment{
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.residmenu_left_contentview,container,false);
//        ExpandableListView expandableListView = (ExpandableListView)view.findViewById(R.id.leftmenu_listview);
//        MainLeftMenuAdpter mainLeftMenuAdpter = new MainLeftMenuAdpter(getActivity(), MenuLeftDatas.getParentDatas(),MenuLeftDatas.getChildDatas());
//        expandableListView.setAdapter(mainLeftMenuAdpter);
        return view;
    }
}
