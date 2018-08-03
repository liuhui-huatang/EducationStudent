package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.mysubsidize.MySubsidizeInternet;
import com.htcompany.education.studentforgansu.internet.mysubsidize.MySubsidizePersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.TemporaryHardshipGrantsAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.HardshipGrantsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 临时困难补助
 * Created by wrb on 2016/11/17.
 */
public class TemporaryHardshipGrantsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private ListView temporaryhardship_lv;
    private TemporaryHardshipGrantsAdapter grantsAdapter;
    //网络请求类
    private List<HardshipGrantsEntity> grantsEntities;
    private MySubsidizeInternet subsidizeInternet;
    private MySubsidizePersener subsidizePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temporaryhardshipgrants_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        grantsEntities = new ArrayList<HardshipGrantsEntity>();
        subsidizeInternet = new MySubsidizeInternet(this,myHandler);
        subsidizePersener = new MySubsidizePersener(this);
        waitDialog =new WaitDialog(this,"");
        waitDialog.show();
        subsidizeInternet.getSubsidize_LSKNBZLISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv= (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn= (RelativeLayout)this.findViewById(R.id.right_text_btn);
        temporaryhardship_lv = (ListView)this.findViewById(R.id.temporaryhardship_lv);
        grantsAdapter = new TemporaryHardshipGrantsAdapter(this,grantsEntities);
        temporaryhardship_lv.setAdapter(grantsAdapter);
    }
    public void initViewValues(){
        title.setText("临时困难补助");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("申请");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_text_btn:
                Intent intent = new Intent(TemporaryHardshipGrantsActivity.this,TemporaryHardshipGrantsApplyActivity.class);
                startActivityForResult(intent,101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    subsidizeInternet.getSubsidize_LSKNBZLISTDatas();
                    break;

            }
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
                    ToastUtil.showToast("连接服务器失败",TemporaryHardshipGrantsActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TemporaryHardshipGrantsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<HardshipGrantsEntity> datas = subsidizePersener.parseLSKNBZ_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0) {
                        setAdapterDatas(datas);
                    }
                    break;
            }
        }
    };
    public void setAdapterDatas(List<HardshipGrantsEntity> datas){
        if(grantsEntities.size()>0){
            grantsEntities.clear();
        }
        for(HardshipGrantsEntity entity:datas){
            grantsEntities.add(entity);
        }
        grantsAdapter.notifyDataSetChanged();
    }
}
