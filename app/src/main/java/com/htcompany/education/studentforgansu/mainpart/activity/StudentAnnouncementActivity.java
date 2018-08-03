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
import com.htcompany.education.studentforgansu.commonpart.views.xlistview.XListView;
import com.htcompany.education.studentforgansu.internet.myclass.MyClassInternet;
import com.htcompany.education.studentforgansu.internet.myclass.MyClassPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.ClassAnnouncementAdapter;
import com.htcompany.education.studentforgansu.mainpart.adapter.StudentAnnouncementAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassAnnouncementEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 学生公告
 * Created by wrb on 2016/10/24.
 */
public class StudentAnnouncementActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private StudentAnnouncementAdapter classAnnouncementAdapter;
    private XListView classgg_recy;
    private List<ClassAnnouncementEntity> announcementEntities;
    private int pageNum=1;
    //网络请求类
    private MyClassInternet teacherInternet;
    private MyClassPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classannouncement_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        announcementEntities = new ArrayList<ClassAnnouncementEntity>();
        teacherInternet = new MyClassInternet(this,tableHanler);
        teacherPersener = new MyClassPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getStudentGGDatas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        classgg_recy = (XListView) this.findViewById(R.id.classgg_recy) ;
        classgg_recy.setPullRefreshEnable(true);
        classgg_recy.setPullLoadEnable(false);
        classgg_recy.setXListViewListener(this);
        classAnnouncementAdapter = new StudentAnnouncementAdapter(this,announcementEntities);
        classgg_recy.setAdapter(classAnnouncementAdapter);
    }
    public void initViewValues(){
        title.setText("学生公告");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        classgg_recy.setOnItemClickListener(announceClicer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
        }
    }
    public AdapterView.OnItemClickListener announceClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ClassAnnouncementEntity entity = (ClassAnnouncementEntity)classAnnouncementAdapter.getItem(position-1);
            Intent intent = new Intent(StudentAnnouncementActivity.this,AnnouncementDetailsActivity.class);
            intent.putExtra("entity",entity);
            startActivity(intent);
        }
    };
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",StudentAnnouncementActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<ClassAnnouncementEntity> datas = teacherPersener.parseClassGGData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else {
                        stopListView();
                        classgg_recy.setPullLoadEnable(false);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",StudentAnnouncementActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas( List<ClassAnnouncementEntity> datas){
        if(pageNum==1) {
            if (announcementEntities.size() > 0) {
                announcementEntities.clear();
            }
        }
        if(datas.size()>=10){
            classgg_recy.setPullLoadEnable(true);
        }else{
            classgg_recy.setPullLoadEnable(false);
        }
        for(ClassAnnouncementEntity entity:datas){
            announcementEntities.add(entity);
        }
        classAnnouncementAdapter.notifyDataSetChanged();
        stopListView();
    }
    @Override
    public void onRefresh() {
        pageNum=1;
        teacherInternet.getStudentGGDatas(String.valueOf(pageNum));
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        teacherInternet.getStudentGGDatas(String.valueOf(pageNum));
    }
    /**
     * 停止列表刷新
     */
    public void stopListView() {
        classgg_recy.stopRefresh();
        classgg_recy.stopLoadMore();
        classgg_recy.setRefreshTime("刚刚");
    }
}
