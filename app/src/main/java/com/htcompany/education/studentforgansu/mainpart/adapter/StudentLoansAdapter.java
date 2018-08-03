package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.StudentLoansEntity;

import java.util.List;

/**
 * 助学贷款列表
 * Created by wrb on 2016/11/18.
 */
public class StudentLoansAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<StudentLoansEntity> datas;
    public StudentLoansAdapter(Context context,List<StudentLoansEntity> datas){
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
        if(convertView==null){
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.studentloansadapter_item,null);
            vh.studentloans_term_tv = (TextView)convertView.findViewById(R.id.studentloans_term_tv);
            vh.studentloans_danhao_tv = (TextView)convertView.findViewById(R.id.studentloans_danhao_tv);
            vh.studentloans_sqtime_tv = (TextView)convertView.findViewById(R.id.studentloans_sqtime_tv);
            vh.studentloans_money_tv = (TextView)convertView.findViewById(R.id.studentloans_money_tv);
            vh.studentloans_longtime_tv = (TextView)convertView.findViewById(R.id.studentloans_longtime_tv);
            vh.studentloans_hfmoney_tv = (TextView)convertView.findViewById(R.id.studentloans_hfmoney_tv);
            vh.studentloans_blank_tv = (TextView)convertView.findViewById(R.id.studentloans_blank_tv);
            vh.studentloans_qytime_tv = (TextView)convertView.findViewById(R.id.studentloans_qytime_tv);
            vh.studentloans_dbr_tv = (TextView)convertView.findViewById(R.id.studentloans_dbr_tv);
            vh.studentloans_yqsj_tv = (TextView)convertView.findViewById(R.id.studentloans_yqsj_tv);
            vh.studentloans_dktime_tv = (TextView)convertView.findViewById(R.id.studentloans_dktime_tv);
            vh.studentloans_yyqtime_tv = (TextView)convertView.findViewById(R.id.studentloans_yyqtime_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHolder) convertView.getTag();
        }
        vh.studentloans_term_tv.setText(datas.get(position).getSchool_date());
        vh.studentloans_danhao_tv.setText("贷款合同号:"+datas.get(position).getHt_num());
        vh.studentloans_sqtime_tv.setText(datas.get(position).getLoan_first());
        vh.studentloans_money_tv.setText(datas.get(position).getAmount());
        vh.studentloans_longtime_tv.setText(datas.get(position).getLoan_year());
        vh.studentloans_hfmoney_tv.setText(datas.get(position).getLoan_use());
        vh.studentloans_blank_tv.setText(datas.get(position).getLoan_bank());
        vh.studentloans_qytime_tv.setText(datas.get(position).getSign_date());
        vh.studentloans_dbr_tv.setText(datas.get(position).getDanbao());
        if("1".equals(datas.get(position).getDaly())){
            vh.studentloans_yqsj_tv.setText("已延期:"+datas.get(position).getDaly_year()+"年");
        }else{
            vh.studentloans_yqsj_tv.setText("未延期");
        }

        vh.studentloans_dktime_tv.setText("贷款日期:"+datas.get(position).getLoan_start_time());
        vh.studentloans_yyqtime_tv.setText(datas.get(position).getStatus());
        return convertView;
    }
    class ViewHolder{
        public TextView studentloans_term_tv,studentloans_danhao_tv,studentloans_sqtime_tv,studentloans_money_tv,
                studentloans_longtime_tv,studentloans_hfmoney_tv,studentloans_blank_tv,studentloans_qytime_tv,
                studentloans_dbr_tv,studentloans_yqsj_tv,studentloans_dktime_tv,studentloans_yyqtime_tv;
    }
}
