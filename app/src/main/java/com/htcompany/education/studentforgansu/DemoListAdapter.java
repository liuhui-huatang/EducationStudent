package com.htcompany.education.studentforgansu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */
public class DemoListAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<String> datas;
    public DemoListAdapter(Context context,List<String> datas){
        this.context =context;
        this.datas =datas;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return inflater.inflate(R.layout.demolist_item,null);
    }
}
