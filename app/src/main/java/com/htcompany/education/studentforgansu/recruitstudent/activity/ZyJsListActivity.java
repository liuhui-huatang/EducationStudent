package com.htcompany.education.studentforgansu.recruitstudent.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.zhaosheng.ZhaoShengInternet;
import com.htcompany.education.studentforgansu.internet.zhaosheng.ZhaoShengPersoner;
import com.htcompany.education.studentforgansu.mainpart.entity.DYNewsEntity;
import com.htcompany.education.studentforgansu.recruitstudent.RecruitStudentActivity;
import com.htcompany.education.studentforgansu.recruitstudent.adapter.RecruitStudentZYAdapter;
import com.htcompany.education.studentforgansu.recruitstudent.entity.RecruitStudentZYEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 专业介绍列表
 * Created by weiruibin on 2017/6/29.
 */

public class ZyJsListActivity extends BaseActivity{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView zyjslist_lv;
    private RecruitStudentZYAdapter studentZYAdapter;
    private List<RecruitStudentZYEntity> studentZYEntities;
    private ZhaoShengInternet zhaoShengInternet;
    private ZhaoShengPersoner zhaoShengPersoner;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zyjslist_activity);
        initDatas();
        initViews();
    }
    public void initDatas(){
        studentZYEntities=new ArrayList<RecruitStudentZYEntity>();
        zhaoShengInternet = new ZhaoShengInternet(this,handler);
        zhaoShengPersoner = new ZhaoShengPersoner(this,handler);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        zhaoShengInternet.get_zyDatas();
    }
    public void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        zyjslist_lv=(ListView) this.findViewById(R.id.zyjslist_lv);
        studentZYAdapter = new RecruitStudentZYAdapter(this,studentZYEntities);
        zyjslist_lv.setAdapter(studentZYAdapter);
        title.setText("专业介绍");
        reback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        zyjslist_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecruitStudentZYEntity entity = (RecruitStudentZYEntity) studentZYAdapter.getItem(position);
                Intent intent = new Intent(ZyJsListActivity.this, ZyJsDetailsActivity.class);
                intent.putExtra("entity",entity);
                startActivity(intent);
            }
        });
    }
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 222:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<RecruitStudentZYEntity> datas = zhaoShengPersoner.parseZYLISTData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        if(studentZYEntities.size()>0){
                            studentZYEntities.clear();
                        }
                        for(RecruitStudentZYEntity entity:datas){
                            studentZYEntities.add(entity);
                        }
                        studentZYAdapter.notifyDataSetChanged();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",ZyJsListActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",ZyJsListActivity.this);
                    break;
            }
        }
    };
}
