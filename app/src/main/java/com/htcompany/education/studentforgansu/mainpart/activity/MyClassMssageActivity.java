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
import com.htcompany.education.studentforgansu.internet.myclass.MyClassInternet;
import com.htcompany.education.studentforgansu.internet.myclass.MyClassPersener;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassDetailEntity;

/**
 * 我的班级信息
 * Created by wrb on 2016/10/21.
 */
public class MyClassMssageActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    //================================班级信息===============================
    private TextView myclassmsg_xzbmc_tv,myclassmsg_xzbdm_tv,myclassmsg_xzbjs_tv,myclassmsg_zy_tv,myclassmsg_cc_tv,myclassmsg_xz_tv,myclassmsg_nj_tv,
            myclassmsg_rs_tv,myclassmsg_nsrs_tv,myclassmsg_girlsrs_tv,myclassmsg_jxbmc_tv;
    //================================班委会名单===============================
    private TextView myclassmsg_bz_tv,myclassmsg_xxwy_tv,myclassmsg_tywy_tv,myclassmsg_shwy_tv,myclassmsg_xcwy_tv;
    //================================团支部委员会名单===============================
    private TextView myclassmsg_tzbsj_tv,myclassmsg_dzbsj_tv;
    //网络请求类
    private MyClassInternet teacherInternet;
    private MyClassPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myclassmessage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();

    }
    public void initDatas(){
        teacherInternet = new MyClassInternet(this,tableHanler);
        teacherPersener = new MyClassPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getClassMsgDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        //================================班级信息===============================
        myclassmsg_xzbmc_tv= (TextView)this.findViewById(R.id.myclassmsg_xzbmc_tv);
        myclassmsg_xzbdm_tv= (TextView)this.findViewById(R.id.myclassmsg_xzbdm_tv);
        myclassmsg_xzbjs_tv= (TextView)this.findViewById(R.id.myclassmsg_xzbjs_tv);
        myclassmsg_zy_tv= (TextView)this.findViewById(R.id.myclassmsg_zy_tv);
        myclassmsg_cc_tv= (TextView)this.findViewById(R.id.myclassmsg_cc_tv);
        myclassmsg_xz_tv= (TextView)this.findViewById(R.id.myclassmsg_xz_tv);
        myclassmsg_nj_tv= (TextView)this.findViewById(R.id.myclassmsg_nj_tv);
        myclassmsg_rs_tv= (TextView)this.findViewById(R.id.myclassmsg_rs_tv);
        myclassmsg_nsrs_tv= (TextView)this.findViewById(R.id.myclassmsg_nsrs_tv);
        myclassmsg_girlsrs_tv= (TextView)this.findViewById(R.id.myclassmsg_girlsrs_tv);
        myclassmsg_jxbmc_tv= (TextView)this.findViewById(R.id.myclassmsg_jxbmc_tv);
        //================================班委会名单===============================
        myclassmsg_bz_tv= (TextView)this.findViewById(R.id.myclassmsg_bz_tv);
        myclassmsg_xxwy_tv= (TextView)this.findViewById(R.id.myclassmsg_xxwy_tv);
        myclassmsg_tywy_tv= (TextView)this.findViewById(R.id.myclassmsg_tywy_tv);
        myclassmsg_shwy_tv= (TextView)this.findViewById(R.id.myclassmsg_shwy_tv);
        myclassmsg_xcwy_tv= (TextView)this.findViewById(R.id.myclassmsg_xcwy_tv);
        //================================团支部委员会名单===============================
        myclassmsg_tzbsj_tv= (TextView)this.findViewById(R.id.myclassmsg_tzbsj_tv);
        myclassmsg_dzbsj_tv= (TextView)this.findViewById(R.id.myclassmsg_dzbsj_tv);
    }
    public void initViewValues(){
        title.setText("班级信息");
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
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",MyClassMssageActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ClassDetailEntity entity = teacherPersener.parseMyClassMsgData((String)msg.obj);
                    if(entity!=null){
                        setViewValue(entity);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyClassMssageActivity.this);
                    break;
            }
        }
    };

    /**
     * 设置界面数据
     * @param entity
     */
    public void setViewValue(ClassDetailEntity entity){
        myclassmsg_xzbmc_tv.setText("行政班:"+entity.getAdmin_name());
        myclassmsg_xzbdm_tv.setText("行政班代码:"+entity.getCode());
        myclassmsg_xzbjs_tv= (TextView)this.findViewById(R.id.myclassmsg_xzbjs_tv);
        myclassmsg_zy_tv.setText("专业:"+entity.getMajor_name());
        myclassmsg_cc_tv.setText("层次:"+entity.getLevels_name());
        myclassmsg_xz_tv.setText("学制:"+entity.getLength_name());
        myclassmsg_nj_tv.setText("年级:"+entity.getGrade_name());
        myclassmsg_rs_tv.setText("总人数:"+entity.getTotal()+"人");
        myclassmsg_nsrs_tv.setText("男生人数:"+entity.getMan()+"人");
        myclassmsg_girlsrs_tv.setText("女生人数:"+entity.getWoman()+"人");
        myclassmsg_jxbmc_tv.setText("教学班:"+entity.getName());
    }
}
