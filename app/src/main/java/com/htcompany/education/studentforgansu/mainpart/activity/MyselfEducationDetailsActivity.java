package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;

/**
 * 自我教育添加
 * Created by wrb on 2016/11/14.
 */
public class MyselfEducationDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myselfeducationdetails_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
    }
    public void initViewValues(){
        title.setText("添加自我教育");
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
