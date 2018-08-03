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
import com.htcompany.education.studentforgansu.mainpart.entity.FinancialTypeEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;
import com.htcompany.education.studentforgansu.recruitstudent.activity.ZsItemSelectorActivity;
import com.htcompany.education.studentforgansu.recruitstudent.entity.ZSSHEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 申请助学金
 * Created by wrb on 2016/11/18.
 */
public class StudentFinancialApplyActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private EditText financialapply_name_edt,financialapply_monety_edt,financialapply_zknum_edt,financialapply_remark_edt;
    private TextView financialapply_term_tv;
    private EditText financialapply_starttime_tv,financialapply_endtime_tv;
    //网络请求类
    private MySubsidizeInternet subsidizeInternet;
    private MySubsidizePersener subsidizePersener;
    private WaitDialog waitDialog=null;
    private List<ZSSHEntity> financialTypeEntities;//助学金类型
    private List<ZSSHEntity> termsEntities;//学期类型
    private String termkey="";//学期
    private String typekey="";//学期
    private Intent intent=null;
    private DateViewDailog dateViewDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentfinancialapply_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        financialTypeEntities = new ArrayList<ZSSHEntity>();
        termsEntities = new ArrayList<ZSSHEntity>();
//        for(TermEntity termEntity: MyApp.termEntities){
//            ZSSHEntity zsshEntity = new ZSSHEntity();
//            zsshEntity.setValue(termEntity.getName());
//            zsshEntity.setKey(termEntity.getOnecode());
//            termsEntities.add(zsshEntity);
//        }
        subsidizeInternet = new MySubsidizeInternet(this,myHandler);
        subsidizePersener = new MySubsidizePersener(this);
        waitDialog =new WaitDialog(this,"");
//        waitDialog.show();
//        subsidizeInternet.getSubsidize_ZXJTypeLISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn= (RelativeLayout)this.findViewById(R.id.right_text_btn);
        right_btn_tv= (TextView)this.findViewById(R.id.right_btn_tv);

        financialapply_name_edt=(EditText)this.findViewById(R.id.financialapply_name_edt);
        financialapply_monety_edt=(EditText)this.findViewById(R.id.financialapply_monety_edt);
        financialapply_zknum_edt=(EditText)this.findViewById(R.id.financialapply_zknum_edt);
        financialapply_remark_edt=(EditText)this.findViewById(R.id.financialapply_remark_edt);

        financialapply_term_tv=(TextView) this.findViewById(R.id.financialapply_term_tv);
        financialapply_starttime_tv=(EditText)this.findViewById(R.id.financialapply_starttime_tv);
        financialapply_endtime_tv=(EditText) this.findViewById(R.id.financialapply_endtime_tv);
    }
    public void initViewValues(){
        title.setText("助学金申请");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("申请");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        financialapply_term_tv.setOnClickListener(this);
        financialapply_starttime_tv.setOnClickListener(this);
        financialapply_endtime_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_text_btn:
                //提交数据
                if("".equals(financialapply_name_edt.getText().toString().trim())){
                    ToastUtil.showToast("请输入申请名称",StudentFinancialApplyActivity.this);
                }else if("".equals(financialapply_monety_edt.getText().toString().trim())){
                    ToastUtil.showToast("请输入助学金金额",StudentFinancialApplyActivity.this);
                }else if("".equals(financialapply_starttime_tv.getText().toString().trim())){
                    ToastUtil.showToast("请输入年份",StudentFinancialApplyActivity.this);
                }else if("".equals(financialapply_endtime_tv.getText().toString().trim())){
                    ToastUtil.showToast("请输入月份",StudentFinancialApplyActivity.this);
                }else if("".equals(financialapply_remark_edt.getText().toString().trim())){
                    ToastUtil.showToast("请输入原因",StudentFinancialApplyActivity.this);
                }else {
                    waitDialog.show();
                    subsidizeInternet.uploadSubsidize_ZXJDetialDatas(
                            financialapply_name_edt.getText().toString().trim(),
                            financialapply_monety_edt.getText().toString().trim(),
                            financialapply_starttime_tv.getText().toString().trim(),
                            financialapply_endtime_tv.getText().toString().trim(),
                            financialapply_remark_edt.getText().toString().trim());
                }
                break;
//            case R.id.financialapply_term_tv:
//                //学期
//                intent = new Intent(StudentFinancialApplyActivity.this,ZsItemSelectorActivity.class);
//                intent.putExtra("datas",(Serializable) termsEntities);
//                intent.putExtra("flag","xq");
//                intent.putExtra("title", "选择学期");
//                startActivityForResult(intent,100);
//                break;
//            case R.id.financialapply_starttime_tv:
//                //开始时间
//                dateViewDailog =new DateViewDailog(this,1000,myHandler,false);
//                dateViewDailog.show();
//                break;
//            case R.id.financialapply_endtime_tv:
//                //结束时间
//                dateViewDailog =new DateViewDailog(this,2000,myHandler,false);
//                dateViewDailog.show();
//                break;
//            case R.id.financialapply_type_tv:
//                //类型
//                intent = new Intent(StudentFinancialApplyActivity.this,ZsItemSelectorActivity.class);
//                intent.putExtra("datas",(Serializable) financialTypeEntities);
//                intent.putExtra("flag","zxjtype");
//                intent.putExtra("title", "选择助学金类型");
//                startActivityForResult(intent,100);
//                break;
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(data!=null){
//            switch (resultCode){
//                case 100:
//                    String flag = data.getStringExtra("flag");
//                    ZSSHEntity entity = (ZSSHEntity) data.getSerializableExtra("entity");
//                    if("xq".equals(flag)){
//                        financialapply_term_tv.setText(entity.getValue());
//                        termkey =entity.getKey();
//                    }else if("zxjtype".equals(flag)){
//                        financialapply_type_tv.setText(entity.getValue());
//                        typekey=entity.getKey();
//                    }
//                    break;
//            }
//        }
//    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
//                case 1000:
////                    开始时间
//                    financialapply_starttime_tv.setText((String)msg.obj);
//                    dateViewDailog.dismiss();
//                    break;
//                case 2000:
//                    //结束时间
//                    financialapply_endtime_tv.setText((String)msg.obj);
//                    dateViewDailog.dismiss();
//                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",StudentFinancialApplyActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentFinancialApplyActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(subsidizePersener.parseZXJ_AddData((String)msg.obj)){
                        ToastUtil.showToast("申请成功，请耐心等待",StudentFinancialApplyActivity.this);
                        Intent intent = new Intent();
                        setResult(101,intent);
                        finish();
                    }
                    break;
//                case 201:
//                    if(waitDialog!=null){
//                        waitDialog.dismiss();
//                    }
//                    List<FinancialTypeEntity> datas=subsidizePersener.parseZXJ_TYPELISTData((String)msg.obj);
//                    if(datas!=null&&datas.size()>0){
//                        if(financialTypeEntities.size()>0){
//                            financialTypeEntities.clear();
//                        }
//                        for(FinancialTypeEntity entity:datas){
//                            ZSSHEntity zsshEntity = new ZSSHEntity();
//                            zsshEntity.setValue(entity.getName());
//                            zsshEntity.setKey(entity.getUnkey());
//                            financialTypeEntities.add(zsshEntity);
//                        }
//                    }
//                    break;
            }
        }
    };

}
