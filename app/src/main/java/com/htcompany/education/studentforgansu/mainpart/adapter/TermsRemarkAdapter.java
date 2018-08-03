package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.TermsRemarkEntity;

import java.util.List;

/**
 * 学期评语适配器
 * Created by wrb on 2016/10/24.
 */
public class TermsRemarkAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TermsRemarkEntity> datas;
    public TermsRemarkAdapter(Context context,List<TermsRemarkEntity> datas){
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
        MyViewHodler vh;
        if(convertView==null){
            vh = new MyViewHodler();
            convertView = inflater.inflate(R.layout.termsremark_list_item,null);
            vh.termsremark_bottomline_tv = (TextView)convertView.findViewById(R.id.termsremark_bottomline_tv);
            vh.termremark_xq_tv= (TextView)convertView.findViewById(R.id.termremark_xq_tv);
            vh.termremark_pjr_tv= (TextView)convertView.findViewById(R.id.termremark_pjr_tv);
            vh.termremark_content_tv= (TextView)convertView.findViewById(R.id.termremark_content_tv);
            vh.termremark_month_tv= (TextView)convertView.findViewById(R.id.termremark_month_tv);
            vh.termremark_day_tv= (TextView)convertView.findViewById(R.id.termremark_day_tv);
            convertView.setTag(vh);
        }else{
            vh = (MyViewHodler) convertView.getTag();
        }
        if(position==datas.size()-1){
            vh.termsremark_bottomline_tv.setVisibility(View.GONE);
        }
        vh.termremark_xq_tv.setText(datas.get(position).getTerm());
        vh.termremark_pjr_tv.setText("评价人:"+datas.get(position).getLaoshi());
        vh.termremark_content_tv.setText(datas.get(position).getContent());
        vh.termremark_month_tv.setText(datas.get(position).getMonth()+"月");
        vh.termremark_day_tv.setText(datas.get(position).getDay()+"日");
        return convertView;
    }

    public class MyViewHodler{
        public TextView termsremark_topline_tv,termsremark_bottomline_tv;
        public TextView termremark_xq_tv,termremark_pjr_tv,termremark_content_tv;
        private TextView termremark_month_tv,termremark_day_tv;
    }
}
