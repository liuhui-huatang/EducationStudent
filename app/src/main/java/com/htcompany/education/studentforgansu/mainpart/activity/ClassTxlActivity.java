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
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.myclass.MyClassInternet;
import com.htcompany.education.studentforgansu.internet.myclass.MyClassPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.ClassTxlAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTxlEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级通讯录
 * Created by wrb on 2017/1/13.
 */
public class ClassTxlActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView classtxl_lv;
    private ClassTxlAdapter txlAdapter;
    //网络请求类
    private MyClassInternet teacherInternet;
    private MyClassPersener teacherPersener;
    private WaitDialog waitDialog=null;
    private SharedPrefUtil sharedPrefUtil=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_txl_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvnents();
    }
    public void initDatas(){
        sharedPrefUtil = new SharedPrefUtil(this, "login");
        teacherInternet = new MyClassInternet(this,tableHanler);
        teacherPersener = new MyClassPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getClassTXLDatas();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        classtxl_lv = (ListView)this.findViewById(R.id.classtxl_lv);
        txlAdapter =  new ClassTxlAdapter(this, MyApp.contactsEntities);
        classtxl_lv.setAdapter(txlAdapter);
    }
    public void initViewValues(){
        title.setText("班级通讯录");
    }
    public void initOnclicEvnents(){
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
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",ClassTxlActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    List<ClassTxlEntity> tabledatas = teacherPersener.parseClassTxlData((String)msg.obj);
                    if(tabledatas!=null&&tabledatas.size()>0){
                        initTXLDatas(tabledatas);
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",ClassTxlActivity.this);
                    break;
            }
        }
    };
    public void initTXLDatas(List<ClassTxlEntity> datas){
        if(MyApp.contactsEntities.size()>0){
            MyApp.contactsEntities.clear();
        }
        for(ClassTxlEntity entity:datas){

                MyApp.contactsEntities.add(entity);


        }
        txlAdapter.notifyDataSetChanged();
    }
}
