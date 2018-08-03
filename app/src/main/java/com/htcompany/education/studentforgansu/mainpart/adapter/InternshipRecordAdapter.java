package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.SXRecoderEntity;

import java.util.List;

/**
 * 实习记录
 * Created by wrb on 2016/11/29.
 */
public class InternshipRecordAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<SXRecoderEntity> datas;
    public InternshipRecordAdapter(Context context,List<SXRecoderEntity> datas){
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView=inflater.inflate(R.layout.internshiprecordadapter_item,null);
            vh.internshiprecordadatper_bg_ll = (LinearLayout)convertView.findViewById(R.id.internshiprecordadatper_bg_ll);
            vh.internshiprecordadatper_sftgtext_tv = (TextView) convertView.findViewById(R.id.internshiprecordadatper_sftgtext_tv);
            vh.internshiprecordadatper_bg_img = (ImageView) convertView.findViewById(R.id.internshiprecordadatper_bg_img);
            vh.internshiprecordadatper_sftgicon_img = (ImageView) convertView.findViewById(R.id.internshiprecordadatper_sftgicon_img);

            vh.sxjl_term_tv = (TextView) convertView.findViewById(R.id.sxjl_term_tv);
            vh.sxjl_cname_tv = (TextView) convertView.findViewById(R.id.sxjl_cname_tv);
            vh.sxjl_time_tv = (TextView) convertView.findViewById(R.id.sxjl_time_tv);
            vh.sxjl_gw_tv = (TextView) convertView.findViewById(R.id.sxjl_gw_tv);
            vh.sxjl_money_tv = (TextView) convertView.findViewById(R.id.sxjl_money_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        if("0".equals(datas.get(position).getZt())){
            vh.internshiprecordadatper_bg_ll.setBackground(context.getResources().getDrawable(R.mipmap.shixi_bg1));
            vh.internshiprecordadatper_sftgtext_tv.setTextColor(context.getResources().getColor(R.color.sxjyjl_tv3color));
            vh.internshiprecordadatper_bg_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.shixi_icon1));
            vh.internshiprecordadatper_sftgicon_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.shixi_icon3));
            vh.internshiprecordadatper_sftgtext_tv.setText("实习考核中");
        }else if("1".equals(datas.get(position).getZt())){
            vh.internshiprecordadatper_bg_ll.setBackground(context.getResources().getDrawable(R.mipmap.shixi_bg2));
            vh.internshiprecordadatper_sftgtext_tv.setTextColor(context.getResources().getColor(R.color.sxjyjl_tv4color));
            vh.internshiprecordadatper_bg_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.shixi_icon2));
            vh.internshiprecordadatper_sftgicon_img.setImageDrawable(context.getResources().getDrawable(R.mipmap.shixi_icon4));
            vh.internshiprecordadatper_sftgtext_tv.setText("实习已通过");
        }
        vh.sxjl_term_tv.setText(datas.get(position).getName());
        vh.sxjl_cname_tv.setText(datas.get(position).getEl_name());
        vh.sxjl_time_tv.setText("实习时间:"+datas.get(position).getEr_work_start_date());
        vh.sxjl_gw_tv.setText("岗位:"+datas.get(position).getEp_name());
        vh.sxjl_money_tv.setText("薪资:"+datas.get(position).getEr_wages()+"/月");
        return convertView;
    }

    public class ViewHolder{
        public LinearLayout internshiprecordadatper_bg_ll;
        public TextView internshiprecordadatper_sftgtext_tv;
        public ImageView internshiprecordadatper_bg_img,internshiprecordadatper_sftgicon_img;
        public TextView sxjl_term_tv,sxjl_cname_tv,sxjl_time_tv,sxjl_gw_tv,sxjl_money_tv;
    }
}
