package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.htcompany.education.studentforgansu.R;

/**
 * 健康素质评价-健康水平
 * Created by wrb on 2016/11/29.
 */
public class OqeHealthFragmentAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    public OqeHealthFragmentAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return inflater.inflate(R.layout.oqehealthfragmentadapter_item,null);
    }
}
