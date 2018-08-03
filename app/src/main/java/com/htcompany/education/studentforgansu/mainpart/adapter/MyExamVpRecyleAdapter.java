package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.MenuLeftDatas;
import com.htcompany.education.studentforgansu.mainpart.entity.MyExamClassEntity;

import java.util.List;

/**
 * 我的成绩列表适配器
 * Created by wrb on 2016/10/31.
 */
public class MyExamVpRecyleAdapter  extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<MyExamClassEntity> datas;
    public MyExamVpRecyleAdapter(Context context,List<MyExamClassEntity> datas){
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
        MyViewHodler vh;
        if(convertView==null){
            vh = new MyViewHodler();
            convertView =inflater.inflate(R.layout.myexamresult_list_item,parent,false);
            vh.myexam_vpitem_cunt_tv = (TextView)convertView.findViewById(R.id.myexam_vpitem_cunt_tv);
            vh.myexam_vpitem_classname_tv = (TextView)convertView.findViewById(R.id.myexam_vpitem_classname_tv);
            vh.myexam_vpitem_exam_ll= (LinearLayout) convertView.findViewById(R.id.myexam_vpitem_exam_ll);
            convertView.setTag(vh);
        }else{
            vh = (MyViewHodler) convertView.getTag();
        }
        vh.myexam_vpitem_cunt_tv.setText(datas.get(position).getScore());
        vh.myexam_vpitem_classname_tv.setText(datas.get(position).getCource());
        vh.myexam_vpitem_exam_ll.setBackgroundDrawable(context.getResources().getDrawable(MenuLeftDatas.getColorsListDatas().get(position)));
        return convertView;
    }

    public class MyViewHodler{
        public TextView myexam_vpitem_cunt_tv,myexam_vpitem_classname_tv;
        public LinearLayout myexam_vpitem_exam_ll;
    }
}
