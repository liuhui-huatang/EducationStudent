package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.mainpart.adapter.MyselfEducationAdapter;

/**
 * 自我教育
 * Created by WRB on 2016/11/14.
 */
public class MyselfEducationActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView myselfeducation_lv;
    private MyselfEducationAdapter educationAdapter;
    private FloatingActionButton myselfeducation_add_fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myselfeducation_activity);
        initView();
        initViewValues();
        initClicerEvents();
    }
    public void initView(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        myselfeducation_lv = (ListView)this.findViewById(R.id.myselfeducation_lv);
        educationAdapter = new MyselfEducationAdapter(this);
        myselfeducation_lv.setAdapter(educationAdapter);
        myselfeducation_add_fab = (FloatingActionButton)this.findViewById(R.id.myselfeducation_add_fab);
    }
    public void initViewValues(){
        title.setText("自我教育");
    }

    public void initClicerEvents(){
        reback_btn.setOnClickListener(this);
        myselfeducation_add_fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.myselfeducation_add_fab:
                Intent intent = new Intent(MyselfEducationActivity.this,MyselfEducationDetailsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
