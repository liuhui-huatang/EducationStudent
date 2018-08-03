package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.BaseUtils;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassAnnouncementEntity;

import java.util.List;

/**
 * Created by weiruibin on 2017/7/28.
 */

public class StudentAnnouncementAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ClassAnnouncementEntity> datas;
    public StudentAnnouncementAdapter(Context context,List<ClassAnnouncementEntity> datas){
        this.context = context;
        this.datas = datas;
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
        StudentAnnouncementAdapter.ViewHodler vh;
        if(convertView==null){
            vh = new StudentAnnouncementAdapter.ViewHodler();
            convertView=inflater.inflate(R.layout.classannouncement_list_item,null);
            vh.calssgg_itemtime_tv= (TextView)convertView.findViewById(R.id.calssgg_itemtime_tv);
            vh.calssgg_itemtitle_tv = (TextView)convertView.findViewById(R.id.calssgg_itemtitle_tv);
            vh.calssgg_itemcontent_tv = (TextView)convertView.findViewById(R.id.calssgg_itemcontent_tv);
            vh.classgg_itemtp_img=(ImageView)convertView.findViewById(R.id.classgg_itemtp_img);
            convertView.setTag(vh);
        }else{
            vh = (StudentAnnouncementAdapter.ViewHodler) convertView.getTag();
        }
        int viewindx = position% BaseUtils.getClassnoticeIconImage().size();
        vh.classgg_itemtp_img.setImageResource(BaseUtils.getClassnoticeIconImage().get(viewindx));
        vh.calssgg_itemtime_tv.setText(datas.get(position).getUpdate_time());
        vh.calssgg_itemtitle_tv.setText(datas.get(position).getTitle());
        vh.calssgg_itemcontent_tv.setText(Html.fromHtml(datas.get(position).getContent()));
        return convertView;
    }
    class ViewHodler{
        public ImageView classgg_itemtp_img;
        public TextView calssgg_itemtitle_tv,calssgg_itemcontent_tv,calssgg_itemtime_tv;
    }
}
