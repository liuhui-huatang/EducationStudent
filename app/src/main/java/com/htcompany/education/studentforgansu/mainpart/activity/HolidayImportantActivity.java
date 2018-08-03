package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.mainpart.adapter.HolidayImportantAdapter;

/**
 * 假期要事记录
 * Created by wrb on 2016/10/31.
 */
public class HolidayImportantActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn,right_share_rel;
    private ImageView right_share_img;
    private ListView holidayimport_lv;
    private FloatingActionButton holidayimport_add_fab;
    private HolidayImportantAdapter importantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holidayimportant_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){

    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        right_share_rel = (RelativeLayout)this.findViewById(R.id.right_share_rel);
        right_share_img=(ImageView)this.findViewById(R.id.right_share_img);
        holidayimport_lv=(ListView)this.findViewById(R.id.holidayimport_lv);
        importantAdapter = new HolidayImportantAdapter(this);
        holidayimport_lv.setAdapter(importantAdapter);
        holidayimport_add_fab = (FloatingActionButton)this.findViewById(R.id.holidayimport_add_fab);
    }
    public void initViewValues(){
        title.setText("假期要事记录");
        right_share_rel.setVisibility(View.VISIBLE);
        right_share_img.setImageDrawable(getResources().getDrawable(R.mipmap.jqysjl_icon_right));
    }

    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        holidayimport_add_fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
            case R.id.holidayimport_add_fab:
                Intent intent = new Intent(this,HolidayImportantAddActivity.class);
                startActivity(intent);
                break;
        }
    }
}
