package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.HardshipGrantsEntity;

import java.util.List;

/**
 * 临时困难补助适配器
 * Created by wrb on 2016/11/24.
 */
public class TemporaryHardshipGrantsAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<HardshipGrantsEntity> datas;
    public TemporaryHardshipGrantsAdapter(Context context,List<HardshipGrantsEntity> datas){
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
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView =inflater.inflate(R.layout.temporaryhardshipgrantsadapter_item,null);
            vh.hardshipgrants_name_tv = (TextView)convertView.findViewById(R.id.hardshipgrants_name_tv);
            vh.hardshipgrants_status_tv = (TextView)convertView.findViewById(R.id.hardshipgrants_status_tv);
            vh.hardshipgrants_term_tv = (TextView)convertView.findViewById(R.id.hardshipgrants_term_tv);
            vh.hardshipgrants_time_tv = (TextView)convertView.findViewById(R.id.hardshipgrants_time_tv);
            vh.hardshipgrants_money_tv = (TextView)convertView.findViewById(R.id.hardshipgrants_money_tv);
            vh.hardshipgrants_remark_tv = (TextView)convertView.findViewById(R.id.hardshipgrants_remark_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        vh.hardshipgrants_status_tv.setText(datas.get(position).getStatus());
        vh.hardshipgrants_term_tv.setText(datas.get(position).getSchool_date());
        vh.hardshipgrants_time_tv.setText(datas.get(position).getSubsidy_date());
        vh.hardshipgrants_money_tv.setText(datas.get(position).getAmount()+"元");
        vh.hardshipgrants_remark_tv.setText(datas.get(position).getReason());
        return convertView;
    }
    class ViewHodler{
        public TextView hardshipgrants_name_tv,hardshipgrants_status_tv,hardshipgrants_term_tv,
                hardshipgrants_time_tv,hardshipgrants_money_tv,hardshipgrants_remark_tv;
    }
}
