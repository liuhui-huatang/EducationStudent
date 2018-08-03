package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;

/**
 * 我的评价
 * Created by wrb on 2017/1/5.
 */
public class EvaluateFuctionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout evaluatefuction_zhszpj_ll,evaluatefuction_xqpy_ll,evaluatefuction_bypy_ll,evaluatefuction_xsjc_ll;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluatefuction_activity);
        initViews();
        initValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        evaluatefuction_zhszpj_ll=(LinearLayout)this.findViewById(R.id.evaluatefuction_zhszpj_ll);
        evaluatefuction_xqpy_ll=(LinearLayout)this.findViewById(R.id.evaluatefuction_xqpy_ll);
        evaluatefuction_bypy_ll=(LinearLayout)this.findViewById(R.id.evaluatefuction_bypy_ll);
        evaluatefuction_xsjc_ll=(LinearLayout)this.findViewById(R.id.evaluatefuction_xsjc_ll);
    }
    public void initValues(){
        title.setText("我的评价");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        evaluatefuction_zhszpj_ll.setOnClickListener(this);
        evaluatefuction_xqpy_ll.setOnClickListener(this);
        evaluatefuction_bypy_ll.setOnClickListener(this);
        evaluatefuction_xsjc_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
//            case R.id.evaluatefuction_zhszpj_ll:
//                //综合素质评价
//                intent = new Intent(EvaluateFuctionActivity.this, OverallQualityEvaluationActivity.class);
//                startActivity(intent);
//                break;
            case R.id.evaluatefuction_xqpy_ll:
                //毕业评语
                intent = new Intent(EvaluateFuctionActivity.this, GraduatedCommentsActivity.class);
                startActivity(intent);
                break;
            case R.id.evaluatefuction_bypy_ll:
                //学期评语
                intent = new Intent(EvaluateFuctionActivity.this, TermsRemarkActivity.class);
                startActivity(intent);
                break;
            case R.id.evaluatefuction_xsjc_ll:
                //学生奖惩
                intent = new Intent(EvaluateFuctionActivity.this, PrizeInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
