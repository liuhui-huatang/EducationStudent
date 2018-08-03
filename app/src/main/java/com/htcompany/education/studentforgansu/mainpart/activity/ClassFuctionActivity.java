package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
/**
 * 我的班级功能也
 * Created by wrb on 2017/1/5.
 */
public class ClassFuctionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout classfuntion_jbxx_ll,classfuntion_wdcj_ll,classfuntion_tzxx_ll,classfuntion_wdxxk_ll,classfuntion_txl_ll;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classfuction_activity);
        initViews();
        initValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        classfuntion_jbxx_ll=(LinearLayout)this.findViewById(R.id.classfuntion_jbxx_ll);
        classfuntion_wdcj_ll=(LinearLayout)this.findViewById(R.id.classfuntion_wdcj_ll);
        classfuntion_tzxx_ll=(LinearLayout)this.findViewById(R.id.classfuntion_tzxx_ll);
        classfuntion_wdxxk_ll=(LinearLayout)this.findViewById(R.id.classfuntion_wdxxk_ll);
        classfuntion_txl_ll=(LinearLayout)this.findViewById(R.id.classfuntion_txl_ll);
    }
    public void initValues(){
        title.setText("我的班级");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        classfuntion_jbxx_ll.setOnClickListener(this);
        classfuntion_wdcj_ll.setOnClickListener(this);
        classfuntion_tzxx_ll.setOnClickListener(this);
        classfuntion_wdxxk_ll.setOnClickListener(this);
        classfuntion_txl_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.classfuntion_jbxx_ll:
                //班级详情
                intent = new Intent(ClassFuctionActivity.this, MyClassMssageActivity.class);
                startActivity(intent);
                break;
            case R.id.classfuntion_wdcj_ll:
                //班级课表
                intent = new Intent(ClassFuctionActivity.this, ClassTimetableActivity.class);
                startActivity(intent);
                break;
            case R.id.classfuntion_tzxx_ll:
                //班级公告
                intent = new Intent(ClassFuctionActivity.this, ClassAnnouncementActivity.class);
                startActivity(intent);
                break;
            case R.id.classfuntion_wdxxk_ll:
                //学生公告
                intent = new Intent(ClassFuctionActivity.this, StudentAnnouncementActivity.class);
                startActivity(intent);
                break;
            case R.id.classfuntion_txl_ll:
                //班级通讯录
                intent = new Intent(ClassFuctionActivity.this, ClassTxlActivity.class);
                startActivity(intent);
                break;
        }
    }
}
