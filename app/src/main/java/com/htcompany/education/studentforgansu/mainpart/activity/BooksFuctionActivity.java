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
 * 图书功能
 * Created by wrb on 2017/1/5.
 */
public class BooksFuctionActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout bookfunction_xyts_ll,bookfunction_wdyd_ll;
    private Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booksfuction_activity);
        initViews();
        initValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        bookfunction_xyts_ll=(LinearLayout)this.findViewById(R.id.bookfunction_xyts_ll);
        bookfunction_wdyd_ll=(LinearLayout)this.findViewById(R.id.bookfunction_wdyd_ll);
    }
    public void initValues(){
        title.setText("校园图书");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        bookfunction_xyts_ll.setOnClickListener(this);
        bookfunction_wdyd_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.bookfunction_xyts_ll:
                //校园图书
                intent = new Intent(BooksFuctionActivity.this, SchoolBooksActivity.class);
                startActivity(intent);
                break;
            case R.id.bookfunction_wdyd_ll:
                //我的预定
                intent = new Intent(BooksFuctionActivity.this, SchoolBookReserveActivity.class);
                startActivity(intent);
                break;
        }
    }
}
