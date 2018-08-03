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
import com.htcompany.education.studentforgansu.mainpart.entity.MenuLeftDatas;
import com.htcompany.education.studentforgansu.mainpart.entity.YBMJobEntity;

import java.util.List;

/**
 * 企业信息适配器
 * Created by wrb on 2016/11/18.
 */
public class CommpanyInformationAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<YBMJobEntity> datas;
    public CommpanyInformationAdapter(Context context, List<YBMJobEntity> datas){
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
            convertView = inflater.inflate(R.layout.commpanyinformationadapter_item,null);
            vh.ybmjob_name_tv =(TextView)convertView.findViewById(R.id.ybmjob_name_tv);
            vh.ybmjob_time_tv =(TextView)convertView.findViewById(R.id.ybmjob_time_tv);
            vh.ybmjob_cname_tv =(TextView)convertView.findViewById(R.id.ybmjob_cname_tv);
            vh.ybmjob_money_tv =(TextView)convertView.findViewById(R.id.ybmjob_money_tv);
            vh.ybmjob_adress_tv =(TextView)convertView.findViewById(R.id.ybmjob_adress_tv);
            vh.ybmjob_xl_tv =(TextView)convertView.findViewById(R.id.ybmjob_xl_tv);
            vh.ybmjob_bmcount_tv =(TextView)convertView.findViewById(R.id.ybmjob_bmcount_tv);
            vh.ybmjob_line_tv=(TextView)convertView.findViewById(R.id.ybmjob_line_tv);
            vh.commpanyinformationjobadpter_bg_ll=(LinearLayout)convertView.findViewById(R.id.commpanyinformationjobadpter_bg_ll);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        int viewIndex = position% MenuLeftDatas.getBG_JNJDDatas().size();
        vh.commpanyinformationjobadpter_bg_ll.setBackground(context.getResources().getDrawable(MenuLeftDatas.getBG_JNJDDatas().get(viewIndex)));
        vh.ybmjob_line_tv.setBackgroundColor(context.getResources().getColor(MenuLeftDatas.getTVCOLOR_JNJDDatas().get(viewIndex)));
        vh.ybmjob_time_tv.setTextColor(context.getResources().getColor(MenuLeftDatas.getTVCOLOR_JNJDDatas().get(viewIndex)));
        vh.ybmjob_name_tv.setText(datas.get(position).getEp_name());
        vh.ybmjob_time_tv.setText(datas.get(position).getEp_start_time()+"-"+datas.get(position).getEp_end_time());
        vh.ybmjob_cname_tv.setText(datas.get(position).getEl_name());
        vh.ybmjob_money_tv.setText(datas.get(position).getEp_monthly_pay());
        vh.ybmjob_adress_tv.setText(datas.get(position).getEp_address());
        vh.ybmjob_xl_tv.setText(datas.get(position).getEp_education());
        vh.ybmjob_bmcount_tv.setText(datas.get(position).getEp_num()+"人");
        return convertView;
    }
    class ViewHodler{
        public TextView ybmjob_name_tv,ybmjob_time_tv,ybmjob_cname_tv,
                ybmjob_money_tv,ybmjob_adress_tv,ybmjob_xl_tv,ybmjob_bmcount_tv,ybmjob_line_tv;
        public LinearLayout commpanyinformationjobadpter_bg_ll;
    }
}
