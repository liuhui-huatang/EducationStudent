package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.PunishmentEntity;

import java.util.List;

/**
 * 惩罚记录
 * Created by wrb on 2016/11/8.
 */
public class PunishmentFragmentAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<PunishmentEntity> datas;
    public PunishmentFragmentAdapter(Context context,List<PunishmentEntity> datas){
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
            convertView = inflater.inflate(R.layout.punishmentfragment_lv_item,null);
            vh.punishment_content_tv = (TextView)convertView.findViewById(R.id.punishment_content_tv);
            vh.punishment_cfjg_tv = (TextView)convertView.findViewById(R.id.punishment_cfjg_tv);
            vh.punishment_time_tv = (TextView)convertView.findViewById(R.id.punishment_time_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.punishment_content_tv.setText(datas.get(position).getC_reason());
        vh.punishment_cfjg_tv.setText(datas.get(position).getP_description());
        vh.punishment_time_tv.setText(datas.get(position).getCh_date());
        return convertView;
    }
    class ViewHodler{
        public TextView punishment_content_tv,punishment_cfjg_tv,punishment_time_tv;
    }
}
