package com.htcompany.education.studentforgansu.recruitstudent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.recruitstudent.entity.RecruitStudentZYEntity;

import java.util.List;

/**
 * 专业列表适配器
 * Created by wrb on 2017/1/19.
 */
public class RecruitStudentZYAdapter extends BaseAdapter{
    public Context context;
    public LayoutInflater inflater;
    private List<RecruitStudentZYEntity> datas;
    public RecruitStudentZYAdapter(Context context,List<RecruitStudentZYEntity> datas){
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
            vh =new ViewHolder();
            convertView=inflater.inflate(R.layout.recruitstudentzyadapter_item,null);
            vh.recruitstudentzyadapter_name_tv=(TextView)convertView.findViewById(R.id.recruitstudentzyadapter_name_tv);
            vh.recruitstudentzyadapter_zytype_tv=(TextView)convertView.findViewById(R.id.recruitstudentzyadapter_zytype_tv);
            vh.recruitstudentzyadapter_zyxz_tv=(TextView)convertView.findViewById(R.id.recruitstudentzyadapter_zyxz_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.recruitstudentzyadapter_name_tv.setText(datas.get(position).getName());
        vh.recruitstudentzyadapter_zytype_tv.setText("类别:"+datas.get(position).getType_name());
        vh.recruitstudentzyadapter_zyxz_tv.setText("学制:"+datas.get(position).getLength_name());
        return convertView;
    }
    class ViewHolder{
        public TextView recruitstudentzyadapter_name_tv,recruitstudentzyadapter_zytype_tv,recruitstudentzyadapter_zyxz_tv;
    }
}
