package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.MyGdListView;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.SkillsGameDetails_CXDWAdapter;
import com.htcompany.education.studentforgansu.mainpart.adapter.SkillsGameDetails_HJDWAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.SkillsGameDetailEntity;

/**
 * 技能大赛详情
 * Created by wrb on 2016/11/21.
 */
public class SkillsGameDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    //参赛队伍，获奖队伍
    private RelativeLayout gamedetails_csdw_rel,gamedetails_hddw_rel;
    private TextView gamedetails_csdw_tv,gamedetails_hddw_tv;
    private TextView gamedetails_csdwline_tv,gamedetails_hddwline_tv;
    private int flag=0;
    private MyGdListView skillgamedetials_csdw_lv,skillgamedetials_hjdw_lv;
    private SkillsGameDetails_CXDWAdapter cxdwAdapter;
    private SkillsGameDetails_HJDWAdapter hjdwAdapter;
    //==========网络请求类===========
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private WaitDialog waitDialog=null;
    private String m_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skillsgamedetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        m_id =getIntent().getStringExtra("m_id");
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        waitDialog= new WaitDialog(this,"");
        waitDialog.show();
        thingsInternet.get_SKILLS_DETAILSDatas(m_id);
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        gamedetails_csdw_rel= (RelativeLayout)this.findViewById(R.id.gamedetails_csdw_rel);
        gamedetails_hddw_rel= (RelativeLayout)this.findViewById(R.id.gamedetails_hddw_rel);
        gamedetails_csdw_tv = (TextView)this.findViewById(R.id.gamedetails_csdw_tv);
        gamedetails_hddw_tv = (TextView)this.findViewById(R.id.gamedetails_hddw_tv);
        gamedetails_csdwline_tv= (TextView)this.findViewById(R.id.gamedetails_csdwline_tv);
        gamedetails_hddwline_tv= (TextView)this.findViewById(R.id.gamedetails_hddwline_tv);
        skillgamedetials_csdw_lv=(MyGdListView) this.findViewById(R.id.skillgamedetials_csdw_lv);
        skillgamedetials_hjdw_lv=(MyGdListView) this.findViewById(R.id.skillgamedetials_hjdw_lv);
    }
    public void initViewValues(){
        title.setText("赛事介绍");
        setViewSelect(flag);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        gamedetails_csdw_rel.setOnClickListener(this);
        gamedetails_hddw_rel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.gamedetails_csdw_rel:
                flag=0;
                setViewSelect(flag);
                break;
            case R.id.gamedetails_hddw_rel:
                flag=1;
                setViewSelect(flag);
                break;
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",SkillsGameDetailsActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    SkillsGameDetailEntity entity = tingsPersener.parseShillGameDetailData((String)msg.obj);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",SkillsGameDetailsActivity.this);
                    break;
            }
        }
    };

    public void setViewNormal() {
        gamedetails_csdw_tv.setTextColor(getResources().getColor(R.color.jnds_titlecolor));
        gamedetails_hddw_tv.setTextColor(getResources().getColor(R.color.jnds_titlecolor));

        gamedetails_csdwline_tv.setVisibility(View.GONE);
        gamedetails_hddwline_tv.setVisibility(View.GONE);

        skillgamedetials_csdw_lv.setVisibility(View.GONE);
        skillgamedetials_hjdw_lv.setVisibility(View.GONE);
    }

    public void setViewSelect(int index) {
        setViewNormal();
        switch (index) {
            case 0:
                skillgamedetials_csdw_lv.setVisibility(View.VISIBLE);
                gamedetails_csdw_tv.setTextColor(getResources().getColor(R.color.jnds_timecolor));
                gamedetails_csdwline_tv.setVisibility(View.VISIBLE);
                cxdwAdapter = new SkillsGameDetails_CXDWAdapter(SkillsGameDetailsActivity.this);
                skillgamedetials_csdw_lv.setAdapter(cxdwAdapter);
                break;

            case 1:
                skillgamedetials_hjdw_lv.setVisibility(View.VISIBLE);
                gamedetails_hddw_tv.setTextColor(getResources().getColor(
                        R.color.jnds_timecolor));
                gamedetails_hddwline_tv.setVisibility(View.VISIBLE);
                hjdwAdapter = new SkillsGameDetails_HJDWAdapter(SkillsGameDetailsActivity.this);
                skillgamedetials_hjdw_lv.setAdapter(hjdwAdapter);
                break;
        }

    }

}
