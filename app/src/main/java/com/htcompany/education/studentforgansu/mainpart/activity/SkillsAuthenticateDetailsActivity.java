package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;

/**
 * 技能鉴定详情
 * Created by wrb on 2016/11/24.
 */
public class SkillsAuthenticateDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skillsauthenticatedetails_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_text_btn = (RelativeLayout)this.findViewById(R.id.right_text_btn);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
    }
    public void initViewValues(){
        title.setText("技能鉴定详情");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("报名");

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
}
