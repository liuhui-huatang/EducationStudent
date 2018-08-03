package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.PullMoreListView;
import com.htcompany.education.studentforgansu.mainpart.adapter.ShoolsForumListAdapter;

/**
 * 校园论坛界面
 * Created by wrb on 2016/11/21.
 */
public class ShoolsForumListActivity extends BaseActivity implements View.OnClickListener,PullMoreListView.MyPullUpListViewCallBack,SwipeRefreshLayout.OnRefreshListener {
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private PullMoreListView shoolsforumlist_lv;
    private ShoolsForumListAdapter forumListAdapter;
    private SwipeRefreshLayout shoolsforumcomment_swiperefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoolsforumlist_activity);
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv= (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn=(RelativeLayout)this.findViewById(R.id.right_text_btn);
        shoolsforumcomment_swiperefresh = (SwipeRefreshLayout)this.findViewById(R.id.shoolsforumcomment_swiperefresh);
        shoolsforumcomment_swiperefresh.setColorScheme(R.color.class_jljl_bgcolor, R.color.xytp_xq_textcolor,
                R.color.qj_btncolor, R.color.xytp_xq_texttwocolor);
        shoolsforumcomment_swiperefresh.setOnRefreshListener(this);
        shoolsforumlist_lv = (PullMoreListView)this.findViewById(R.id.shoolsforumlist_lv);
        forumListAdapter = new ShoolsForumListAdapter(this);
        shoolsforumlist_lv.setAdapter(forumListAdapter);
        shoolsforumlist_lv.setMyPullUpListViewCallBack(this);
    }
    public void initViewValues(){
        title.setText("发布");
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("发布");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        shoolsforumlist_lv.setOnItemClickListener(formClicer);
        right_text_btn.setOnClickListener(this);
    }
    public AdapterView.OnItemClickListener formClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ShoolsForumListActivity.this,ShoolsForumDetailsActivity.class);
            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_text_btn:
                Intent intent = new Intent(ShoolsForumListActivity.this,ShoolsForumAddCommentActivity.class);
                startActivity(intent);
                break;
        }
    }
    //加载更多
    @Override
    public void scrollBottomState() {
        ToastUtil.showToast("加载更多",ShoolsForumListActivity.this);
    }
    //下拉刷新
    @Override
    public void onRefresh() {

    }
}
