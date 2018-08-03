package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.BaseUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.dateview.DateViewDailog;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluateInternet;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluatePersener;
import com.htcompany.education.studentforgansu.mainpart.dailogs.MyLeaveTypeDailog;
import com.htcompany.education.studentforgansu.mainpart.entity.MyLeaveTypeEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 添加请假
 * Created by wrb on 2016/11/10.
 */
public class MyLeaveApplyAddActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private TextView myleaveapplyadd_starttime_tv,myleaveapplyadd_endtime_tv;
    private DateViewDailog dateViewDailog;
    private EditText myleaveapplyadd_reson_edt;
    private MyLeaveTypeDailog typeDailog;
    private String type="";
    private TextView myleaveapply_type_tv;//请假类型
    private List<MyLeaveTypeEntity> typeEntities;
    private String leavelist="";
    //网络请求类
    private MyEvaluateInternet evaluateInternet;
    private MyEvaluatePersener evaluatePersener;
    private WaitDialog waitDialog=null;
    private TimePickerView pickerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myleaveapplyadd_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        leavelist=getIntent().getStringExtra("leavelist");
        typeEntities=new ArrayList<MyLeaveTypeEntity>();
        evaluateInternet = new MyEvaluateInternet(MyLeaveApplyAddActivity.this,myHandler);
        evaluatePersener = new MyEvaluatePersener(MyLeaveApplyAddActivity.this);
        waitDialog = new WaitDialog(MyLeaveApplyAddActivity.this,"");
        waitDialog.show();
        evaluateInternet.getStudentQJTYPES_LISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_text_btn = (RelativeLayout)this.findViewById(R.id.right_text_btn);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        myleaveapply_type_tv= (TextView)this.findViewById(R.id.myleaveapply_type_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        myleaveapplyadd_starttime_tv= (TextView)this.findViewById(R.id.myleaveapplyadd_starttime_tv);
        myleaveapplyadd_endtime_tv= (TextView)this.findViewById(R.id.myleaveapplyadd_endtime_tv);
        myleaveapplyadd_reson_edt=(EditText)this.findViewById(R.id.myleaveapplyadd_reson_edt);
    }
    public void initViewValues(){
        title.setText("请假申请");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("提交");

    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        myleaveapplyadd_starttime_tv.setOnClickListener(this);
        myleaveapplyadd_endtime_tv.setOnClickListener(this);
        myleaveapply_type_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.myleaveapply_type_tv:
                //选择请假类型
                if(typeEntities.size()>0){
                    typeDailog = new MyLeaveTypeDailog(this,typeEntities,typeCliceer);
                    typeDailog.show();
                }else{
                }
                break;
            case R.id.myleaveapplyadd_starttime_tv:
                //开始时间
//                dateViewDailog = new DateViewDailog(MyLeaveApplyAddActivity.this,1000,myHandler,false);
//                dateViewDailog.show();
                pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        if(BaseUtils.DateCompare(BaseUtils.getTime(date),BaseUtils.getSystemTime())){
                            myleaveapplyadd_starttime_tv.setText(BaseUtils.getTime(date));
                        }else {
                            ToastUtil.showToast("开始时间必须大于当前时间",MyLeaveApplyAddActivity.this);
                        }
                    }
                }).setType(new boolean[]{true, true, true, true, true, false})
                        .setLabel("", "", "", "点", "分", "")
                        .isCenterLabel(false)
                        .isCyclic(true)
                        .setDividerColor(Color.DKGRAY)
                        .setContentSize(21)
                        .setTitleText("选择时间")
                        .setTitleColor(Color.BLUE)
                        .setTitleBgColor(Color.WHITE)
                        .isDialog(true)
                        .build();
                pickerView.show();
                break;
            case R.id.myleaveapplyadd_endtime_tv:
                //结束时间
//                dateViewDailog = new DateViewDailog(MyLeaveApplyAddActivity.this,2000,myHandler,false);
//                dateViewDailog.show();
                if("".equals(myleaveapplyadd_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请先选择开始时间",MyLeaveApplyAddActivity.this);
                }else {
                    pickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {
                            if (BaseUtils.DateCompare(BaseUtils.getTime(date), myleaveapplyadd_starttime_tv.getText().toString())) {
                                myleaveapplyadd_endtime_tv.setText(BaseUtils.getTime(date));
                            } else {
                                ToastUtil.showToast("结束时间必须大于开始时间", MyLeaveApplyAddActivity.this);
                            }
                        }
                    }).setType(new boolean[]{true, true, true, true, true, false})
                            .setLabel("", "", "", "点", "分", "")
                            .isCenterLabel(false)
                            .isCyclic(true)
                            .setDividerColor(Color.DKGRAY)
                            .setContentSize(21)
                            .setTitleText("选择时间")
                            .setTitleColor(Color.BLUE)
                            .setTitleBgColor(Color.WHITE)
                            .isDialog(true)
                            .build();
                    pickerView.show();
                }
                break;
            case R.id.right_text_btn:
                //提交
                if("".equals(myleaveapply_type_tv.getText().toString())){
                    ToastUtil.showToast("请选择请假类型",MyLeaveApplyAddActivity.this);
                }else if("".equals(myleaveapplyadd_starttime_tv.getText().toString())){
                    ToastUtil.showToast("请选择开始时间",MyLeaveApplyAddActivity.this);
                }else  if("".equals(myleaveapplyadd_endtime_tv.getText().toString())){
                    ToastUtil.showToast("请选择结束时间",MyLeaveApplyAddActivity.this);
                }else  if("".equals(myleaveapplyadd_reson_edt.getText().toString())){
                    ToastUtil.showToast("请输入请假原因",MyLeaveApplyAddActivity.this);
                }else{
                    waitDialog.show();
                    evaluateInternet.uploadStudentQJSQDatas(type,myleaveapplyadd_starttime_tv.getText().toString(),myleaveapplyadd_endtime_tv.getText().toString(),myleaveapplyadd_reson_edt.getText().toString());
                }
                break;
        }
    }
    public AdapterView.OnItemClickListener typeCliceer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MyLeaveTypeEntity typeEntity = (MyLeaveTypeEntity) typeEntities.get(position);
            type=typeEntity.getId();
            myleaveapply_type_tv.setText(typeEntity.getName());
            typeDailog.dismiss();
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    myleaveapplyadd_starttime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 2000:
                    myleaveapplyadd_endtime_tv.setText((String)msg.obj);
                    dateViewDailog.dismiss();
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",MyLeaveApplyAddActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(evaluatePersener.parseStudnet_QJResultData((String)msg.obj)){
                        ToastUtil.showToast("提交成功",MyLeaveApplyAddActivity.this);
                        if("yes".equals(leavelist)){
                            Intent intent = new Intent();
                            setResult(101,intent);
                            finish();
                        }else{
                            finish();
                        }

                    }else{
                        ToastUtil.showToast("请从新提交",MyLeaveApplyAddActivity.this);
                    }
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<MyLeaveTypeEntity> datas = evaluatePersener.parseMyLeaveTypes_ListData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        if(typeEntities.size()>0){
                            typeEntities.clear();
                        }
                        for(MyLeaveTypeEntity entity:datas){
                            typeEntities.add(entity);
                        }
                    }

                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyLeaveApplyAddActivity.this);
                    break;
            }
        }
    };

}
