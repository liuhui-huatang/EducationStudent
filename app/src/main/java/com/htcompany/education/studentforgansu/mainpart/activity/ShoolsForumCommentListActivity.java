package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.mainpart.adapter.ShoolsForumCommentListAdapter;

/**
 * 论坛评论列表详情
 * Created by wrb on 2016/11/24.
 */
public class ShoolsForumCommentListActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private ListView shoolsforumcommentlist_lv;
    private ShoolsForumCommentListAdapter commentListAdapter;
    private LinearLayout forumcommentbottom_ll;
   //上滑隐藏按钮，下滑显示按钮
    private boolean isBottomTilte,isUpSlide;
    private float lastY,viewSlop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoolsforumcommentlist_activity);
        viewSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        shoolsforumcommentlist_lv = (ListView)this.findViewById(R.id.shoolsforumcommentlist_lv);
        commentListAdapter = new ShoolsForumCommentListAdapter(this);
        shoolsforumcommentlist_lv.setAdapter(commentListAdapter);
        forumcommentbottom_ll = (LinearLayout)this.findViewById(R.id.forumcommentbottom_ll);
    }
    public void initViewValues(){
        title.setText("评论");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        forumcommentbottom_ll.setOnClickListener(this);
        shoolsforumcommentlist_lv.setOnTouchListener(scrollTouncher);
        shoolsforumcommentlist_lv.setOnItemClickListener(formClicer);
    }
    public AdapterView.OnItemClickListener formClicer = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Intent intent = new Intent(ShoolsForumListActivity.this,ShoolsForumDetailsActivity.class);
//            startActivity(intent);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.forumcommentbottom_ll:
               Intent intent = new Intent(ShoolsForumCommentListActivity.this,ShoolsForumAddCommentActivity.class);
                startActivity(intent);
                break;

        }
    }
    public View.OnTouchListener scrollTouncher = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    lastY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float disY = event.getY() - lastY;
                    //垂直方向滑动
                    if (Math.abs(disY) > viewSlop) {
                        //是否向上滑动
                        isUpSlide = disY < 0;
                        //实现底部tools的显示与隐藏
                        if (isUpSlide) {
                            if (!isBottomTilte)
                                hideTools();
                        } else {
                            if (isBottomTilte)
                                showTools();
                        }
                    }

                    break;
            }

            return false;
        }
    };
    /**
     * 显示工具栏
     */
    private void showTools() {

//        ObjectAnimator anim = ObjectAnimator.ofFloat(forumcommentbottom_ll, "y", forumcommentbottom_ll.getY(),
//                forumcommentbottom_ll.getY() - forumcommentbottom_ll.getHeight());
//        anim.setDuration(300);
//        anim.start();
        forumcommentbottom_ll.setVisibility(View.VISIBLE);
        isBottomTilte = false;
    }

    /**
     * 隐藏工具栏
     */
    private void hideTools() {

//        ObjectAnimator anim = ObjectAnimator.ofFloat(forumcommentbottom_ll, "y", forumcommentbottom_ll.getY(),
//                forumcommentbottom_ll.getY() + forumcommentbottom_ll.getHeight());
//        anim.setDuration(300);
//        anim.start();
        forumcommentbottom_ll.setVisibility(View.GONE);
        isBottomTilte = true;

    }
}
