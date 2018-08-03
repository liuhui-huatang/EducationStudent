package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.mainpart.adapter.SchollvotingListAdapter;

/**
 * 学校投票
 * Created by wrb on 2016/10/26.
 */
public class SchollVotingActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView schollvoting_lv;
    private SchollvotingListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schollvoting_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){

    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        schollvoting_lv=(ListView)this.findViewById(R.id.schollvoting_lv);
        adapter = new SchollvotingListAdapter(this);
        schollvoting_lv.setAdapter(adapter);
        schollvoting_lv.setOnItemClickListener(votingClicer);
    }
    public void initViewValues(){
        title.setText("投票");
    }

    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener votingClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(SchollVotingActivity.this,SchollVotingDetailsActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                finish();
                break;
        }
    }
}
