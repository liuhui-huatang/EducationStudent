package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.mainpart.entity.AssetRepairEntity;

/**
 * 资产详情
 * Created by weiruibin on 2017/7/7.
 */

public class AssetMaintenanceDetialsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ImageView zcbx_photo_img,zcbx_status_img;
    private TextView zcbx_title_tv,zcbx_content_tv,zcbx_adres_tv,zcbx_time_tv;
    private AssetRepairEntity maintenanceEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assetmaintenancedetials_activity);
        initDatas();
        initViews();
        initValues();
    }
    public void initDatas(){
        maintenanceEntity=(AssetRepairEntity) getIntent().getSerializableExtra("entity");
    }
    public void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        zcbx_photo_img=(ImageView)this.findViewById(R.id.zcbx_photo_img);
        zcbx_status_img=(ImageView)this.findViewById(R.id.zcbx_status_img);
        zcbx_title_tv=(TextView)this.findViewById(R.id.zcbx_title_tv);
        zcbx_content_tv=(TextView)this.findViewById(R.id.zcbx_content_tv);
        zcbx_adres_tv=(TextView)this.findViewById(R.id.zcbx_adres_tv);
        zcbx_time_tv=(TextView)this.findViewById(R.id.zcbx_time_tv);

    }
    public void initValues(){
        title.setText("资产维护");
        reback_btn.setOnClickListener(this);
        if(maintenanceEntity!=null){
            Glide.with(this).load(maintenanceEntity.getImgurl())
                    .placeholder(R.mipmap.bottombg_show_icon)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(zcbx_photo_img);
            zcbx_title_tv.setText(maintenanceEntity.getTitle());
            zcbx_content_tv.setText(maintenanceEntity.getMiaoshu());
            zcbx_adres_tv.setText(maintenanceEntity.getPlace());
            zcbx_time_tv.setText(maintenanceEntity.getRepair_time());
            if("未处理".equals(maintenanceEntity.getShow_treatment_status_id())){
                zcbx_status_img.setImageResource(R.mipmap.zcwh_wchu);
            }else if("已派工".equals(maintenanceEntity.getShow_treatment_status_id())){
                zcbx_status_img.setImageResource(R.mipmap.zcwh_zhomg);
            }else if("已处理".equals(maintenanceEntity.getShow_treatment_status_id())){
                zcbx_status_img.setImageResource(R.mipmap.zcwh_ychuli);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
        }
    }
}
