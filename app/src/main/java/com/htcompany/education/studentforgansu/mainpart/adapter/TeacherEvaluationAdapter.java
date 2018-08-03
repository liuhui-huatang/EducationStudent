package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.TeacherEvaluationEntity;

import java.util.List;

/**
 * 评教列表
 * Created by wrb on 2017/2/22.
 */
public class TeacherEvaluationAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeacherEvaluationEntity> datas;
    public TeacherEvaluationAdapter(Context context,List<TeacherEvaluationEntity> datas){
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
        ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.teacherevaluationadapter_item,null);
            vh.teacherevaluation_title_tv = (TextView)convertView.findViewById(R.id.teacherevaluation_title_tv);
            vh.teacherevaluation_term_tv = (TextView)convertView.findViewById(R.id.teacherevaluation_term_tv);
            vh.teacherevaluation_status_tv = (TextView)convertView.findViewById(R.id.teacherevaluation_status_tv);
            vh.teacherevaluation_starttime_tv = (TextView)convertView.findViewById(R.id.teacherevaluation_starttime_tv);
            vh.teacherevaluation_endtime_tv = (TextView)convertView.findViewById(R.id.teacherevaluation_endtime_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.teacherevaluation_title_tv.setText(datas.get(position).getTitle());
        vh.teacherevaluation_term_tv.setText(datas.get(position).getXueqi_name());
        if("1".equals(datas.get(position).getIs_ping())){
             //已评教
            vh.teacherevaluation_status_tv.setText("已评教");
        }else if("2".equals(datas.get(position).getIs_ping())){
            //可以评教
            vh.teacherevaluation_status_tv.setText("可以评教");
        }else if("3".equals(datas.get(position).getIs_ping())){
            //已过期
            vh.teacherevaluation_status_tv.setText("已过期");
        }
        vh.teacherevaluation_starttime_tv.setText("开始时间:"+datas.get(position).getAtime());
        vh.teacherevaluation_endtime_tv.setText("结束时间:"+datas.get(position).getBtime());
        return convertView;
    }
    class ViewHolder{
        public TextView teacherevaluation_title_tv,teacherevaluation_term_tv,teacherevaluation_status_tv,
                teacherevaluation_starttime_tv,teacherevaluation_endtime_tv;
    }
}
