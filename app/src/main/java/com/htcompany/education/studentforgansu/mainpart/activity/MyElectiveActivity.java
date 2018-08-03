package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.grzy.MySelfPersener;
import com.htcompany.education.studentforgansu.internet.grzy.MyselfInternet;
import com.htcompany.education.studentforgansu.mainpart.entity.MyElectiveEntity;

/**
 * 我的选修课
 * Created by wrb on 2016/11/12.
 */
public class MyElectiveActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView myelective_name_tv,myelective_status_tv,myelective_ssbj_tv,
            myelective_skteacher_tv,myelective_skjs_tv,myelective_zyone_tv,
            myelective_zytwo_tv,myelective_zythree_tv,myelective_zyfour_tv,
            myelective_zyfive_tv;
    //==============网络请求类===============
    private MyselfInternet myselfInternet;
    private MySelfPersener mySelfPersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myelective_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        myselfInternet = new MyselfInternet(this,myHandler);
        mySelfPersener = new MySelfPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        myselfInternet.getMySelectClassMsg();
    }
    public void initViews(){
        title= (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        myelective_name_tv= (TextView)this.findViewById(R.id.myelective_name_tv);
        myelective_status_tv= (TextView)this.findViewById(R.id.myelective_status_tv);
        myelective_ssbj_tv= (TextView)this.findViewById(R.id.myelective_ssbj_tv);
        myelective_skteacher_tv= (TextView)this.findViewById(R.id.myelective_skteacher_tv);
        myelective_skjs_tv= (TextView)this.findViewById(R.id.myelective_skjs_tv);
        myelective_zyone_tv= (TextView)this.findViewById(R.id.myelective_zyone_tv);
        myelective_zytwo_tv= (TextView)this.findViewById(R.id.myelective_zytwo_tv);
        myelective_zythree_tv= (TextView)this.findViewById(R.id.myelective_zythree_tv);
        myelective_zyfour_tv= (TextView)this.findViewById(R.id.myelective_zyfour_tv);
        myelective_zyfive_tv= (TextView)this.findViewById(R.id.myelective_zyfive_tv);
    }
    public void initViewValues(){
        title.setText("选修课");
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
                    ToastUtil.showToast("连接超时", MyElectiveActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    MyElectiveEntity entity=mySelfPersener.parseMyXXKData((String)msg.obj);
                    if(entity!=null){
                        setViewVlaue(entity);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常", MyElectiveActivity.this);
                    break;
            }
        }
    };
    public void setViewVlaue(MyElectiveEntity entity){
        myelective_name_tv.setText(entity.getTrbd_cource());
        myelective_status_tv.setText(entity.getTrbd_status());
        myelective_ssbj_tv.setText(entity.getTrbd_xxbj());
        myelective_skteacher_tv.setText(entity.getTrbd_teacherid());
        myelective_skjs_tv.setText(entity.getTrbd_didian());
        myelective_zyone_tv.setText(entity.getTrbd_zhiyuany());
        myelective_zytwo_tv.setText(entity.getTrbd_zhiyuane());
        myelective_zythree_tv.setText(entity.getTrbd_zhiyuans());
        myelective_zyfour_tv.setText(entity.getTrbd_zhiyuant());
        myelective_zyfive_tv.setText(entity.getTrbd_zhiyuanw());
    }
}
