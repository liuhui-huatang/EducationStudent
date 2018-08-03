package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.mainpart.entity.DYNewsEntity;

import java.util.List;

/**
 * 德育新聞適配器
 * Created by wrb on 2016/11/29.
 */
public class DeYuManageAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<DYNewsEntity> datas;
    public DeYuManageAdapter(Context context,List<DYNewsEntity> datas){
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
            convertView = inflater.inflate(R.layout.deyumanageadapter_item,null);
            vh.dyxw_adapter_img = (ImageView)convertView.findViewById(R.id.dyxw_adapter_img);
            vh.news_title_tv = (TextView) convertView.findViewById(R.id.news_title_tv);
            vh.news_time_tv = (TextView) convertView.findViewById(R.id.news_time_tv);
            vh.news_auther_tv = (TextView) convertView.findViewById(R.id.news_auther_tv);
            vh.news_content_tv=(TextView)convertView.findViewById(R.id.news_content_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        vh.news_title_tv.setText(datas.get(position).getTitle());
        vh.news_time_tv.setText(datas.get(position).getUpdate_time());
        vh.news_auther_tv.setText("作者:"+datas.get(position).getAuthor());
        vh.news_content_tv.setText(Html.fromHtml(datas.get(position).getContent()));


        Log.e(datas.get(position).getTitle(),InterfaceManager.siteURLIP+datas.get(position).getImage());

        Glide.with(context)
                .load(InterfaceManager.siteURLIP+datas.get(position).getImage())
                .placeholder(R.mipmap.bottombg_show_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.dyxw_adapter_img);

        return convertView;
    }
    class ViewHodler{
        public TextView news_title_tv,news_time_tv,news_auther_tv,news_content_tv;
        public ImageView dyxw_adapter_img;
    }

}
