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
import com.htcompany.education.studentforgansu.internet.mysubsidize.MySubsidizeInternet;
import com.htcompany.education.studentforgansu.internet.mysubsidize.MySubsidizePersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.ScholarshipAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.ScholarshipEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 奖学金界面
 * Created by wrb on 2016/11/15.
 */
public class ScholarshipActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView scholarship_lv;
    private ScholarshipAdapter scholarshipAdapter;
    private List<ScholarshipEntity> scholarshipEntities;
    //网络请求类
    private MySubsidizeInternet subsidizeInternet;
    private MySubsidizePersener subsidizePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scholarship_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        scholarshipEntities = new ArrayList<ScholarshipEntity>();
        subsidizeInternet = new MySubsidizeInternet(this,myHandler);
        subsidizePersener = new MySubsidizePersener(this);
        waitDialog =new WaitDialog(this,"");
        waitDialog.show();
        subsidizeInternet.getSubsidize_JXJLISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        scholarship_lv = (ListView)this.findViewById(R.id.scholarship_lv);
        scholarshipAdapter = new ScholarshipAdapter(this,scholarshipEntities);
        scholarship_lv.setAdapter(scholarshipAdapter);
    }
    public void initViewValues(){
        title.setText("奖学金");
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
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",ScholarshipActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",ScholarshipActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ScholarshipEntity> datas = (List<ScholarshipEntity>) subsidizePersener.parseJXJ_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }
                    break;
            }
        }
    };

    public void setAdapterDatas(List<ScholarshipEntity> datas){
        if(scholarshipEntities.size()>0) {
            scholarshipEntities.clear();
        }
        for(ScholarshipEntity entity:datas){
            scholarshipEntities.add(entity);
        }
        scholarshipAdapter.notifyDataSetChanged();
    }
}
