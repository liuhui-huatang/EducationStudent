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
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluateInternet;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluatePersener;
import com.htcompany.education.studentforgansu.mainpart.entity.GraduatedCommentEntity;

/**
 * 毕业评语
 * Created by wrb on 2016/10/25.
 */
public class GraduatedCommentsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView graduated_sname_tv,graduated_content_tv,graduated_teachername_tv;
    //网络请求类
    private MyEvaluateInternet evaluateInternet;
    private MyEvaluatePersener evaluatePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graduatedcomments_activity);
        initDatas();
        initViews();
        initViewValues();
        initClicerEvents();
    }
    public void initDatas(){
        evaluateInternet = new MyEvaluateInternet(GraduatedCommentsActivity.this,myHandler);
        evaluatePersener = new MyEvaluatePersener(GraduatedCommentsActivity.this);
        waitDialog = new WaitDialog(GraduatedCommentsActivity.this,"");
        waitDialog.show();
        evaluateInternet.getStudentBYPYDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        graduated_sname_tv= (TextView)this.findViewById(R.id.graduated_sname_tv);
        graduated_content_tv= (TextView)this.findViewById(R.id.graduated_content_tv);
        graduated_teachername_tv= (TextView)this.findViewById(R.id.graduated_teachername_tv);
    }
    public void initViewValues(){
        title.setText("毕业评语");
    }
    public void initClicerEvents(){
        reback_btn.setOnClickListener(this);
    }
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
                    ToastUtil.showToast("服务器连接失败",GraduatedCommentsActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    GraduatedCommentEntity entity = evaluatePersener.parseStudnetBYPYData((String)msg.obj);
                    if(entity!=null){
                        graduated_sname_tv.setText(entity.getStudent()+"同学:");
                        graduated_content_tv.setText(entity.getPinyu());
                        graduated_teachername_tv.setText("评价教师:"+entity.getTeacher());
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",GraduatedCommentsActivity.this);
                    break;
            }
        }
    };
}
