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
 * 请假功能界面
 * Created by wrb on 2017/1/5.
 */
public class LeaveApplyFuctionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout leaveapplyfuction_qjsq_ll,leaveapplyfuction_qjjl_ll;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaveapplyfuction_activity);
        initViews();
        initViewVlues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        leaveapplyfuction_qjsq_ll=(LinearLayout)this.findViewById(R.id.leaveapplyfuction_qjsq_ll);
        leaveapplyfuction_qjjl_ll=(LinearLayout)this.findViewById(R.id.leaveapplyfuction_qjjl_ll);
    }
    public void initViewVlues(){
        title.setText("请假信息");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        leaveapplyfuction_qjsq_ll.setOnClickListener(this);
        leaveapplyfuction_qjjl_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.leaveapplyfuction_qjsq_ll:
                //请假申请
                intent = new Intent(LeaveApplyFuctionActivity.this, MyLeaveApplyAddActivity.class);
                startActivity(intent);
                break;
            case R.id.leaveapplyfuction_qjjl_ll:
                //请假记录
                intent = new Intent(LeaveApplyFuctionActivity.this, MyLeaveApplyActivity.class);
                startActivity(intent);
                break;

        }
    }
}
