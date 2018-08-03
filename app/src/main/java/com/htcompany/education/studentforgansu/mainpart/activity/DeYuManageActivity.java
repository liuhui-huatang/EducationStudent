package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.MainActivity;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.BaseUtils;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.xlistview.XListView;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.DeYuManageAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.BannarEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.DYNewsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 德育管理
 * Created by wrb on 2016/11/29.
 */
public class DeYuManageActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private TextView title;
    private RelativeLayout reback_btn;

    private XListView deyumanage_lv;
    private DeYuManageAdapter deYuManageAdapter;
    private int pageNum=1;

    //==========网络请求类===========
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private List<DYNewsEntity> newsEntities;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deyumanage_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        newsEntities = new ArrayList<DYNewsEntity>();
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        waitDialog= new WaitDialog(this,"");
        waitDialog.show();
        thingsInternet.get_DYXWDatas("",String.valueOf(pageNum));
    }
    public void initViews(){
        title=(TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        deyumanage_lv = (XListView)this.findViewById(R.id.deyumanage_lv);
        deyumanage_lv.setPullRefreshEnable(true);
        deyumanage_lv.setPullLoadEnable(false);
        deyumanage_lv.setXListViewListener(this);
        deYuManageAdapter = new DeYuManageAdapter(this,newsEntities);
        deyumanage_lv.setAdapter(deYuManageAdapter);
        deyumanage_lv.setOnItemClickListener(itemClickListener);
    }
    public void initViewValues(){
        title.setText("德育新闻");
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
     switch (v.getId()){
    case R.id.reback_btn:
        this.finish();
        break;
}
    }

    public AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DYNewsEntity entity = (DYNewsEntity) deYuManageAdapter.getItem(position-1);
            startActivity(new Intent(DeYuManageActivity.this, MainNewsDetailsActivity.class)
                    .putExtra("entity",entity));
        }
    };
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",DeYuManageActivity.this);
                    break;
                case 202:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DYNewsEntity> datas = tingsPersener.parseDYnewsData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterValues(datas);
                    }else{
                        stopListView();
                        deyumanage_lv.setPullLoadEnable(false);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",DeYuManageActivity.this);
                    break;
            }
        }
    };
    public void setAdapterValues(List<DYNewsEntity> datas){
        if(pageNum==1) {
            if (newsEntities.size() > 0) {
                newsEntities.clear();
            }
        }
        if(datas.size()>=10){
            deyumanage_lv.setPullLoadEnable(true);
        }else{
            deyumanage_lv.setPullLoadEnable(false);
        }
        for(DYNewsEntity entity:datas){
            newsEntities.add(entity);
        }
        deYuManageAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        thingsInternet.get_DYXWDatas("",String.valueOf(pageNum));

    }

    @Override
    public void onLoadMore() {
        pageNum++;
        thingsInternet.get_DYXWDatas("",String.valueOf(pageNum));

    }
    /**
     * 停止列表刷新
     */
    public void stopListView() {
        deyumanage_lv.stopRefresh();
        deyumanage_lv.stopLoadMore();
        deyumanage_lv.setRefreshTime("刚刚");
    }
}
