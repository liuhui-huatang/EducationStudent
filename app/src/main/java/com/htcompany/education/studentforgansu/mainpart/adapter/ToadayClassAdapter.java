package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.BaseUtils;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTableEntity;

import java.util.List;

/**
 * 当天课程适配器
 * Created by wrb on 2017/2/20.
 */
public class ToadayClassAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassTableEntity> datas;
    public ToadayClassAdapter(Context context,List<ClassTableEntity> datas){
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView=inflater.inflate(R.layout.toadayclassadapter_item,null);
            vh.maintodayclass_name_tv = (TextView)convertView.findViewById(R.id.maintodayclass_name_tv);
            vh.maintodayclass_teacher_tv = (TextView)convertView.findViewById(R.id.maintodayclass_teacher_tv);
            vh.maintodayclass_time_tv = (TextView)convertView.findViewById(R.id.maintodayclass_time_tv);
            vh.maintodayclass_room_tv = (TextView)convertView.findViewById(R.id.maintodayclass_room_tv);
            vh.today_classbg_rel=(RelativeLayout)convertView.findViewById(R.id.today_classbg_rel);
            vh.todayclassadaptet_img=(ImageView)convertView.findViewById(R.id.todayclassadaptet_img);
            convertView.setTag(vh);
        }else {
            vh = (ViewHodler) convertView.getTag();
        }
        int viewIndex = position% BaseUtils.getBackgrounImage().size();
        vh.today_classbg_rel.setBackground(context.getResources().getDrawable(BaseUtils.getBackgrounImage().get(viewIndex)));
        vh.todayclassadaptet_img.setImageResource(BaseUtils.getClassIconImage().get(viewIndex));
        vh.maintodayclass_time_tv.setTextColor(context.getResources().getColor(BaseUtils.getClassColor().get(viewIndex)));
        vh.maintodayclass_time_tv.setShadowLayer(5F, 0F,1F,context.getResources().getColor(BaseUtils.getClassYYColor().get(viewIndex)));
        vh.maintodayclass_name_tv.setText(datas.get(position).getCourcename());
        vh.maintodayclass_teacher_tv.setText("授课教师:"+datas.get(position).getTeachername());
        vh.maintodayclass_time_tv.setText(datas.get(position).getTime());
        vh.maintodayclass_room_tv.setText(datas.get(position).getRoomname());
        return convertView;
    }
    class ViewHodler{
        public RelativeLayout today_classbg_rel;
        public ImageView todayclassadaptet_img;
        public TextView maintodayclass_name_tv,maintodayclass_teacher_tv,maintodayclass_time_tv,maintodayclass_room_tv;
    }
}
