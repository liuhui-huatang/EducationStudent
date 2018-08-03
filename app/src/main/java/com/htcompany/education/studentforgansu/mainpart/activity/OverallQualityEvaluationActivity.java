package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.mainpart.fragments.OqeHealthFragment;
import com.htcompany.education.studentforgansu.mainpart.fragments.OqeKnowledgeFragment;
import com.htcompany.education.studentforgansu.mainpart.fragments.OqeMoralityFragment;
import com.htcompany.education.studentforgansu.mainpart.fragments.OqeQualityFragment;
import com.htcompany.education.studentforgansu.mainpart.fragments.OqeSkillsFragment;

/**
 * 综合素质评价
 * Created by wrb on 2016/10/24.
 */
public class OverallQualityEvaluationActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    //健康水平，知识能力，品德发展，职业技能，身心素质
    private RelativeLayout oqe_health_rel,oqe_knowledge_rel,oqe_morality_rel,oqe_skills_rel;
    private ImageView oqe_health_img,oqe_knowledge_img,oqe_morality_img,oqe_skills_img,oqe_quality_img;
    private TextView oqe_health_tv,oqe_knowledge_tv,oqe_morality_tv,oqe_skills_tv,oqe_quality_tv;
    private int titlebarFlag=3;

    private OqeHealthFragment healthFragment;
    private OqeKnowledgeFragment knowledgeFragment;
    private OqeMoralityFragment moralityFragment;
    private OqeSkillsFragment skillsFragment;
    private OqeQualityFragment qualityFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction beginTransaction = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overallqualityevaluation_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        healthFragment = new OqeHealthFragment();
        knowledgeFragment = new OqeKnowledgeFragment();
        moralityFragment = new OqeMoralityFragment();
        skillsFragment = new OqeSkillsFragment();
//        qualityFragment = new OqeQualityFragment();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);

        oqe_health_rel= (RelativeLayout)this.findViewById(R.id.oqe_health_rel);
        oqe_knowledge_rel= (RelativeLayout)this.findViewById(R.id.oqe_knowledge_rel);
        oqe_morality_rel= (RelativeLayout)this.findViewById(R.id.oqe_morality_rel);
        oqe_skills_rel= (RelativeLayout)this.findViewById(R.id.oqe_skills_rel);
//        oqe_quality_rel= (RelativeLayout)this.findViewById(R.id.oqe_quality_rel);

        oqe_health_img=(ImageView)this.findViewById(R.id.oqe_health_img) ;
        oqe_knowledge_img=(ImageView)this.findViewById(R.id.oqe_knowledge_img) ;
        oqe_morality_img=(ImageView)this.findViewById(R.id.oqe_morality_img) ;
        oqe_skills_img=(ImageView)this.findViewById(R.id.oqe_skills_img) ;
//        oqe_quality_img=(ImageView)this.findViewById(R.id.oqe_quality_img) ;

        oqe_health_tv= (TextView)this.findViewById(R.id.oqe_health_tv);
        oqe_knowledge_tv= (TextView)this.findViewById(R.id.oqe_knowledge_tv);
        oqe_morality_tv= (TextView)this.findViewById(R.id.oqe_morality_tv);
        oqe_skills_tv= (TextView)this.findViewById(R.id.oqe_skills_tv);
//        oqe_quality_tv= (TextView)this.findViewById(R.id.oqe_quality_tv);

    }
    public void initViewValues(){
        title.setText("综合素质评价");
        setSelectTitlebar(titlebarFlag);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        oqe_health_rel.setOnClickListener(this);
        oqe_knowledge_rel.setOnClickListener(this);
        oqe_morality_rel.setOnClickListener(this);
        oqe_skills_rel.setOnClickListener(this);
//        oqe_quality_rel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.oqe_health_rel:
                //健康水平
                titlebarFlag=1;
                setSelectTitlebar(titlebarFlag);
                break;
            case R.id.oqe_knowledge_rel:
                //知识水平
                titlebarFlag=2;
                setSelectTitlebar(titlebarFlag);
                break;
            case R.id.oqe_morality_rel:
                //德育
                titlebarFlag=3;
                setSelectTitlebar(titlebarFlag);
                break;
            case R.id.oqe_skills_rel:
                //职业技能
                titlebarFlag=4;
                setSelectTitlebar(titlebarFlag);
                break;
//            case R.id.oqe_quality_rel:
//                //身心素质
//                titlebarFlag=5;
//                setSelectTitlebar(titlebarFlag);
//                break;
        }
    }

    /**
     * 设置选中标题样式
     * @param index
     */
    public void setSelectTitlebar(int index){
        setTitleBarNomal();
        switch (index){
            case 1:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.oqe_fragment_content, healthFragment);
                beginTransaction.commit();
                oqe_health_img.setImageDrawable(getResources().getDrawable(R.mipmap.jiankan_n));
                oqe_health_tv.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.oqe_fragment_content, knowledgeFragment);
                beginTransaction.commit();
                oqe_knowledge_img.setImageDrawable(getResources().getDrawable(R.mipmap.zhishi_n));
                oqe_knowledge_tv.setTextColor(getResources().getColor(R.color.white));
                break;
            case 3:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.oqe_fragment_content, moralityFragment);
                beginTransaction.commit();
                oqe_morality_img.setImageDrawable(getResources().getDrawable(R.mipmap.pinde));
                oqe_morality_tv.setTextColor(getResources().getColor(R.color.white));
                break;
            case 4:
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.oqe_fragment_content, skillsFragment);
                beginTransaction.commit();
                oqe_skills_img.setImageDrawable(getResources().getDrawable(R.mipmap.jineng_n));
                oqe_skills_tv.setTextColor(getResources().getColor(R.color.white));
                break;
//            case 5:
//                beginTransaction = fragmentManager.beginTransaction();
//                beginTransaction.replace(R.id.oqe_fragment_content, qualityFragment);
//                beginTransaction.commit();
//                oqe_quality_img.setImageDrawable(getResources().getDrawable(R.mipmap.shenxin_n));
//                oqe_quality_tv.setTextColor(getResources().getColor(R.color.white));
//                break;
        }
    }
    public void setTitleBarNomal(){
        oqe_health_img.setImageDrawable(getResources().getDrawable(R.mipmap.jiankan));
        oqe_knowledge_img.setImageDrawable(getResources().getDrawable(R.mipmap.zhishi));
        oqe_morality_img.setImageDrawable(getResources().getDrawable(R.mipmap.pinde_n));
        oqe_skills_img.setImageDrawable(getResources().getDrawable(R.mipmap.jineng));
//        oqe_quality_img.setImageDrawable(getResources().getDrawable(R.mipmap.shenxin));

        oqe_health_tv.setTextColor(getResources().getColor(R.color.oqe_title_color));
        oqe_knowledge_tv.setTextColor(getResources().getColor(R.color.oqe_title_color));
        oqe_morality_tv.setTextColor(getResources().getColor(R.color.oqe_title_color));
        oqe_skills_tv.setTextColor(getResources().getColor(R.color.oqe_title_color));
//        oqe_quality_tv.setTextColor(getResources().getColor(R.color.oqe_title_color));
    }
}
