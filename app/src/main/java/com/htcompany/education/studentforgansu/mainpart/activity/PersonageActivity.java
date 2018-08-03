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
 * 个人主页功能也
 * Created by wrb on 2017/1/5.
 */
public class PersonageActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout personage_jbxx_ll,personage_wdcj_ll,personage_tzxx_ll,personage_wdxxk_ll;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personage_activity);
        initViews();
        initViewsValues();
        initOnClicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        personage_jbxx_ll=(LinearLayout)this.findViewById(R.id.personage_jbxx_ll);
        personage_wdcj_ll=(LinearLayout)this.findViewById(R.id.personage_wdcj_ll);
        personage_tzxx_ll=(LinearLayout)this.findViewById(R.id.personage_tzxx_ll);
        personage_wdxxk_ll=(LinearLayout)this.findViewById(R.id.personage_wdxxk_ll);
    }
    public void initViewsValues(){
        title.setText("个人主页");
    }
    public void initOnClicEvents(){
        reback_btn.setOnClickListener(this);
        personage_jbxx_ll.setOnClickListener(this);
        personage_wdcj_ll.setOnClickListener(this);
        personage_tzxx_ll.setOnClickListener(this);
        personage_wdxxk_ll.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.personage_jbxx_ll:
                //基本信息
                intent = new Intent(PersonageActivity.this, PersonInfomationActivity.class);
                startActivity(intent);
                break;
            case R.id.personage_wdcj_ll:
                //我的成绩
                intent = new Intent(PersonageActivity.this, MyExamResultsActivity.class);
                startActivity(intent);
                break;
            case R.id.personage_tzxx_ll:
                //体质信息
                intent = new Intent(PersonageActivity.this, BodyCheckedActivity.class);
                startActivity(intent);
                break;
            case R.id.personage_wdxxk_ll:
                //我的选修课
                intent = new Intent(PersonageActivity.this, MyElectiveActivity.class);
                startActivity(intent);
                break;
        }
    }
}
