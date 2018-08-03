package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.AssetRepairAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.AssetRepairEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产报修
 * Created by wrb on 2016/10/31.
 */
public class AssetRepairActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_share_rel;
    private ImageView right_share_img;
    private ListView assetrepair_lv;
    private AssetRepairAdapter assetRepairAdapter;
    //==========网络请求类===========
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private WaitDialog waitDialog=null;
    private List<AssetRepairEntity> assetRepairEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assetrepair_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        assetRepairEntities = new ArrayList<AssetRepairEntity>();
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        waitDialog= new WaitDialog(this,"");
        waitDialog.show();
        thingsInternet.getAsset_RepairListDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        right_share_rel = (RelativeLayout)this.findViewById(R.id.right_share_rel);
        right_share_img = (ImageView)this.findViewById(R.id.right_share_img);
        assetrepair_lv=(ListView)this.findViewById(R.id.assetrepair_lv);
        assetRepairAdapter = new AssetRepairAdapter(this,assetRepairEntities);
        assetrepair_lv.setAdapter(assetRepairAdapter);
    }
    public void initViewValues(){
        title.setText("资产报修");
        right_share_rel.setVisibility(View.VISIBLE);
        right_share_img.setImageDrawable(getResources().getDrawable(R.mipmap.zcbx_icon_add));
    }

    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_share_rel.setOnClickListener(this);
        assetrepair_lv.setOnItemClickListener(itemClickListener);
    }

  public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          AssetRepairEntity entity = (AssetRepairEntity) assetRepairAdapter.getItem(position);
          Intent intent = new Intent(AssetRepairActivity.this,AssetMaintenanceDetialsActivity.class);
          intent.putExtra("entity",entity);
          startActivity(intent);
      }
  };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
            case R.id.right_share_rel:
                Intent intent = new Intent(this,AssetRepairAddActivity.class);
                startActivityForResult(intent,100);
                break;
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",AssetRepairActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<AssetRepairEntity> datas = tingsPersener.parseAssetRepairListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterValues(datas);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",AssetRepairActivity.this);
                    break;
            }
        }
    };
    public void setAdapterValues(List<AssetRepairEntity> datas){
        if(assetRepairEntities.size()>0){
            assetRepairEntities.clear();
        }
        for(AssetRepairEntity entity:datas){
            assetRepairEntities.add(entity);
        }
        assetRepairAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    thingsInternet.getAsset_RepairListDatas();
                    break;
            }
        }
    }

}
