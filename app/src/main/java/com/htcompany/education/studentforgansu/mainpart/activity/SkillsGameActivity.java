package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.SkillsGameAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.SkillsGameEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 技能大赛
 * Created by wrb on 2016/11/12.
 */
public class SkillsGameActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView skillgame_lv;
    private SkillsGameAdapter gameAdapter;
    //==========网络请求类===========
    private List<SkillsGameEntity> skillsGameEntities;
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skillsgame_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        skillsGameEntities = new ArrayList<SkillsGameEntity>();
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        waitDialog= new WaitDialog(this,"");
        waitDialog.show();
        thingsInternet.get_SKILLS_LISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        skillgame_lv = (ListView)this.findViewById(R.id.skillgame_lv);
        gameAdapter = new SkillsGameAdapter(this,skillsGameEntities);
        skillgame_lv.setAdapter(gameAdapter);
        skillgame_lv.setOnItemClickListener(gameClicer);
    }
    public void initViewValues(){
        title.setText("技能大赛");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener gameClicer=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            SkillsGameEntity entity = (SkillsGameEntity) gameAdapter.getItem(position);
            Intent intent = new Intent(SkillsGameActivity.this,SkillsGameDetailsActivity.class);
            intent.putExtra("m_id",entity.getM_id());
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
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
                    ToastUtil.showToast("连接超时",SkillsGameActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<SkillsGameEntity> datas = tingsPersener.parseShillGameListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterValues(datas);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",SkillsGameActivity.this);
                    break;
            }
        }
    };
    public void setAdapterValues(List<SkillsGameEntity> datas){
        if(skillsGameEntities.size()>0){
            skillsGameEntities.clear();
        }
        for(SkillsGameEntity entity:datas){
            skillsGameEntities.add(entity);
        }
        gameAdapter.notifyDataSetChanged();
    }
}
