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
import com.htcompany.education.studentforgansu.mainpart.entity.TuitionWaiverEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 免学费
 * Created by wrb on 2016/11/17.
 */
public class TuitionWaiverActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView tuitionwaiver_lv;
    private TuitionWaiverAdapter waiverAdapter;
    private List<TuitionWaiverEntity> waiverEntities;
    //网络请求类
    private MySubsidizeInternet subsidizeInternet;
    private MySubsidizePersener subsidizePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuitionwaiver_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        waiverEntities = new ArrayList<TuitionWaiverEntity>();
        subsidizeInternet = new MySubsidizeInternet(this,myHandler);
        subsidizePersener = new MySubsidizePersener(this);
        waitDialog =new WaitDialog(this,"");
        waitDialog.show();
        subsidizeInternet.getSubsidize_MXFLISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        tuitionwaiver_lv = (ListView)this.findViewById(R.id.tuitionwaiver_lv);
        waiverAdapter = new TuitionWaiverAdapter(this,waiverEntities);
        tuitionwaiver_lv.setAdapter(waiverAdapter);
    }
    public void initViewValues(){
        title.setText("免学费");
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
                    ToastUtil.showToast("连接服务器失败",TuitionWaiverActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TuitionWaiverActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TuitionWaiverEntity> datas = subsidizePersener.parseMXF_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0) {
                        setAdapterDatas(datas);
                    }
                    break;
            }
        }
    };
    public void setAdapterDatas(List<TuitionWaiverEntity> datas){
        if(waiverEntities.size()>0){
            waiverEntities.clear();
        }
        for(TuitionWaiverEntity entity:datas){
            waiverEntities.add(entity);
        }
        waiverAdapter.notifyDataSetChanged();
    }
}

