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
 * 资助功能界面
 * Created by wrb on 2017/1/5.
 */
public class SubsidizeFunctionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout schoolthingsfuction_jxj_ll,subsidizefunction_zxj_ll,
            subsidizefunction_lsknbz_ll,subsidizefunction_mxf_ll,subsidizefunction_zxdk_ll;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subsidizefunction_activity);
        initViews();
        initValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        schoolthingsfuction_jxj_ll=(LinearLayout)this.findViewById(R.id.schoolthingsfuction_jxj_ll);
        subsidizefunction_zxj_ll=(LinearLayout)this.findViewById(R.id.subsidizefunction_zxj_ll);
        subsidizefunction_lsknbz_ll=(LinearLayout)this.findViewById(R.id.subsidizefunction_lsknbz_ll);
        subsidizefunction_mxf_ll=(LinearLayout)this.findViewById(R.id.subsidizefunction_mxf_ll);
        subsidizefunction_zxdk_ll=(LinearLayout)this.findViewById(R.id.subsidizefunction_zxdk_ll);
    }
    public void initValues(){
        title.setText("我的资助");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        schoolthingsfuction_jxj_ll.setOnClickListener(this);
        subsidizefunction_zxj_ll.setOnClickListener(this);
        subsidizefunction_lsknbz_ll.setOnClickListener(this);
        subsidizefunction_mxf_ll.setOnClickListener(this);
        subsidizefunction_zxdk_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.schoolthingsfuction_jxj_ll:
                //奖学金
                intent = new Intent(SubsidizeFunctionActivity.this, ScholarshipActivity.class);
                startActivity(intent);
                break;
            case R.id.subsidizefunction_zxj_ll:
                //助学金
                intent = new Intent(SubsidizeFunctionActivity.this, StudentFinancialActivity.class);
                startActivity(intent);
                break;
            case R.id.subsidizefunction_lsknbz_ll:
                //临时困哪补助
                intent = new Intent(SubsidizeFunctionActivity.this, TemporaryHardshipGrantsActivity.class);
                startActivity(intent);
                break;
            case R.id.subsidizefunction_mxf_ll:
                //免学费
                intent = new Intent(SubsidizeFunctionActivity.this, TuitionWaiverActivity.class);
                startActivity(intent);
                break;
            case R.id.subsidizefunction_zxdk_ll:
                //助学贷款
                intent = new Intent(SubsidizeFunctionActivity.this, StudentLoansActivity.class);
                startActivity(intent);
                break;
        }
    }
}
