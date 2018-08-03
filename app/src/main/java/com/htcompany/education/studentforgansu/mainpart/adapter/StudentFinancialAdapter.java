package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.StudentFinancialEntity;

import java.util.List;

/**
 * 助学金列表适配器
 * Created by wrb on 2016/11/15.
 */
public class StudentFinancialAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<StudentFinancialEntity> datas;
    public StudentFinancialAdapter(Context context,List<StudentFinancialEntity> datas){
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
            vh =new ViewHodler();
            convertView=inflater.inflate(R.layout.studentfinancialadapter_item,null);
            vh.studentfinancial_jxmc_tv = (TextView)convertView.findViewById(R.id.studentfinancial_jxmc_tv);
            vh.studentfinancial_stauts_tv = (TextView)convertView.findViewById(R.id.studentfinancial_stauts_tv);
            vh.studentfinancial_year_tv = (TextView)convertView.findViewById(R.id.studentfinancial_year_tv);
            vh.studentfinancial_month_tv = (TextView)convertView.findViewById(R.id.studentfinancial_month_tv);

            vh.studentfinancial_money_tv = (TextView)convertView.findViewById(R.id.studentfinancial_money_tv);
            vh.studentfinancial_remark_tv = (TextView)convertView.findViewById(R.id.studentfinancial_remark_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.studentfinancial_jxmc_tv.setText(datas.get(position).getReason());
        vh.studentfinancial_stauts_tv.setText(datas.get(position).getStatus());
        if(datas.get(position).getZhuangtai()!=null){
            if("0".equals(datas.get(position).getZhuangtai())){
                vh.studentfinancial_stauts_tv.setBackgroundColor(context.getResources().getColor(R.color.hz_tag_jk));
            }else if("1".equals(datas.get(position).getZhuangtai())){
                vh.studentfinancial_stauts_tv.setBackgroundColor(context.getResources().getColor(R.color.hz_tag_gxy));

            }else if("2".equals(datas.get(position).getZhuangtai())){
                vh.studentfinancial_stauts_tv.setBackgroundColor(context.getResources().getColor(R.color.login_btn_bg));

            }else if("3".equals(datas.get(position).getZhuangtai())){
                vh.studentfinancial_stauts_tv.setBackgroundColor(context.getResources().getColor(R.color.jxj_bgcolor));

            }
        }
        vh.studentfinancial_money_tv.setText(datas.get(position).getTotal());
        vh.studentfinancial_remark_tv.setText(datas.get(position).getMiaoshu());
        vh.studentfinancial_year_tv.setText(datas.get(position).getNian());
        vh.studentfinancial_month_tv.setText(datas.get(position).getYue());
        return convertView;
    }
    class ViewHodler{
        public TextView studentfinancial_jxmc_tv,studentfinancial_stauts_tv,studentfinancial_money_tv,
                studentfinancial_year_tv,studentfinancial_month_tv,studentfinancial_remark_tv;
    }
}
