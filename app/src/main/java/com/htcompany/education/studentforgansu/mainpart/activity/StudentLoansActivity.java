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
import com.htcompany.education.studentforgansu.mainpart.adapter.StudentLoansAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.StudentLoansEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 助学贷款
 * Created by wrb on 2016/11/17.
 */
public class StudentLoansActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private ListView studentloans_lv;
    private StudentLoansAdapter loansAdapter;
    //网络请求类
    private List<StudentLoansEntity> loansEntities;
    private MySubsidizeInternet subsidizeInternet;
    private MySubsidizePersener subsidizePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentloans_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        loansEntities = new ArrayList<StudentLoansEntity>();
        subsidizeInternet = new MySubsidizeInternet(this,myHandler);
        subsidizePersener = new MySubsidizePersener(this);
        waitDialog =new WaitDialog(this,"");
        waitDialog.show();
        subsidizeInternet.getSubsidize_ZXDKLISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn = (RelativeLayout)this.findViewById(R.id.right_text_btn);
        studentloans_lv = (ListView)this.findViewById(R.id.studentloans_lv);
        loansAdapter = new StudentLoansAdapter(this,loansEntities);
        studentloans_lv.setAdapter(loansAdapter);

    }
    public void initViewValues(){
        title.setText("助学贷款");
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
                Intent intent = new Intent(StudentLoansActivity.this,StudentLoansApplyActivity.class);
                startActivityForResult(intent,101);
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
                    ToastUtil.showToast("连接服务器失败",StudentLoansActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentLoansActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<StudentLoansEntity> datas = subsidizePersener.parseZXDK_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0) {
                        setAdapterDatas(datas);
                    }
                    break;
            }
        }
    };
    public void setAdapterDatas(List<StudentLoansEntity> datas){
        if(loansEntities.size()>0){
            loansEntities.clear();
        }
        for(StudentLoansEntity entity:datas){
            loansEntities.add(entity);
        }
        loansAdapter.notifyDataSetChanged();
    }
}
