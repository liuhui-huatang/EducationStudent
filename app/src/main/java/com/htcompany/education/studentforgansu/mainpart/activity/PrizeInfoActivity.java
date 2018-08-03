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
import com.htcompany.education.studentforgansu.mainpart.fragments.PunishmentFragment;
import com.htcompany.education.studentforgansu.mainpart.fragments.RewardsFragment;

/**
 * 奖惩记录
 * Created by wrb on 2016/10/24.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class PrizeInfoActivity extends BaseFragmentActivity implements View.OnClickListener{
    private TextView title_leftitem_tv,title_rightitem_tv;
    private int titleItemShowFlag=1;
    private RelativeLayout rebackselector_btn;
    private RewardsFragment rewardsFragment;
    private PunishmentFragment punishmentFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction beginTransaction = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prizeinfo_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        rewardsFragment = new RewardsFragment();
        punishmentFragment = new PunishmentFragment();
    }
    public void initViews(){
        title_leftitem_tv=(TextView)this.findViewById(R.id.title_leftitem_tv);
        title_rightitem_tv=(TextView)this.findViewById(R.id.title_rightitem_tv);
        rebackselector_btn = (RelativeLayout)this.findViewById(R.id.rebackselector_btn);
    }
    public void initViewValues(){
        title_leftitem_tv.setText("奖励记录");
        title_rightitem_tv.setText("惩处记录");
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
                beginTransaction.replace(R.id.prizeinfocontent_framgent, rewardsFragment);
                beginTransaction.commit();
                title_leftitem_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                title_leftitem_tv.setBackground(getResources().getDrawable(R.drawable.title_whit_left_shape));
                break;
            case 2:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.prizeinfocontent_framgent, punishmentFragment);
                beginTransaction.commit();
                title_rightitem_tv.setTextColor(getResources().getColor(R.color.title_bar_color));
                title_rightitem_tv.setBackground(getResources().getDrawable(R.drawable.title_whit_right_shape));
                break;
        }
    }
}
