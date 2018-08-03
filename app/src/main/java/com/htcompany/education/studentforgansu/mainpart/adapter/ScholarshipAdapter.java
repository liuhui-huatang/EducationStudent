package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.ScholarshipEntity;

import java.util.List;

/**
 * 奖学金适配器
 * Created by wrb on 2016/11/15.
 */
public class ScholarshipAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ScholarshipEntity> datas;
    public ScholarshipAdapter(Context context,List<ScholarshipEntity> datas){
        this.context = context;
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
        ViewHolder vh;
        if (convertView==null){
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.scholarshipadapter_item,null);
            vh.jxj_mc_tv = (TextView)convertView.findViewById(R.id.jxj_mc_tv);
            vh.jxj_status_tv = (TextView)convertView.findViewById(R.id.jxj_status_tv);
            vh.jxj_term_tv = (TextView)convertView.findViewById(R.id.jxj_term_tv);
            vh.jxj_leve_tv = (TextView)convertView.findViewById(R.id.jxj_leve_tv);
            vh.jxj_type_tv = (TextView)convertView.findViewById(R.id.jxj_type_tv);
            vh.jxj_money_tv = (TextView)convertView.findViewById(R.id.jxj_money_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.jxj_mc_tv.setText(datas.get(position).getTitle());
        vh.jxj_status_tv.setText(datas.get(position).getStatus());
        vh.jxj_term_tv.setText(datas.get(position).getSchool_date());
        vh.jxj_leve_tv.setText(datas.get(position).getLevel());
        vh.jxj_type_tv.setText(datas.get(position).getType());
        vh.jxj_money_tv.setText(datas.get(position).getAmount()+"元");
        return convertView;
    }
    class ViewHolder{
        public TextView jxj_mc_tv,jxj_status_tv,jxj_term_tv,jxj_leve_tv,jxj_type_tv,jxj_money_tv;
    }
}
