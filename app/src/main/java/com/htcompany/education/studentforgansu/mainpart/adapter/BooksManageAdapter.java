package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.views.XCRoundRectImageView;
import com.htcompany.education.studentforgansu.mainpart.entity.BookEntity;

import java.util.List;

/**
 * 图书管理列表适配器
 * Created by wrb on 2016/11/15.
 */
public class BooksManageAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<BookEntity> datas;
    public BooksManageAdapter(Context context,List<BookEntity> datas){
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
        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView = inflater.inflate(R.layout.booksmanageadapter_item,null);
            vh.booksmanageadapter_icon_img = (XCRoundRectImageView)convertView.findViewById(R.id.booksmanageadapter_icon_img);
            vh.booksmanageadapter_name_tv = (TextView)convertView.findViewById(R.id.booksmanageadapter_name_tv);
            vh.booksmanageadapter_auther_tv = (TextView)convertView.findViewById(R.id.booksmanageadapter_auther_tv);
            vh.booksmanageadapter_postion_tv = (TextView)convertView.findViewById(R.id.booksmanageadapter_postion_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        vh.booksmanageadapter_name_tv.setText(datas.get(position).getName());
        vh.booksmanageadapter_auther_tv.setText(datas.get(position).getZuozhe());
        vh.booksmanageadapter_postion_tv.setText(datas.get(position).getShelf_name()+datas.get(position).getCengshu());
        return convertView;
    }
    class ViewHodler{
        public XCRoundRectImageView booksmanageadapter_icon_img;
        public TextView booksmanageadapter_name_tv,booksmanageadapter_auther_tv,booksmanageadapter_postion_tv;
    }
}
