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
import com.htcompany.education.studentforgansu.mainpart.adapter.StudentFinancialAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.StudentFinancialEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 助学金
 * Created by wrb on 2016/11/15.
 */
public class StudentFinancialActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private ListView studentfinancial_lv;
    private StudentFinancialAdapter financialAdapter;
    private List<StudentFinancialEntity> financialEntities;
    //网络请求类
    private MySubsidizeInternet subsidizeInternet;
    private MySubsidizePersener subsidizePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentfinancial_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        financialEntities = new ArrayList<StudentFinancialEntity>();
        subsidizeInternet = new MySubsidizeInternet(this,myHandler);
        subsidizePersener = new MySubsidizePersener(this);
        waitDialog =new WaitDialog(this,"");
        waitDialog.show();
        subsidizeInternet.getSubsidize_ZXJLISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn = (RelativeLayout)this.findViewById(R.id.right_text_btn);
        studentfinancial_lv = (ListView)this.findViewById(R.id.studentfinancial_lv);
        financialAdapter = new StudentFinancialAdapter(this,financialEntities);
        studentfinancial_lv.setAdapter(financialAdapter);
    }
    public void initViewValues(){
        title.setText("助学金");
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
                //申请助学金
                Intent intent = new Intent(StudentFinancialActivity.this,StudentFinancialApplyActivity.class);
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
                    subsidizeInternet.getSubsidize_ZXJLISTDatas();
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
                    ToastUtil.showToast("连接服务器失败",StudentFinancialActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentFinancialActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<StudentFinancialEntity> datas = (List<StudentFinancialEntity>) subsidizePersener.parseZXJ_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }
                    break;
            }
        }
    };

    public void setAdapterDatas(List<StudentFinancialEntity> datas){
        if(financialEntities.size()>0) {
            financialEntities.clear();
        }
        for(StudentFinancialEntity entity:datas){
            financialEntities.add(entity);
        }
        financialAdapter.notifyDataSetChanged();
    }
}
