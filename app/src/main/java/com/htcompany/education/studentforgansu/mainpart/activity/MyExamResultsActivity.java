package com.htcompany.education.studentforgansu.mainpart.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseFragmentActivity;
import com.htcompany.education.studentforgansu.mainpart.fragments.MyExamFragment;

/**
 * Created by Administrator on 2016/10/20.
 * 我的成绩
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class MyExamResultsActivity extends BaseFragmentActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private MyExamFragment myExamFragment;//我的成绩
    private FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction beginTransaction = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myexamresult_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
                myExamFragment =new MyExamFragment();
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.examcontent_framgent, myExamFragment);
                beginTransaction.commit();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
    }
    public void initViewValues(){
        title.setText("我的成绩");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
        }
    }



}
