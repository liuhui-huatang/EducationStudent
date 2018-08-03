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
import com.htcompany.education.studentforgansu.mainpart.fragments.BodyCheckedFragment;
import com.htcompany.education.studentforgansu.mainpart.fragments.BodyPhysiqueFragment;

/**
 * 体检体质信息
 * Created by WRB on 2016/10/21.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class BodyCheckedActivity extends BaseFragmentActivity implements View.OnClickListener{
    private TextView title_leftitem_tv,title_rightitem_tv;
    private RelativeLayout rebackselector_btn;
    private int titleItemShowFlag=1;
    private BodyCheckedFragment checkedFragment;//体检界面
    private BodyPhysiqueFragment physiqueFragment;//体质界面
    private FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction beginTransaction = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bodychecked_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        checkedFragment = new BodyCheckedFragment();
        physiqueFragment = new BodyPhysiqueFragment();
    }
    public void initViews(){
        rebackselector_btn=(RelativeLayout)this.findViewById(R.id.rebackselector_btn);
        title_leftitem_tv=(TextView)this.findViewById(R.id.title_leftitem_tv);
        title_rightitem_tv=(TextView)this.findViewById(R.id.title_rightitem_tv);
    }
    public void initViewValues(){
        title_leftitem_tv.setText("体检信息");
        title_rightitem_tv.setText("体质信息");
        initSelectViews(titleItemShowFlag);
    }
    public void initOnclicEvents(){
        title_leftitem_tv.setOnClickListener(this);
        title_rightitem_tv.setOnClickListener(this);
        rebackselector_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rebackselector_btn:
                this.finish();
                break;
            case R.id.title_leftitem_tv:
                titleItemShowFlag=1;
                initSelectViews(titleItemShowFlag);
                break;
            case R.id.title_rightitem_tv:
                titleItemShowFlag=2;
                initSelectViews(titleItemShowFlag);
                break;
        }
    }

    public void initNormalViews(){

        title_leftitem_tv.setTextColor(getResources().getColor(R.color.white));
        title_leftitem_tv.setBackground(getResources().getDrawable(R.drawable.title_tcolor_left_shape));

        title_rightitem_tv.setTextColor(getResources().getColor(R.color.white));
        title_rightitem_tv.setBackground(getResources().getDrawable(R.drawable.title_tcolor_right_shape));
    }
    public void initSelectViews(int viewFlag){
        initNormalViews();
        switch (viewFlag){
            case 1:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.bodycontent_framgent, checkedFragment);
                beginTransaction.commit();
                title_leftitem_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                title_leftitem_tv.setBackground(getResources().getDrawable(R.drawable.title_whit_left_shape));
                break;
            case 2:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.bodycontent_framgent, physiqueFragment);
                beginTransaction.commit();
                title_rightitem_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                title_rightitem_tv.setBackground(getResources().getDrawable(R.drawable.title_whit_right_shape));
                break;
        }
    }
}
