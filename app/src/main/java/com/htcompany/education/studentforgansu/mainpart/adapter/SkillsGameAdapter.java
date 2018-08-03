package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.SkillsGameEntity;

import java.util.List;

/**
 * 技能大赛适配器
 * Created by wrb on 2016/11/21.
 */
public class SkillsGameAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<SkillsGameEntity> datas;
    public SkillsGameAdapter(Context context,List<SkillsGameEntity> datas){
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
            convertView=inflater.inflate(R.layout.skillsgameadapter_item,null);
            vh.skillsgameadapter_title_tv = (TextView)convertView.findViewById(R.id.skillsgameadapter_title_tv);
            vh.skillsgameadapter_time_tv = (TextView)convertView.findViewById(R.id.skillsgameadapter_time_tv);
            vh.skillsgameadapter_fbr_tv = (TextView)convertView.findViewById(R.id.skillsgameadapter_fbr_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        vh.skillsgameadapter_title_tv.setText(datas.get(position).getName());
        vh.skillsgameadapter_time_tv .setText(datas.get(position).getMatch_date());
        vh.skillsgameadapter_fbr_tv.setText(datas.get(position).getMatch_unit());
        return convertView;
    }
    class ViewHodler{
        public TextView skillsgameadapter_title_tv,skillsgameadapter_time_tv,skillsgameadapter_fbr_tv;
    }
}
