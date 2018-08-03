package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.htcompany.education.studentforgansu.R;

/**
 * 假期要事记录适配器
 * Created by wrn on 2016/11/1.
 */
public class HolidayImportantAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    public HolidayImportantAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 8;
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

        return inflater.inflate(R.layout.holidayimportant_list_item,parent,false);
    }
}
