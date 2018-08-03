package com.htcompany.education.studentforgansu.mainpart.activity;

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
import com.htcompany.education.studentforgansu.mainpart.adapter.TermsRemarkAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.TermsRemarkEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学期评语
 * Created by WRB on 2016/10/24.
 */
public class TermsRemarkActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView tr_recyl;
    private TermsRemarkAdapter qEvaluationAdapter;
    private List<TermsRemarkEntity> termsRemarkEntities;
    //网络请求类
    private MyEvaluateInternet evaluateInternet;
    private MyEvaluatePersener evaluatePersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termremark_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        termsRemarkEntities = new ArrayList<TermsRemarkEntity>();
        evaluateInternet = new MyEvaluateInternet(this,myHandler);
        evaluatePersener = new MyEvaluatePersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        evaluateInternet.getStudentTermPYDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        tr_recyl = (ListView) this.findViewById(R.id.tr_recyl);
        qEvaluationAdapter = new TermsRemarkAdapter(this,termsRemarkEntities);
        tr_recyl.setAdapter(qEvaluationAdapter);
    }
    public void initViewValues(){
        title.setText("学期评语");
    }
    public void initOnclicEvents(){
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
                    ToastUtil.showToast("服务器连接失败",TermsRemarkActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TermsRemarkEntity> datas=evaluatePersener.parseStudnetTermPYData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TermsRemarkActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas( List<TermsRemarkEntity> datas){
        if(termsRemarkEntities.size()>0){
            termsRemarkEntities.clear();
        }
        for(TermsRemarkEntity entity:datas){
            termsRemarkEntities.add(entity);
        }
        qEvaluationAdapter.notifyDataSetChanged();
    }
}
