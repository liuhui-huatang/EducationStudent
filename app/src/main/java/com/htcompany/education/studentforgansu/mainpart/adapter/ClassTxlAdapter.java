package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.chatpart.ui.ChatActivity;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTxlEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 班级通讯录适配器
 * Created by wrb on 2017/1/13.
 */
public class ClassTxlAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<ClassTxlEntity> datas;
    public ClassTxlAdapter(Context context,List<ClassTxlEntity> datas){
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHodler vh;
        if(convertView==null){
            vh = new ViewHodler();
            convertView = inflater.inflate(R.layout.class_txl_adapter,null);
            vh.classtxl_photo=(XCRoundAndOvalImageView)convertView.findViewById(R.id.classtxl_photo);
            vh.classtxl_name_tv = (TextView)convertView.findViewById(R.id.classtxl_name_tv);
            vh.classtxt_yy_tv = (TextView)convertView.findViewById(R.id.classtxt_yy_tv);
            vh.classtxt_callphone_tv =(TextView)convertView.findViewById(R.id.classtxt_callphone_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.classtxl_name_tv.setText(datas.get(position).getTruename());
        vh.classtxt_yy_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(context,ChatActivity.class);
                chatIntent.putExtra("userId",datas.get(position).getHxuser());
                chatIntent.putExtra("username",datas.get(position).getTruename());
                chatIntent.putExtra("userphoto",InterfaceManager.siteURLIP+datas.get(position).getPhotos());
               context.startActivity(chatIntent);
            }
        });
        if("".equals(datas.get(position).getPhone())||datas.get(position).getPhone()==null||"null".equals(datas.get(position).getPhone())){
            vh.classtxt_callphone_tv.setBackgroundColor(context.getResources().getColor(R.color.zcbx_contentcolor));
            vh.classtxt_callphone_tv.setEnabled(false);
            vh.classtxt_callphone_tv.setText("暂无电话");
        }else{
            vh.classtxt_callphone_tv.setBackgroundColor(context.getResources().getColor(R.color.myexamresult_item_color4));
            vh.classtxt_callphone_tv.setEnabled(true);
            vh.classtxt_callphone_tv.setText("拨打电话");
        }
        Glide.with(context)
                .load(InterfaceManager.siteURLIP+datas.get(position).getPhotos())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.classtxl_photo);
        vh.classtxt_callphone_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //意图：想干什么事
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                //url:统一资源定位符
                //uri:统一资源标示符（更广）
                intent.setData(Uri.parse("tel:" + datas.get(position).getPhone()));
                //开启系统拨号器
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHodler{
        XCRoundAndOvalImageView classtxl_photo;
        TextView classtxl_name_tv,classtxt_yy_tv,classtxt_callphone_tv;
    }
}
