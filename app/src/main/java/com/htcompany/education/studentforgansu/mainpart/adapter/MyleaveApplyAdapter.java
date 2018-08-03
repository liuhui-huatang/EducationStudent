package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.mainpart.entity.LeaveApplyEntity;

import java.util.List;


/**
 * 请假列表适配器
 * Created by wrb on 2016/11/10.
 */
public class MyleaveApplyAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<LeaveApplyEntity> datas;
    private SharedPrefUtil sharedPrefUtil=null;
    public MyleaveApplyAdapter(Context context,List<LeaveApplyEntity> datas){
        this.context = context;
        sharedPrefUtil = new SharedPrefUtil(context, "login");
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
            convertView =inflater.inflate(R.layout.myleaveapply_lv_adpter,null);
            vh.myleaveapply_xzb_tv = (TextView)convertView.findViewById(R.id.myleaveapply_xzb_tv);
            vh.myleaveapply_name_tv = (TextView)convertView.findViewById(R.id.myleaveapply_name_tv);
            vh.myleaveapply_time_tv = (TextView)convertView.findViewById(R.id.myleaveapply_time_tv);
            vh.myleaveapply_content_tv = (TextView)convertView.findViewById(R.id.myleaveapply_content_tv);
            vh.myleaveapply_status_tv= (TextView)convertView.findViewById(R.id.myleaveapply_status_tv);
            vh.myleaveapply_status_img=(ImageView)convertView.findViewById(R.id.myleaveapply_status_img);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.myleaveapply_xzb_tv.setText(datas.get(position).getXzb());
        vh.myleaveapply_name_tv.setText("类型:"+datas.get(position).getType());
        vh.myleaveapply_time_tv.setText(datas.get(position).getStart_time()+"至"+datas.get(position).getEnd_time());
        vh.myleaveapply_content_tv.setText(datas.get(position).getReason());
        vh.myleaveapply_status_tv.setText(datas.get(position).getStatus());
        if("审核通过".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_status_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_status_tv.setTextColor(context.getResources().getColor(R.color.qj_btncolor));
            vh.myleaveapply_status_img.setImageResource(R.mipmap.qj_togguo);
        }else if("已提交".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_status_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_status_tv.setTextColor(context.getResources().getColor(R.color.qj_btn2color));
            vh.myleaveapply_status_img.setImageResource(R.mipmap.qj_yitij);
        }else if("审核未通过".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_status_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_status_tv.setTextColor(context.getResources().getColor(R.color.qj_btn3color));
            vh.myleaveapply_status_img.setImageResource(R.mipmap.qj_weitongguo);
        }else if("已录入".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_status_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_status_tv.setTextColor(context.getResources().getColor(R.color.qj_btn5color));
            vh.myleaveapply_status_img.setImageResource(R.mipmap.qj_luru);
        }else if("已销假".equals(datas.get(position).getStatus())) {
            vh.myleaveapply_status_tv.setText(datas.get(position).getStatus());
            vh.myleaveapply_status_tv.setTextColor(context.getResources().getColor(R.color.qj_btn4color));
            vh.myleaveapply_status_img.setImageResource(R.mipmap.qj_xiaoij);
        }
        return convertView;
    }
    class ViewHolder{
        public TextView myleaveapply_xzb_tv,myleaveapply_name_tv,
                myleaveapply_time_tv,myleaveapply_content_tv,myleaveapply_status_tv;
        public ImageView myleaveapply_status_img;
    }
}
