package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.TeachingInteractionEntity;

import java.util.List;

/**
 * 教学互动
 * Created by wrb on 2016/11/22.
 */
public class TeachingInteractionAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TeachingInteractionEntity> datas;
    public TeachingInteractionAdapter(Context context,List<TeachingInteractionEntity> datas){
        this.context  =context;
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder vh;
        if(convertView==null){
            vh = new ViewHoder();
            convertView=inflater.inflate(R.layout.teachinginteractionadapter_item,null);
            vh.jxhdspq_bg_ll = (LinearLayout)convertView.findViewById(R.id.jxhdspq_bg_ll);
            vh.jxhd_class_tv = (TextView)convertView.findViewById(R.id.jxhd_class_tv);
            vh.jxhd_wt_tv = (TextView)convertView.findViewById(R.id.jxhd_wt_tv);
            vh.jxhd_btwr_tv = (TextView)convertView.findViewById(R.id.jxhd_btwr_tv);
            vh.jxhd_time_tv = (TextView)convertView.findViewById(R.id.jxhd_time_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHoder) convertView.getTag();
        }
        vh.jxhd_class_tv.setText("课程:"+datas.get(position).getKecheng());
        vh.jxhd_wt_tv.setText(datas.get(position).getQuestion());
        vh.jxhd_btwr_tv.setText("被提问人:"+datas.get(position).getTeacher_name());
        vh.jxhd_time_tv.setText("提问日期:"+datas.get(position).getTwtime());
        if("0".equals(datas.get(position).getIs_yes())||"3".equals(datas.get(position).getIs_yes())){
            //0 3 未解决
            vh.jxhdspq_bg_ll.setBackground(context.getResources().getDrawable(R.mipmap.jxhd_bg1));
        }else if("2".equals(datas.get(position).getIs_yes())){
            //未解决
            vh.jxhdspq_bg_ll.setBackground(context.getResources().getDrawable(R.mipmap.jxhd_bg1));
        }else if("1".equals(datas.get(position).getIs_yes())){
            //已解决
            vh.jxhdspq_bg_ll.setBackground(context.getResources().getDrawable(R.mipmap.jxhd_bg2));
        }
        return convertView;
    }
    class ViewHoder{
        public LinearLayout jxhdspq_bg_ll;
        private TextView jxhd_class_tv,jxhd_wt_tv,jxhd_btwr_tv,jxhd_time_tv;
    }
}
