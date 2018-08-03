package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluateInternet;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluatePersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.MyleaveApplyAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.LeaveApplyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 请假申请列表
 * Created wrb Administrator on 2016/11/10.
 */
public class MyLeaveApplyActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private ListView myleaveapply_lv;
    private MyleaveApplyAdapter myleaveApplyAdapter;
    private List<LeaveApplyEntity> leaveApplyEntities;
    //网络请求类
    private MyEvaluateInternet evaluateInternet;
    private MyEvaluatePersener evaluatePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myleaveapply_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
            leaveApplyEntities = new ArrayList<LeaveApplyEntity>();
            evaluateInternet = new MyEvaluateInternet(MyLeaveApplyActivity.this,myHandler);
            evaluatePersener = new MyEvaluatePersener(MyLeaveApplyActivity.this);
            waitDialog = new WaitDialog(MyLeaveApplyActivity.this,"");
            waitDialog.show();
            evaluateInternet.getStudentQJ_LISTDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_text_btn = (RelativeLayout)this.findViewById(R.id.right_text_btn);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        myleaveapply_lv = (ListView)this.findViewById(R.id.myleaveapply_lv);
        myleaveApplyAdapter = new MyleaveApplyAdapter(this,leaveApplyEntities);
        myleaveapply_lv.setAdapter(myleaveApplyAdapter);
    }
    public void initViewValues(){
        title.setText("请假记录");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("申请");
    }
  public void initOnclicEvents(){
      right_text_btn.setOnClickListener(this);
      reback_btn.setOnClickListener(this);
  }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_text_btn:
                Intent intent = new Intent(MyLeaveApplyActivity.this,MyLeaveApplyAddActivity.class);
                intent.putExtra("leavelist","yes");
                startActivityForResult(intent,101);
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
                    ToastUtil.showToast("服务器连接失败",MyLeaveApplyActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<LeaveApplyEntity> datas = evaluatePersener.parseStudnetQJData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterValues(datas);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",MyLeaveApplyActivity.this);
                    break;
            }
        }
    };
    public void setAdapterValues(List<LeaveApplyEntity> datas){
        if(leaveApplyEntities.size()>0){
            leaveApplyEntities.clear();
        }
        for(LeaveApplyEntity entity:datas){
            leaveApplyEntities.add(entity);
        }
        myleaveApplyAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            switch (resultCode){
                case 101:
                    evaluateInternet.getStudentQJ_LISTDatas();
                    break;
            }
        }
    }
}

