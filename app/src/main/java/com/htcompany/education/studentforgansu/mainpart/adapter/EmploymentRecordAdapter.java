package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.CompanyJYJLEntity;

import java.util.List;

/**
 * 就业记录适配器
 * Created by wrb on 2016/11/29.
 */
public class EmploymentRecordAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<CompanyJYJLEntity> datas;
    public EmploymentRecordAdapter(Context context,List<CompanyJYJLEntity> datas){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.datas = datas;

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
            convertView =inflater.inflate(R.layout.employmentrecordadapter_item,null);
            vh.ema_term_tv = (TextView)convertView.findViewById(R.id.ema_term_tv);
            vh.ema_name_tv = (TextView)convertView.findViewById(R.id.ema_name_tv);
            vh.ema_time_tv = (TextView)convertView.findViewById(R.id.ema_time_tv);
            vh.ema_phostion_tv = (TextView)convertView.findViewById(R.id.ema_phostion_tv);
            vh.ema_money_tv = (TextView)convertView.findViewById(R.id.ema_money_tv);
            vh.ema_address_tv = (TextView)convertView.findViewById(R.id.ema_address_tv);
            vh.internshiprecordadatper_sftgtext_tv = (TextView)convertView.findViewById(R.id.internshiprecordadatper_sftgtext_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.ema_term_tv.setText(datas.get(position).getName());
        vh.ema_name_tv.setText(datas.get(position).getEl_name());
        vh.ema_time_tv.setText("实习时间:"+datas.get(position).getEr_sign_time());
        vh.ema_phostion_tv.setText("岗位:"+datas.get(position).getEp_name());
        vh.ema_money_tv.setText("薪资:"+datas.get(position).getEr_wages()+"/每月");
        vh.ema_address_tv.setText("地点:"+datas.get(position).getEp_address());
        if("0".equals(datas.get(position).getZt())){
            vh.internshiprecordadatper_sftgtext_tv.setText("在职");
        }else if("1".equals(datas.get(position).getZt())){
            vh.internshiprecordadatper_sftgtext_tv.setText("离职");
        }

        return convertView;
    }

    class ViewHodler{
        public TextView ema_term_tv,ema_name_tv,ema_time_tv,ema_phostion_tv,
                ema_money_tv,ema_address_tv,internshiprecordadatper_sftgtext_tv;
    }
}
