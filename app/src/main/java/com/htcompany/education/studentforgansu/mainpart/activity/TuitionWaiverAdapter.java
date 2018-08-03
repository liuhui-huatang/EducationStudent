package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.TuitionWaiverEntity;

import java.util.List;

/**
 * 免学费记录
 * Created by wrb on 2016/11/17.
 */
public class TuitionWaiverAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TuitionWaiverEntity> datas;
    public TuitionWaiverAdapter(Context context,List<TuitionWaiverEntity> datas){
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
            convertView = inflater.inflate(R.layout.tuitionwaiveradapter_item,null);
            vh.tuitionwaiver_term_tv = (TextView)convertView.findViewById(R.id.tuitionwaiver_term_tv);
            vh.tuitionwaiver_money_tv = (TextView)convertView.findViewById(R.id.tuitionwaiver_money_tv);
            vh.tuitionwaiver_starttime_tv = (TextView)convertView.findViewById(R.id.tuitionwaiver_starttime_tv);
            vh.tuitionwaiver_endtime_tv = (TextView)convertView.findViewById(R.id.tuitionwaiver_endtime_tv);
            vh.tuitionwaiver_remark_tv = (TextView)convertView.findViewById(R.id.tuitionwaiver_remark_tv);
            convertView.setTag(vh);
        }else{
            vh =(ViewHodler)convertView.getTag();
        }
        vh.tuitionwaiver_term_tv.setText(datas.get(position).getSchool_date());
        vh.tuitionwaiver_money_tv.setText(datas.get(position).getAmount()+"元");
        vh.tuitionwaiver_starttime_tv.setText(datas.get(position).getStart_time());
        vh.tuitionwaiver_endtime_tv.setText(datas.get(position).getEnd_time());
        vh.tuitionwaiver_remark_tv.setText(datas.get(position).getReason());
        return convertView;
    }
    class ViewHodler{
        public TextView tuitionwaiver_term_tv,tuitionwaiver_money_tv,tuitionwaiver_starttime_tv,
                tuitionwaiver_endtime_tv,tuitionwaiver_remark_tv;
    }
}
