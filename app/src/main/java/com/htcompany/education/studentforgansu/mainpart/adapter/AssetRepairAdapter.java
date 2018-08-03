package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.mainpart.entity.AssetRepairEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 资产报修列表适配器
 * Created by wrb on 2016/10/31.
 */
public class AssetRepairAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private List<AssetRepairEntity> datas;
    public AssetRepairAdapter(Context context,List<AssetRepairEntity> datas){
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
            convertView=inflater.inflate(R.layout.assetrepair_list_item,parent,false);
            vh.assetmaintenance_photo_img = (ImageView)convertView.findViewById(R.id.assetmaintenance_photo_img);
            vh.assetrepairitem_name_tv=(TextView)convertView.findViewById(R.id.assetrepairitem_name_tv);
            vh.assetrepairitem_content_tv=(TextView)convertView.findViewById(R.id.assetrepairitem_content_tv);
            vh.assetrepairitem_postion_tv=(TextView)convertView.findViewById(R.id.assetrepairitem_postion_tv);
            vh.assetrepairitem_time_tv=(TextView)convertView.findViewById(R.id.assetrepairitem_time_tv);
            vh.assetrepairitem_status_tv=(TextView)convertView.findViewById(R.id.assetrepairitem_status_tv);
            vh.assetrepairitem_status_img=(ImageView)convertView.findViewById(R.id.assetrepairitem_status_img);
            convertView.setTag(vh);
        }else{
            vh = (ViewHodler) convertView.getTag();
        }
        vh.assetrepairitem_name_tv.setText(datas.get(position).getTitle());
        vh.assetrepairitem_content_tv.setText(datas.get(position).getMiaoshu());
        vh.assetrepairitem_postion_tv.setText(datas.get(position).getPlace());
        vh.assetrepairitem_time_tv.setText(datas.get(position).getRepair_time());
        vh.assetrepairitem_status_tv.setText(datas.get(position).getShow_treatment_status_id());
        if("已处理".equals(datas.get(position).getShow_treatment_status_id())) {
            vh.assetrepairitem_status_tv.setText(datas.get(position).getShow_treatment_status_id());
            vh.assetrepairitem_status_tv.setTextColor(context.getResources().getColor(R.color.qj_btncolor));
            vh.assetrepairitem_status_img.setImageResource(R.mipmap.qj_togguo);
        }else if("未处理".equals(datas.get(position).getShow_treatment_status_id())) {
            vh.assetrepairitem_status_tv.setText(datas.get(position).getShow_treatment_status_id());
            vh.assetrepairitem_status_tv.setTextColor(context.getResources().getColor(R.color.qj_btn2color));
            vh.assetrepairitem_status_img.setImageResource(R.mipmap.qj_yitij);
        }else if("已派工".equals(datas.get(position).getShow_treatment_status_id())) {
            vh.assetrepairitem_status_tv.setText(datas.get(position).getShow_treatment_status_id());
            vh.assetrepairitem_status_tv.setTextColor(context.getResources().getColor(R.color.qj_btn4color));
            vh.assetrepairitem_status_img.setImageResource(R.mipmap.qj_xiaoij);
        }
//        ImageLoader.getInstance().displayImage(datas.get(position).getImgurl(),
//                vh.assetmaintenance_photo_img,
//                MyApp.getOptions());
        Glide.with(context)
                .load(datas.get(position).getImgurl())
                .placeholder(R.mipmap.bottombg_show_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(vh.assetmaintenance_photo_img);
        return convertView;
    }
    class ViewHodler{
        public ImageView assetmaintenance_photo_img,assetrepairitem_status_img;
        public TextView assetrepairitem_name_tv,assetrepairitem_content_tv,
                assetrepairitem_postion_tv,assetrepairitem_time_tv,assetrepairitem_status_tv;
    }
}
