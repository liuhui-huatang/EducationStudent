package com.htcompany.education.studentforgansu.mainpart.activity;

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
import com.htcompany.education.studentforgansu.internet.companyinternet.CompanyInternet;
import com.htcompany.education.studentforgansu.internet.companyinternet.CompanyPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.InternshipRecordAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.SXRecoderEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 实习记录
 * Created by wrb on 2016/11/17.
 */
public class InternshipRecordActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView internshiprecord_lv;
    private InternshipRecordAdapter recordAdapter;
    private List<SXRecoderEntity> sxRecoderEntities;
    private CompanyInternet companyInternet;
    private CompanyPersener companyPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internshiprecord_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        sxRecoderEntities = new ArrayList<SXRecoderEntity>();
        companyInternet = new CompanyInternet(this,myHandler);
        companyPersener = new CompanyPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        companyInternet.getCompany_SXJLMsg();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        internshiprecord_lv = (ListView)this.findViewById(R.id.internshiprecord_lv);
        recordAdapter = new InternshipRecordAdapter(this,sxRecoderEntities);
        internshiprecord_lv.setAdapter(recordAdapter);
    }
    public void initViewValues(){
        title.setText("实习记录");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<SXRecoderEntity> datas = companyPersener.parseCompany_SXJLData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",InternshipRecordActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",InternshipRecordActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas(List<SXRecoderEntity> datas){
        if(sxRecoderEntities.size()>0){
            sxRecoderEntities.clear();
        }
        for(SXRecoderEntity entity:datas){
            sxRecoderEntities.add(entity);
        }
        recordAdapter.notifyDataSetChanged();
    }
}
