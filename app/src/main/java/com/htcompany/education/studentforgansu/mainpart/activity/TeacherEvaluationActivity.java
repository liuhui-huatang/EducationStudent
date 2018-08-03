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
import com.htcompany.education.studentforgansu.internet.mysubsidize.MySubsidizeInternet;
import com.htcompany.education.studentforgansu.internet.mysubsidize.MySubsidizePersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.TeacherEvaluationAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.TeacherEvaluationEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师评教
 * Created by wrb on 2017/2/22.
 */
public class TeacherEvaluationActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private ListView teacherevaluation_lv;
    private TeacherEvaluationAdapter evaluationAdapter;
    private List<TeacherEvaluationEntity> financialEntities;
    //网络请求类
    private MySubsidizeInternet subsidizeInternet;
    private MySubsidizePersener subsidizePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherevaluation_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        financialEntities = new ArrayList<TeacherEvaluationEntity>();
        subsidizeInternet = new MySubsidizeInternet(this,myHandler);
        subsidizePersener = new MySubsidizePersener(this);
        waitDialog =new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn = (RelativeLayout)this.findViewById(R.id.right_text_btn);
        teacherevaluation_lv = (ListView)this.findViewById(R.id.teacherevaluation_lv);
        evaluationAdapter = new TeacherEvaluationAdapter(this,financialEntities);
        teacherevaluation_lv.setAdapter(evaluationAdapter);
    }
    public void initViewValues(){
        title.setText("评教教师");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        teacherevaluation_lv.setOnItemClickListener(pjClicer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public AdapterView.OnItemClickListener pjClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TeacherEvaluationEntity entity = (TeacherEvaluationEntity)evaluationAdapter.getItem(position);
            if("1".equals(entity.getIs_ping())){
                ToastUtil.showToast("已评教不可再评",TeacherEvaluationActivity.this);
            }else if("2".equals(entity.getIs_ping())){
                Intent intent = new Intent(TeacherEvaluationActivity.this,TeacherEvaluationDetialsActivity.class);
                intent.putExtra("url",entity.getUrl());
                startActivity(intent);
            }else if("3".equals(entity.getIs_ping())){
                ToastUtil.showToast("已过期不可评教",TeacherEvaluationActivity.this);
            }
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接服务器失败",TeacherEvaluationActivity.this);
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeacherEvaluationActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TeacherEvaluationEntity> datas = (List<TeacherEvaluationEntity>) subsidizePersener.parseTeacherPj_LISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }
                    break;
            }
        }
    };

    public void setAdapterDatas(List<TeacherEvaluationEntity> datas){
        if(financialEntities.size()>0) {
            financialEntities.clear();
        }
        for(TeacherEvaluationEntity entity:datas){
            financialEntities.add(entity);
        }
        evaluationAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        waitDialog.show();
        subsidizeInternet.getSubsidize_Teacherpj_Datas();
    }
}
