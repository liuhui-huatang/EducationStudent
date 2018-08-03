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
 * 学校功能
 * Created by wrb on 2017/1/5.
 */
public class SchoolThingsFuctionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reabck_btn;
    private LinearLayout schoolthingsfuction_xytp_ll,schoolthingsfuction_zcbx_ll,schoolthingsfuction_jqysjl_ll,
            schoolthingsfuction_jnds_ll,schoolthingsfuction_jxhd_ll;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schoolthingsfuction_activity);
        initViews();
        initViewValues();
        initOnlcicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reabck_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        schoolthingsfuction_xytp_ll=(LinearLayout)this.findViewById(R.id.schoolthingsfuction_xytp_ll);
        schoolthingsfuction_zcbx_ll=(LinearLayout)this.findViewById(R.id.schoolthingsfuction_zcbx_ll);
        schoolthingsfuction_jqysjl_ll=(LinearLayout)this.findViewById(R.id.schoolthingsfuction_jqysjl_ll);
        schoolthingsfuction_jnds_ll=(LinearLayout)this.findViewById(R.id.schoolthingsfuction_jnds_ll);
        schoolthingsfuction_jxhd_ll=(LinearLayout)this.findViewById(R.id.schoolthingsfuction_jxhd_ll);
    }
    public void initViewValues(){
        title.setText("校内事宜");
    }
    public void initOnlcicEvents(){
        reabck_btn.setOnClickListener(this);
        schoolthingsfuction_xytp_ll.setOnClickListener(this);
        schoolthingsfuction_zcbx_ll.setOnClickListener(this);
        schoolthingsfuction_jqysjl_ll.setOnClickListener(this);
        schoolthingsfuction_jnds_ll.setOnClickListener(this);
        schoolthingsfuction_jxhd_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.schoolthingsfuction_xytp_ll:
                //校园投片
                intent = new Intent(SchoolThingsFuctionActivity.this, SchollVotingActivity.class);
                startActivity(intent);
                break;
            case R.id.schoolthingsfuction_zcbx_ll:
                //资产包修
                intent = new Intent(SchoolThingsFuctionActivity.this, AssetRepairActivity.class);
                startActivity(intent);
                break;
            case R.id.schoolthingsfuction_jqysjl_ll:
                //假期要事记录
                intent = new Intent(SchoolThingsFuctionActivity.this, HolidayImportantActivity.class);
                startActivity(intent);
                break;
            case R.id.schoolthingsfuction_jnds_ll:
                //技能大赛
                intent = new Intent(SchoolThingsFuctionActivity.this, SkillsGameActivity.class);
                startActivity(intent);
                break;
            case R.id.schoolthingsfuction_jxhd_ll:
                //教学互动
                intent = new Intent(SchoolThingsFuctionActivity.this, TeachingInteractionActivity.class);
                startActivity(intent);
                break;
        }
    }
}
