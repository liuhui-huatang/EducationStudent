package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.mainpart.adapter.SkillsAuthenticateAdapter;

/**
 * 技能鉴定
 * Created by wrb on 2016/11/24.
 */
public class SkillsAuthenticateActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView skillauthenticate_lv;
    private SkillsAuthenticateAdapter authenticateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skillsauthenticate_activity);
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
        skillauthenticate_lv=(ListView)this.findViewById(R.id.skillauthenticate_lv);
        authenticateAdapter = new SkillsAuthenticateAdapter(this);
        skillauthenticate_lv.setAdapter(authenticateAdapter);
    }
    public void initViewValues(){
        title.setText("技能鉴定");
    }

    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        skillauthenticate_lv.setOnItemClickListener(authenticateClicer);
    }
      public AdapterView.OnItemClickListener authenticateClicer = new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent = new Intent(SkillsAuthenticateActivity.this,SkillsAuthenticateDetailsActivity.class);
              startActivity(intent);
          }
      };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
        }
    }
}
