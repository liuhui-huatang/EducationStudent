package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;

/**
 * 添加假期要事
 * Created by wrn on 2016/11/1.
 */
public class HolidayImportantAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holidayimportantadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initDatas(){

    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv= (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn=(RelativeLayout)this.findViewById(R.id.right_text_btn);
    }
    public void initViewValues(){
        title.setText("添加假期要事记录");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("提交");
    }

    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
        }
    }
}
