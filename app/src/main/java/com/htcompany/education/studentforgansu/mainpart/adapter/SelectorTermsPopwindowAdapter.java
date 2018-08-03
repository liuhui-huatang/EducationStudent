package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;

import java.util.List;

/**
 * 学期适配器
 * Created by wrb on 2017/2/8.
 */
public class SelectorTermsPopwindowAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<TermEntity> datas;
    public SelectorTermsPopwindowAdapter(Context context,List<TermEntity> datas){
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
            vh =new ViewHodler();
            convertView = inflater.inflate(R.layout.selectortermspopwindow_item,null);
            vh.selectorterm_item_tv = (TextView) convertView.findViewById(R.id.selectorterm_item_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.selectorterm_item_tv.setText(datas.get(position).getName());
        return convertView;
    }
    class ViewHodler{
        public TextView selectorterm_item_tv;
    }
}
