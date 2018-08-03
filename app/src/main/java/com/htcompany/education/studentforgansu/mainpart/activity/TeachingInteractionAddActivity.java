package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.mainpart.entity.KeChengEntity;
import com.htcompany.education.studentforgansu.recruitstudent.activity.ZsItemSelectorActivity;
import com.htcompany.education.studentforgansu.recruitstudent.entity.ZSSHEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加问题
 * Created by wrb on 2016/11/22.
 */
public class TeachingInteractionAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private TextView interactionadd_class_tv,interactionadd_teacher_tv;//课程,教师
    private EditText interactionadd_discrip_edt;//问题描述
    private Intent intent=null;
    private String classKEY="";
    private String teacherKey="";
    //==========网络请求类===========
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private WaitDialog waitDialog=null;
    private List<ZSSHEntity> zsshEntities;
    private List<ZSSHEntity> zsshjsEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachinginteractionadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        zsshEntities = new ArrayList<ZSSHEntity>();
        zsshjsEntities=new ArrayList<ZSSHEntity>();
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        waitDialog= new WaitDialog(this,"");
        waitDialog.show();
        thingsInternet.getJSHD_CLASSDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn=(RelativeLayout)this.findViewById(R.id.right_text_btn);
        interactionadd_class_tv = (TextView)this.findViewById(R.id.interactionadd_class_tv);
        interactionadd_teacher_tv = (TextView)this.findViewById(R.id.interactionadd_teacher_tv);
        interactionadd_discrip_edt=(EditText)this.findViewById(R.id.interactionadd_discrip_edt);
    }
    public void initViewValues(){
        title.setText("添加问题");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("提问");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        interactionadd_class_tv.setOnClickListener(this);
        interactionadd_teacher_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.interactionadd_class_tv:
                if(zsshEntities.size()>0) {
                    intent = new Intent(TeachingInteractionAddActivity.this, ZsItemSelectorActivity.class);
                    intent.putExtra("datas", (Serializable) zsshEntities);
                    intent.putExtra("flag", "kc");
                    intent.putExtra("title", "选择课程");
                    startActivityForResult(intent, 100);
                }
                break;
            case R.id.interactionadd_teacher_tv:
                if("".equals(interactionadd_class_tv.getText().toString())){
                    ToastUtil.showToast("请先选择提问课程",TeachingInteractionAddActivity.this);
                }else{
                if(zsshjsEntities.size()>0) {
                    intent = new Intent(TeachingInteractionAddActivity.this, ZsItemSelectorActivity.class);
                    intent.putExtra("datas", (Serializable) zsshjsEntities);
                    intent.putExtra("flag", "teacher");
                    intent.putExtra("title", "选择教师");
                    startActivityForResult(intent, 100);
                }
                }
                break;
            case R.id.right_text_btn:
                //添加问题
                if("".equals(interactionadd_class_tv.getText().toString())){
                    ToastUtil.showToast("请选择提问课程",TeachingInteractionAddActivity.this);
                }else if("".equals(interactionadd_teacher_tv.getText().toString())){
                    ToastUtil.showToast("请选择提问教师",TeachingInteractionAddActivity.this);
                }else if("".equals(interactionadd_discrip_edt.getText().toString().trim())){
                    ToastUtil.showToast("请输入问题描述",TeachingInteractionAddActivity.this);
                }else{
                    waitDialog.show();
                    thingsInternet.uplodeJSHD_questionDatas(classKEY,teacherKey,interactionadd_discrip_edt.getText().toString().trim());
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
                    ToastUtil.showToast("连接超时",TeachingInteractionAddActivity.this);
                    break;
                case 200:
                    //课程
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<KeChengEntity> datas = tingsPersener.parseClassesData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setKCAdapterValues(datas);
                    }
                    break;
                case 201:
                    //教师
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<KeChengEntity> datas2 = tingsPersener.parseClassesData((String)msg.obj);
                    if(datas2!=null&&datas2.size()>0){
                        setJSAdapterValues(datas2);
                    }
                    break;
                case 202:
                    //问题提交
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(tingsPersener.parserJXHDQuestion((String)msg.obj)){
                        ToastUtil.showToast("提问成功，等待回答",TeachingInteractionAddActivity.this);
                        Intent intent=new Intent();
                        setResult(101,intent);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新提问",TeachingInteractionAddActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingInteractionAddActivity.this);
                    break;
            }
        }
    };
    public void setKCAdapterValues(List<KeChengEntity> datas){
        if(zsshEntities.size()>0){
            zsshEntities.clear();
        }
        for(KeChengEntity entity:datas){
            ZSSHEntity zsshEntity = new ZSSHEntity();
            zsshEntity.setKey(entity.getValue());
            zsshEntity.setValue(entity.getLabel());
            zsshEntities.add(zsshEntity);
        }
    }
    public void setJSAdapterValues(List<KeChengEntity> datas){
        if(zsshjsEntities.size()>0){
            zsshjsEntities.clear();
        }
        for(KeChengEntity entity:datas){
            ZSSHEntity zsshEntity = new ZSSHEntity();
            zsshEntity.setKey(entity.getValue());
            zsshEntity.setValue(entity.getLabel());
            zsshjsEntities.add(zsshEntity);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 100:
                    String flag = data.getStringExtra("flag");
                    ZSSHEntity entity = (ZSSHEntity) data.getSerializableExtra("entity");
                    if("kc".equals(flag)){
                        interactionadd_class_tv.setText(entity.getValue());
                        classKEY = entity.getKey();
                         if(!"".equals(classKEY)){
                             waitDialog.show();
                             thingsInternet.getJSHD_ClassTeacherDatas(classKEY);
                         }
                    }else if("teacher".equals(flag)){
                        interactionadd_teacher_tv.setText(entity.getValue());
                        teacherKey = entity.getKey();
                    }
                    break;
            }
        }
    }

}
