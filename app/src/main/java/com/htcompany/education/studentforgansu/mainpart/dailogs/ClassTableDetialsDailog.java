package com.htcompany.education.studentforgansu.mainpart.dailogs;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.views.classtable.TimeTableModel;

/**
 * 课程详情
 * Created by wrb on 2017/1/16.
 */
public class ClassTableDetialsDailog extends AlertDialog implements View.OnClickListener{
    private Context context;
    LayoutInflater inflater;
    private TimeTableModel timeTableModel;
    private RelativeLayout classtabledailog_close_rel;
    private TextView classtabledailog_name_tv,classtabledailog_teacher_tv,classtabledailog_room_tv,classtabledailog_time_tv;
    public ClassTableDetialsDailog(Context context, TimeTableModel timeTableModel) {
        super(context);
        this.context = context;
        this.timeTableModel = timeTableModel;
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.classtabledetials_dailog);
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);

        this.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.altdiao_bg));
        initViews();
        initDatas();
    }
    public void initViews(){
        classtabledailog_close_rel=(RelativeLayout)this.findViewById(R.id.classtabledailog_close_rel);
        classtabledailog_name_tv=(TextView)this.findViewById(R.id.classtabledailog_name_tv);
        classtabledailog_teacher_tv=(TextView)this.findViewById(R.id.classtabledailog_teacher_tv);
        classtabledailog_room_tv=(TextView)this.findViewById(R.id.classtabledailog_room_tv);
        classtabledailog_time_tv=(TextView)this.findViewById(R.id.classtabledailog_time_tv);
        classtabledailog_close_rel.setOnClickListener(this);
    }
    public void initDatas(){
        if(timeTableModel!=null){
            classtabledailog_name_tv.setText(timeTableModel.getName());
            classtabledailog_teacher_tv.setText(timeTableModel.getTeacher());
            classtabledailog_room_tv.setText(timeTableModel.getClassroom());
            classtabledailog_time_tv.setText("星期"+timeTableModel.getWeek()+"-"+timeTableModel.getStarttime());
        }
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()){
            case R.id.classtabledailog_close_rel:
                dismiss();
                break;
        }
    }
}