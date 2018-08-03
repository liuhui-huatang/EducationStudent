package com.htcompany.education.studentforgansu.recruitstudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.BaseUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.education.studentforgansu.internet.zhaosheng.ZhaoShengInternet;
import com.htcompany.education.studentforgansu.internet.zhaosheng.ZhaoShengPersoner;
import com.htcompany.education.studentforgansu.recruitstudent.entity.ZSSHEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.widget.CompoundButton.*;

/**
 * 学生预报名信息录入界面
 * Created by wrb on 2017/1/16.
 */
public class RecruitStudentBMActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private ZhaoShengInternet zhaoShengInternet;
    private ZhaoShengPersoner zhaoShengPersoner;
    private WaitDialog waitDialog=null;
    List<ZSSHEntity> mzEntitys=null;
    List<ZSSHEntity> xzEntitys=null;
    List<ZSSHEntity> xslbEntitys=null;
    List<ZSSHEntity> jdfsbEntitys=null;
    List<ZSSHEntity> zyEntitys=null;

    private String mzkey="";
    private String zyKey="";
    private String xzkey="";
    private String xslbkey="";
    private String jdfskey="";

    private EditText zs_name_edt,zs_sfzh_edt,zs_dh_edt,zs_bytx_tv;
    private TextView zs_mz_tv,zs_zy_tv,zs_xz_tv,zs_xslb_tv,zs_jdfs_tv,zs_time_tv;
    private RadioGroup zs_sex_group;
    private RadioButton zs_sexnane_btn,zs_sexnv_btn;
    private DateViewDailog dateViewDailog;
    private String code="";
    private Intent intent=null;
    private String sex="男";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruitstudentbm_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        code = getIntent().getStringExtra("code");
        mzEntitys=new ArrayList<ZSSHEntity>();
        xzEntitys=new ArrayList<ZSSHEntity>();
        xslbEntitys=new ArrayList<ZSSHEntity>();
        jdfsbEntitys=new ArrayList<ZSSHEntity>();
        zyEntitys=new ArrayList<ZSSHEntity>();
        zhaoShengInternet = new ZhaoShengInternet(this,myHandler);
        zhaoShengPersoner = new ZhaoShengPersoner(this,myHandler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        zhaoShengInternet.getZSxlDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn= (RelativeLayout)this.findViewById(R.id.right_text_btn);
        right_btn_tv= (TextView)this.findViewById(R.id.right_btn_tv);

        zs_name_edt=(EditText)this.findViewById(R.id.zs_name_edt);
        zs_sfzh_edt=(EditText)this.findViewById(R.id.zs_sfzh_edt);
        zs_dh_edt=(EditText)this.findViewById(R.id.zs_dh_edt);
        zs_bytx_tv=(EditText)this.findViewById(R.id.zs_bytx_tv);

        zs_mz_tv= (TextView)this.findViewById(R.id.zs_mz_tv);
        zs_zy_tv= (TextView)this.findViewById(R.id.zs_zy_tv);
        zs_xz_tv= (TextView)this.findViewById(R.id.zs_xz_tv);
        zs_xslb_tv= (TextView)this.findViewById(R.id.zs_xslb_tv);
        zs_jdfs_tv= (TextView)this.findViewById(R.id.zs_jdfs_tv);
        zs_time_tv= (TextView)this.findViewById(R.id.zs_time_tv);

        zs_sex_group = (RadioGroup)this.findViewById(R.id.zs_sex_group);
        zs_sexnane_btn = (RadioButton) this.findViewById(R.id.zs_sexnane_btn);
        zs_sexnv_btn = (RadioButton) this.findViewById(R.id.zs_sexnv_btn);

    }
    public void initViewValues(){
        title.setText("报名信息");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("提交");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        zs_mz_tv.setOnClickListener(this);
        zs_zy_tv.setOnClickListener(this);
        zs_xz_tv.setOnClickListener(this);
        zs_xslb_tv.setOnClickListener(this);
        zs_jdfs_tv.setOnClickListener(this);
        zs_time_tv.setOnClickListener(this);
    }
    public OnCheckedChangeListener checkedChangeListener= new OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView.getId()==R.id.zs_sexnane_btn){
                sex="男";
            }else if(buttonView.getId()==R.id.zs_sexnv_btn){
                sex="女";
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_text_btn:
                if("".equals(zs_name_edt.getText().toString())){
                    ToastUtil.showToast("请输入姓名",RecruitStudentBMActivity.this);
                }else if("".equals(zs_dh_edt.getText().toString())){
                ToastUtil.showToast("请输入手机后",RecruitStudentBMActivity.this);
                }else if(!BaseUtils.isMobiles(zs_dh_edt.getText().toString())){
                    ToastUtil.showToast("请输入正确手机号",RecruitStudentBMActivity.this);
                }else if("".equals(zs_sfzh_edt.getText().toString())){
                    ToastUtil.showToast("身份证不能为空",RecruitStudentBMActivity.this);
                }else if(!BaseUtils.isSFZCards(zs_sfzh_edt.getText().toString())){
                    ToastUtil.showToast("请输入正确身份证格式",RecruitStudentBMActivity.this);
                }else{
                    waitDialog.show();
                    zhaoShengInternet.addZSxlDatas(zs_name_edt.getText().toString(),sex,zs_sfzh_edt.getText().toString(),mzkey,zs_dh_edt.getText().toString(),
                            zyKey,xzkey,xslbkey,jdfskey,zs_bytx_tv.getText().toString(),zs_time_tv.getText().toString(),code);
                }
                break;
            case R.id.zs_mz_tv:
                //名族
                intent = new Intent(RecruitStudentBMActivity.this,ZsItemSelectorActivity.class);
                intent.putExtra("datas",(Serializable) mzEntitys);
                intent.putExtra("flag","mz");
                intent.putExtra("title", "选择民族");
                startActivityForResult(intent,100);
                break;
            case R.id.zs_zy_tv:
                //专业
                intent = new Intent(RecruitStudentBMActivity.this,ZsItemSelectorActivity.class);
                intent.putExtra("datas",(Serializable) zyEntitys);
                intent.putExtra("flag","zy");
                intent.putExtra("title", "选择专业");
                startActivityForResult(intent,100);
                break;
            case R.id.zs_xz_tv:
                //学制
                intent = new Intent(RecruitStudentBMActivity.this,ZsItemSelectorActivity.class);
                intent.putExtra("datas",(Serializable) xzEntitys);
                intent.putExtra("flag","xz");
                intent.putExtra("title", "选择学制");
                startActivityForResult(intent,100);
                break;
            case R.id.zs_xslb_tv:
                //学生类别
                intent = new Intent(RecruitStudentBMActivity.this,ZsItemSelectorActivity.class);
                intent.putExtra("datas",(Serializable) xslbEntitys);
                intent.putExtra("flag","xslb");
                intent.putExtra("title", "选择学生类别");
                startActivityForResult(intent,100);
                break;
            case R.id.zs_jdfs_tv:
                //就读方式
                intent = new Intent(RecruitStudentBMActivity.this,ZsItemSelectorActivity.class);
                intent.putExtra("datas",(Serializable) jdfsbEntitys);
                intent.putExtra("flag","jdfs");
                intent.putExtra("title", "选择就读方式");
                startActivityForResult(intent,100);
                break;
            case R.id.zs_time_tv:
                //时间
                dateViewDailog = new DateViewDailog(this,1000,myHandler,false);
                dateViewDailog.show();
                break;
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
                    if("mz".equals(flag)){
                        zs_mz_tv.setText(entity.getValue());
                        mzkey = entity.getKey();
                    }else if("zy".equals(flag)){
                        zs_zy_tv.setText(entity.getValue());
                        zyKey = entity.getKey();
                    } else if("xz".equals(flag)){
                        zs_xz_tv.setText(entity.getValue());
                        xzkey = entity.getKey();
                    }else if("xslb".equals(flag)){
                        zs_xslb_tv.setText(entity.getValue());
                        xslbkey = entity.getKey();
                    }else if("jdfs".equals(flag)){
                        zs_jdfs_tv.setText(entity.getValue());
                        jdfskey = entity.getKey();
                    }
                    break;
            }
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    //时间
                    zs_time_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    zhaoShengPersoner.parseClassTableData((String) msg.obj);
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    Bundle bundle = msg.getData();
                    List<ZSSHEntity> mzdatas=(List<ZSSHEntity>) bundle.getSerializable("mzdatas");
                    List<ZSSHEntity> xzdatas=(List<ZSSHEntity>) bundle.getSerializable("xzdatas");
                    List<ZSSHEntity> xslbdatas=(List<ZSSHEntity>) bundle.getSerializable("xslbdatas");
                    List<ZSSHEntity> jdfsbdatas=(List<ZSSHEntity>) bundle.getSerializable("jdfsbdatas");
                    List<ZSSHEntity> zydatas=(List<ZSSHEntity>) bundle.getSerializable("zydatas");
                    if(mzdatas!=null&&mzdatas.size()>0){
                        initMzDatas(mzdatas);
                    }
                    if(xzdatas!=null&&xzdatas.size()>0){
                        initXzDatas(xzdatas);
                    }
                    if(xslbdatas!=null&&xslbdatas.size()>0){
                        initXslbDatas(xslbdatas);
                    }
                    if(jdfsbdatas!=null&&jdfsbdatas.size()>0){
                        initJdfsDatas(jdfsbdatas);
                    }
                    if(zydatas!=null&&zydatas.size()>0){
                        initZyDatas(zydatas);
                    }
                    break;
                case 202:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(zhaoShengPersoner.parserADDzs((String)msg.obj)){
                        ToastUtil.showToast("提交成功",RecruitStudentBMActivity.this);
                        finish();
                    }else{
                        ToastUtil.showToast("请从新提交",RecruitStudentBMActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",RecruitStudentBMActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",RecruitStudentBMActivity.this);
                    break;
            }
        }
    };

    public void initMzDatas(List<ZSSHEntity> datas){
        if(mzEntitys.size()>0){
            mzEntitys.clear();
        }
        for(ZSSHEntity entity:datas){
            mzEntitys.add(entity);
        }
    }
    public void initXzDatas(List<ZSSHEntity> datas){
        if(xzEntitys.size()>0){
            xzEntitys.clear();
        }
        for(ZSSHEntity entity:datas){
            xzEntitys.add(entity);
        }
    }
    public void initXslbDatas(List<ZSSHEntity> datas){
        if(xslbEntitys.size()>0){
            xslbEntitys.clear();
        }
        for(ZSSHEntity entity:datas){
            xslbEntitys.add(entity);
        }
    }
    public void initJdfsDatas(List<ZSSHEntity> datas){
        if(jdfsbEntitys.size()>0){
            jdfsbEntitys.clear();
        }
        for(ZSSHEntity entity:datas){
            jdfsbEntitys.add(entity);
        }
    }
    public void initZyDatas(List<ZSSHEntity> datas){
        if(zyEntitys.size()>0){
            zyEntitys.clear();
        }
        for(ZSSHEntity entity:datas){
            zyEntitys.add(entity);
        }
    }
}
