package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.mainpart.entity.TeachingInteractionEntity;

/**
 * 教学互动详情
 * Created by wrb on 2016/11/22.
 */
public class TeachingInteractionDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView interactiondetial_class_tv,interactiondetial_discrip_tv,interactiondetial_btwr_tv,
    interactiondetial_time_tv,interactiondetial_zt_tv,interactiondetial_answer_tv,interactiondetial_hdrq_tv,interactiondetial_wjj_tv,interactiondetial_yjj_tv;
    private LinearLayout interactiondetial_cz_ll,interaction_quesiton_ll;
    private TeachingInteractionEntity entity;
    //==========网络请求类===========
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachinginteractiondetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (TeachingInteractionEntity)getIntent().getSerializableExtra("InteractionEntity");
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        waitDialog= new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        interactiondetial_class_tv= (TextView)this.findViewById(R.id.interactiondetial_class_tv);
        interactiondetial_discrip_tv= (TextView)this.findViewById(R.id.interactiondetial_discrip_tv);
        interactiondetial_btwr_tv= (TextView)this.findViewById(R.id.interactiondetial_btwr_tv);
        interactiondetial_time_tv= (TextView)this.findViewById(R.id.interactiondetial_time_tv);
        interactiondetial_zt_tv= (TextView)this.findViewById(R.id.interactiondetial_zt_tv);
        interactiondetial_answer_tv= (TextView)this.findViewById(R.id.interactiondetial_answer_tv);
        interactiondetial_hdrq_tv= (TextView)this.findViewById(R.id.interactiondetial_hdrq_tv);
        interactiondetial_wjj_tv= (TextView)this.findViewById(R.id.interactiondetial_wjj_tv);
        interactiondetial_yjj_tv= (TextView)this.findViewById(R.id.interactiondetial_yjj_tv);
        interactiondetial_cz_ll=(LinearLayout)this.findViewById(R.id.interactiondetial_cz_ll);
        interaction_quesiton_ll=(LinearLayout)this.findViewById(R.id.interaction_quesiton_ll);
    }
    public void initViewValues(){
        title.setText("详情");
//        ['0' => '已提交', '1' => '未解决', '2' => '已解决', '3'=>'未回答'];
        if(entity!=null){
            interactiondetial_class_tv.setText("课程:"+entity.getKecheng());
            interactiondetial_discrip_tv.setText(entity.getQuestion());
            interactiondetial_btwr_tv.setText("被提问人:"+entity.getTeacher_name());
            interactiondetial_time_tv.setText("日期:"+entity.getTwtime());
            if("0".equals(entity.getIs_yes())){
                if("".equals(entity.getQuestion_da())){
                    interactiondetial_zt_tv.setText("状态:未回答");
                    interactiondetial_cz_ll.setVisibility(View.GONE);
                }else{
                    interactiondetial_zt_tv.setText("状态:已回答");
                    interactiondetial_cz_ll.setVisibility(View.VISIBLE);
                }
            }else if("2".equals(entity.getIs_yes())){
                interactiondetial_zt_tv.setText("状态:未解决");
                interactiondetial_cz_ll.setVisibility(View.GONE);
            }else if("1".equals(entity.getIs_yes())){
                interactiondetial_zt_tv.setText("状态:已解决");
                interactiondetial_cz_ll.setVisibility(View.GONE);
            }else if("3".equals(entity.getIs_yes())){
                if("".equals(entity.getQuestion_da())){
                    interactiondetial_zt_tv.setText("状态:未回答");
                    interactiondetial_cz_ll.setVisibility(View.GONE);
                }else{
                    interactiondetial_zt_tv.setText("状态:已回答");
                    interactiondetial_cz_ll.setVisibility(View.VISIBLE);
                }

            }
            interactiondetial_answer_tv.setText(entity.getQuestion_da());
            interactiondetial_hdrq_tv.setText("回答日期:"+entity.getJdtime());
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        interactiondetial_wjj_tv.setOnClickListener(this);
        interactiondetial_yjj_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.interactiondetial_wjj_tv:
                //未解决
                if("".equals(entity.getQuestion_da())){
                ToastUtil.showToast("问题还未回答，回答后才可操作",TeachingInteractionDetailsActivity.this);
                }else{
                    waitDialog.show();
                    thingsInternet.uplodeJSHD_questionISANSWERDatas(entity.getId(),"2");
                }
                break;
            case R.id.interactiondetial_yjj_tv:
                //已解决
                if("".equals(entity.getIs_yes())){
                    ToastUtil.showToast("问题还未回答，回答后才可操作",TeachingInteractionDetailsActivity.this);
                }else {
                    waitDialog.show();
                    thingsInternet.uplodeJSHD_questionISANSWERDatas(entity.getId(), "1");
                }
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
                    ToastUtil.showToast("连接超时",TeachingInteractionDetailsActivity.this);
                    break;
                case 200:
                    //课程
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(tingsPersener.parserJXHDQuestionIFANSWER((String)msg.obj)){
                        Intent intent=new Intent();
                        setResult(102,intent);
                         finish();
                    }else{

                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingInteractionDetailsActivity.this);
                    break;
            }
        }
    };
}
