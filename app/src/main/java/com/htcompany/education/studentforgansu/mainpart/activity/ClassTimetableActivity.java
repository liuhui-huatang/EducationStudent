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
import com.htcompany.education.studentforgansu.commonpart.views.classtable.TimeTableModel;
import com.htcompany.education.studentforgansu.commonpart.views.classtable.TimeTableView;
import com.htcompany.education.studentforgansu.internet.myclass.MyClassInternet;
import com.htcompany.education.studentforgansu.internet.myclass.MyClassPersener;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTableEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级课表
 * Created by wrb on 2016/11/11.
 */
public class ClassTimetableActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TimeTableView mTimaTableView;
    private List<TimeTableModel> mList;
    private List<ClassTableEntity> tableEntities;
    //网络请求类
    private MyClassInternet teacherInternet;
    private MyClassPersener teacherPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classtimetable_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }

    public void initDatas(){
        teacherInternet = new MyClassInternet(this,tableHanler);
        teacherPersener = new MyClassPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        teacherInternet.getClassTableDatas("");
        mList = new ArrayList<TimeTableModel>();
        tableEntities = new ArrayList<ClassTableEntity>();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        mTimaTableView = (TimeTableView) findViewById(R.id.main_timetable_ly);
    }
    public void initViewValues(){
        title.setText("班级课表");
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
    public Handler tableHanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",ClassTimetableActivity.this);
                    break;
                case 200:
                    //网络请求成功，解析数据
                    List<ClassTableEntity> tabledatas = teacherPersener.parseClassTableData((String)msg.obj);
                    if(tabledatas!=null&&tabledatas.size()>0){
                        initClassTableDatas(tabledatas);
                    }
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",ClassTimetableActivity.this);
                    break;
            }
        }
    };


    /**
     * 初始化课表数据
     */
    public void initClassTableDatas(List<ClassTableEntity> tabledatas){
        for(ClassTableEntity tableEntity:tabledatas){
            TimeTableModel tableModel = new TimeTableModel();
            tableModel.setClassroom(tableEntity.getRoomname());//教室
            tableModel.setStartnum(tableEntity.getSecid());//开始节数
            tableModel.setEndnum(tableEntity.getSecid());//节数节数
            tableModel.setStarttime(tableEntity.getTime());//开始时间
            tableModel.setEndtime(tableEntity.getTime());//结束时间
            tableModel.setName(tableEntity.getCourcename());//课程名称
            tableModel.setId(tableEntity.getId());
            tableModel.setWeek(tableEntity.getWeek());
            tableModel.setTeacher(tableEntity.getTeachername());
            tableModel.setClassname(tableEntity.getClassname());//班级名称
            mList.add(tableModel);
        }
        mTimaTableView.setTimeTable(mList);
    }

}
