package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;

/**
 * 论坛详情
 * Created by wrb on 2016/11/21.
 */
public class ShoolsForumDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private LinearLayout shoolsforumdetailbottom_ll;
    private boolean isBottomTilte,isUpSlide;
    private ScrollView content_center_scrollview;
    private float lastY,viewSlop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoolsforumdetails_activity);
        viewSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        initViews();
        initViewValues();
        initOnclicEvents();
//        ShoolsForumCommentListActivity
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        shoolsforumdetailbottom_ll = (LinearLayout)this.findViewById(R.id.shoolsforumdetailbottom_ll);
        content_center_scrollview = (ScrollView)this.findViewById(R.id.content_center_scrollview);
    }
    public void initViewValues(){
        title.setText("论坛详情");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        shoolsforumdetailbottom_ll.setOnClickListener(this);
        content_center_scrollview.setOnTouchListener(scrollTouncher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.shoolsforumdetailbottom_ll:
                Intent intent = new Intent(ShoolsForumDetailsActivity.this,ShoolsForumCommentListActivity.class);
                startActivity(intent);
                break;
        }
    }
  public View.OnTouchListener scrollTouncher = new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {

          switch (event.getAction()) {

              case MotionEvent.ACTION_DOWN:
                  lastY = event.getY();//手指刚刚触摸屏幕位置
                  break;
              case MotionEvent.ACTION_MOVE:
                  float disY = event.getY() - lastY;//滑动的距离
                  //垂直方向滑动
                  if (Math.abs(disY) > viewSlop) {//viewSlop:getScaledTouchSlop是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。
                      //是否向上滑动
                      isUpSlide = disY < 0;//isUpSlide向上滑动为false  向下滑动为true
                      //实现底部tools的显示与隐藏
                      if (isUpSlide) {
                          //向上滑动隐藏底部工具栏 isBottomTilte初始值为false
                          if (!isBottomTilte){
                              hideTools();
                          }
                      } else {
                          //向下滑动，
                          if (isBottomTilte){
                              showTools();
                          }

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

//        ObjectAnimator anim = ObjectAnimator.ofFloat(shoolsforumdetailbottom_ll, "y", shoolsforumdetailbottom_ll.getY(),
//                shoolsforumdetailbottom_ll.getY() - shoolsforumdetailbottom_ll.getHeight());
//        anim.setDuration(300);
//        anim.start();
        shoolsforumdetailbottom_ll.setVisibility(View.VISIBLE);
        isBottomTilte = false;
    }

    /**
     * 隐藏工具栏
     */
    private void hideTools() {

//        ObjectAnimator anim = ObjectAnimator.ofFloat(shoolsforumdetailbottom_ll, "y", shoolsforumdetailbottom_ll.getY(),
//                shoolsforumdetailbottom_ll.getY() + shoolsforumdetailbottom_ll.getHeight());
//        anim.setDuration(300);
//        anim.start();
        shoolsforumdetailbottom_ll.setVisibility(View.GONE);
        isBottomTilte = true;

    }
}
