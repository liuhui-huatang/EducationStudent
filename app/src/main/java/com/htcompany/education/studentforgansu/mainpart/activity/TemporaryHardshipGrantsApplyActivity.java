package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.education.studentforgansu.internet.mysubsidize.MySubsidizeInternet;
import com.htcompany.education.studentforgansu.internet.mysubsidize.MySubsidizePersener;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;
import com.htcompany.education.studentforgansu.recruitstudent.activity.ZsItemSelectorActivity;
import com.htcompany.education.studentforgansu.recruitstudent.entity.ZSSHEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 临时困难补助申请界面
 * Created by wrb on 2016/11/24.
 */
public class TemporaryHardshipGrantsApplyActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private EditText grantsapply_money_edt,grantsapply_content_edt;
    private TextView grantsapply_term_tv,grantsapply_time_tv;
    //网络请求类
    private MySubsidizeInternet subsidizeInternet;
    private MySubsidizePersener subsidizePersener;
    private WaitDialog waitDialog=null;
    private Intent intent=null;
    private List<ZSSHEntity> termsEntities;//学期类型
    private String termkey="";//学期
    private DateViewDailog dateViewDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temporaryhardshipgrantsapply_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        termsEntities = new ArrayList<ZSSHEntity>();
        for(TermEntity termEntity: MyApp.termEntities){
            ZSSHEntity zsshEntity = new ZSSHEntity();
            zsshEntity.setValue(termEntity.getName());
            zsshEntity.setKey(termEntity.getOnecode());
            termsEntities.add(zsshEntity);
        }
        subsidizeInternet = new MySubsidizeInternet(this,myHandler);
        subsidizePersener = new MySubsidizePersener(this);
        waitDialog =new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_text_btn = (RelativeLayout)this.findViewById(R.id.right_text_btn);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);

        grantsapply_money_edt=(EditText)this.findViewById(R.id.grantsapply_money_edt);
        grantsapply_content_edt=(EditText)this.findViewById(R.id.grantsapply_content_edt);
        grantsapply_term_tv= (TextView)this.findViewById(R.id.grantsapply_term_tv);
        grantsapply_time_tv= (TextView)this.findViewById(R.id.grantsapply_time_tv);
    }
    public void initViewValues(){
        title.setText("临时困难补助申请");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("申请");

    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        grantsapply_term_tv.setOnClickListener(this);
        grantsapply_time_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_text_btn:
                //提交数据
                if("".equals(grantsapply_term_tv.getText().toString().trim())){
                    ToastUtil.showToast("请选择学期",TemporaryHardshipGrantsApplyActivity.this);
                }else if("".equals(grantsapply_time_tv.getText().toString().trim())){
                    ToastUtil.showToast("请选择时间",TemporaryHardshipGrantsApplyActivity.this);
                }else if("".equals(grantsapply_money_edt.getText().toString().trim())){
                    ToastUtil.showToast("请输入金额",TemporaryHardshipGrantsApplyActivity.this);
                } else if("".equals(grantsapply_content_edt.getText().toString().trim())){
                    ToastUtil.showToast("请输入原因",TemporaryHardshipGrantsApplyActivity.this);
                }else{
                    waitDialog.show();
                    subsidizeInternet.uploadSubsidize_LSKNBZDetialDatas(termkey,grantsapply_time_tv.getText().toString().trim(),grantsapply_money_edt.getText().toString().trim(),
                            grantsapply_content_edt.getText().toString().trim(),"");
                }
                break;
            case R.id.grantsapply_term_tv:
                //学期
                intent = new Intent(TemporaryHardshipGrantsApplyActivity.this,ZsItemSelectorActivity.class);
                intent.putExtra("datas",(Serializable) termsEntities);
                intent.putExtra("flag","xq");
                intent.putExtra("title", "选择学期");
                startActivityForResult(intent,100);
                break;
            case R.id.grantsapply_time_tv:
                //开始时间
                dateViewDailog =new DateViewDailog(this,1000,myHandler,false);
                dateViewDailog.show();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    String flag = data.getStringExtra("flag");
                    ZSSHEntity entity = (ZSSHEntity) data.getSerializableExtra("entity");
                    if("xq".equals(flag)){
                        grantsapply_term_tv.setText(entity.getValue());
                        termkey =entity.getKey();
                    }
                    break;
            }
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
//                    开始时间
                    grantsapply_time_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",TemporaryHardshipGrantsApplyActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TemporaryHardshipGrantsApplyActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(subsidizePersener.parseZXJ_AddData((String)msg.obj)){
                        ToastUtil.showToast("申请成功，请耐心等待",TemporaryHardshipGrantsApplyActivity.this);
                        Intent intent = new Intent();
                        setResult(101,intent);
                        finish();
                    }
                    break;
            }
        }
    };

}
