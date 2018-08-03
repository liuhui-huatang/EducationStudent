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
 * 企业信息功能页
 * Created by wrb on 2017/1/5.
 */
public class CompanyFunctionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout companyfunction_qyzp_ll,companyfunction_sxjl_ll,companyfunction_jyjl_ll,companyfunction_ybm_ll;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companyfunction_activity);
        initViews();
        initValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        companyfunction_qyzp_ll=(LinearLayout)this.findViewById(R.id.companyfunction_qyzp_ll);
        companyfunction_sxjl_ll=(LinearLayout)this.findViewById(R.id.companyfunction_sxjl_ll);
        companyfunction_jyjl_ll=(LinearLayout)this.findViewById(R.id.companyfunction_jyjl_ll);
        companyfunction_ybm_ll=(LinearLayout)this.findViewById(R.id.companyfunction_ybm_ll);
    }
    public void initValues(){
        title.setText("企业信息");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        companyfunction_qyzp_ll.setOnClickListener(this);
        companyfunction_sxjl_ll.setOnClickListener(this);
        companyfunction_jyjl_ll.setOnClickListener(this);
        companyfunction_ybm_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.companyfunction_qyzp_ll:
               //企业招聘
                intent = new Intent(CompanyFunctionActivity.this, CommpanyInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.companyfunction_sxjl_ll:
                //实习记录
                intent = new Intent(CompanyFunctionActivity.this, InternshipRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.companyfunction_jyjl_ll:
               //就业记录
                intent = new Intent(CompanyFunctionActivity.this, EmploymentRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.companyfunction_ybm_ll:
               //已报名岗位
                intent = new Intent(CompanyFunctionActivity.this, AlreadyApplyJobsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
