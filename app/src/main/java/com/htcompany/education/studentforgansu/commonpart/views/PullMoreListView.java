package com.htcompany.education.studentforgansu.commonpart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;

/**
 * 自定义下拉加载更多listview
 * Created by wrb on 2016/12/22.
 * 我提供一个想法：ListView自带方法中具有添加尾部布局的方法，这样的话，
 * 当我们监听到拉到最后的时候，出现尾部布局并加载新的数据，等加载完后，
 * 更新ListView的中的数据，那些数据将自动把尾部布局压在底下看不到。
 * 如此反复，便可以实现上拉加载更多的功能。
 * a)为listview添加地步布局
 * b)为ListView添加底部监听
 * c)实现ListView滑到底部时所要执行的用户操作
 */
public class PullMoreListView extends ListView implements AbsListView.OnScrollListener{
    private View footerview;//底部布局
    private Context context;
    /** 记录第一行Item的数值 */
    private int firstVisibleItem;
    /** 记录最后一行Item的数值 */
    private int bottomeVisibleItem;
    /** 上拉刷新的ListView的回调监听 */
    private MyPullUpListViewCallBack myPullUpListViewCallBack;
    private boolean isLoadingMore=false;
    private TextView namen;
    public PullMoreListView(Context context) {
        super(context);
        this.context = context;
        initFooterView();
    }

    public PullMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initFooterView();
    }

    public PullMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initFooterView();
    }
    //初始化底部布局
    public void initFooterView(){
        if(footerview==null){
            footerview = LayoutInflater.from(context).inflate(R.layout.pullmorelistview_footerview,null);
            namen = (TextView)footerview.findViewById(R.id.namen);
        }
        addFooterView(footerview);
        // 为ListView设置滑动监听
        setOnScrollListener(this);
        // 去掉底部分割线
        setFooterDividersEnabled(false);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int count = getLastVisiblePosition()+1;
        //当滑动到底部时
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && firstVisibleItem != 0&&count==bottomeVisibleItem) {
            if(!isLoadingMore){
                isLoadingMore=true;
                myPullUpListViewCallBack.scrollBottomState();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        this.bottomeVisibleItem = totalItemCount;
        if (footerview != null) {
            //判断可视Item是否能在当前页面完全显示
            if (visibleItemCount == totalItemCount) {
                // removeFooterView(footerView);
                footerview.setVisibility(View.GONE);//隐藏底部布局
            } else {
                // addFooterView(footerView);
                footerview.setVisibility(View.VISIBLE);//显示底部布局
            }
        }

    }
    public void setMyPullUpListViewCallBack(MyPullUpListViewCallBack myPullUpListViewCallBack) {
        this.myPullUpListViewCallBack = myPullUpListViewCallBack;
    }
   /** * 上拉刷新的ListView的回调监听
    *
            * @author xiejinxiong
    *
            */
    public interface MyPullUpListViewCallBack {

        void scrollBottomState();
    }

    /**
     * 隐藏
     */
    public void setPullMoreHiden(){
        namen.setText("已没有更多");
        isLoadingMore=true;
    }

    /**
     * 展示下拉界面
     */
    public void setPullMoreVisible(){
        footerview.setVisibility(View.VISIBLE);//显示底部布局
        isLoadingMore=false;
    }
    public void stopLoadMore(){
        isLoadingMore =false;
    }
}
