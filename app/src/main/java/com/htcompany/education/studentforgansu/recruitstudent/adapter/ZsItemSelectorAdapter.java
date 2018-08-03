package com.htcompany.education.studentforgansu.recruitstudent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.recruitstudent.entity.ZSSHEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/22.
 */
public class ZsItemSelectorAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ZSSHEntity> datas;
    public ZsItemSelectorAdapter(Context context,List<ZSSHEntity> datas){
        this.context = context;
        this.datas = datas;
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
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView =inflater.inflate(R.layout.zsitemselectoradapter_item,null);
            vh.zslvitem_name_tv = (TextView)convertView.findViewById(R.id.zslvitem_name_tv);
            convertView.setTag(vh);
        }else {
            vh = (ViewHodler) convertView.getTag();
        }
        vh.zslvitem_name_tv.setText(datas.get(position).getValue());
        return convertView;
    }
    public class ViewHodler{
        public TextView zslvitem_name_tv;
    }
}
