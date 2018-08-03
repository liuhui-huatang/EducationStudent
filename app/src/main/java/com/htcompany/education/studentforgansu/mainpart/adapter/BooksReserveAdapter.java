package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.views.XCRoundRectImageView;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.mainpart.entity.BookReserveEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 图书借阅记录适配器
 * Created by wrb on 2017/2/20.
 */
public class BooksReserveAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<BookReserveEntity> datas;
    public BooksReserveAdapter(Context context,List<BookReserveEntity> datas){
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
            convertView = inflater.inflate(R.layout.booksborrowrecord_adapter_item,null);
            vh.booksborrowadapter_icon_img = (XCRoundRectImageView)convertView.findViewById(R.id.booksborrowadapter_icon_img);
            vh.booksborrowadapter_name_tv = (TextView)convertView.findViewById(R.id.booksborrowadapter_name_tv);
            vh.booksborrowadapter_auther_tv = (TextView)convertView.findViewById(R.id.booksborrowadapter_auther_tv);
            vh.booksborrowadapter_time_tv = (TextView)convertView.findViewById(R.id.booksborrowadapter_time_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler)convertView.getTag();
        }
        if(!"".equals(datas.get(position).getPhoto())&&datas.get(position).getPhoto()!=null){
            ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+datas.get(position).getPhoto(),
                    vh.booksborrowadapter_icon_img, MyApp.getOptions());
        }
        vh.booksborrowadapter_time_tv.setText("借阅时间："+datas.get(position).getJieyue_time());
        vh.booksborrowadapter_name_tv.setText(datas.get(position).getBookData().getName());
        vh.booksborrowadapter_auther_tv.setText(datas.get(position).getBookData().getZuozhe());
        return convertView;
    }
    class ViewHodler{
        public XCRoundRectImageView booksborrowadapter_icon_img;
        public TextView booksborrowadapter_name_tv,booksborrowadapter_auther_tv,booksborrowadapter_time_tv;
    }
}
