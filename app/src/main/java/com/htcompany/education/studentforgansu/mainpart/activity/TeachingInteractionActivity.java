package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.swipemenulistview.SwipeMenu;
import com.htcompany.education.studentforgansu.commonpart.views.swipemenulistview.SwipeMenuCreator;
import com.htcompany.education.studentforgansu.commonpart.views.swipemenulistview.SwipeMenuItem;
import com.htcompany.education.studentforgansu.commonpart.views.swipemenulistview.SwipeMenuListView;
import com.htcompany.education.studentforgansu.commonpart.views.xlistview.XListView;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.TeachingInteractionAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.DYNewsEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TeachingInteractionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 教学互动
 * Created by wrb on 2016/11/22.
 */
public class TeachingInteractionActivity extends BaseActivity implements View.OnClickListener ,XListView.IXListViewListener{
    private TextView title,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    private XListView teachinginteraction_lv;
    private TeachingInteractionAdapter interactionAdapter;
    private Intent intent=null;
    private int pageNum=1;
    //==========网络请求类===========
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private WaitDialog waitDialog=null;
    private List<TeachingInteractionEntity> interactionEntities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachinginteraction_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        interactionEntities = new ArrayList<TeachingInteractionEntity>();
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        waitDialog= new WaitDialog(this,"");
        waitDialog.show();
        thingsInternet.getJSHD_LISTDatas(String.valueOf(pageNum));
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        right_btn_tv = (TextView)this.findViewById(R.id.right_btn_tv);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        right_text_btn=(RelativeLayout)this.findViewById(R.id.right_text_btn);
        teachinginteraction_lv = (XListView) this.findViewById(R.id.teachinginteraction_lv);
        teachinginteraction_lv.setPullRefreshEnable(true);
        teachinginteraction_lv.setPullLoadEnable(false);
        teachinginteraction_lv.setXListViewListener(this);
        interactionAdapter = new TeachingInteractionAdapter(this,interactionEntities);
        teachinginteraction_lv.setAdapter(interactionAdapter);
    }
    public void initViewValues(){
        title.setText("教学互动");
        right_text_btn.setVisibility(View.VISIBLE);
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        teachinginteraction_lv.setOnItemClickListener(interationClicer);
    }
   public AdapterView.OnItemClickListener interationClicer = new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           TeachingInteractionEntity entity = (TeachingInteractionEntity)interactionAdapter.getItem(position-1);
           intent = new Intent(TeachingInteractionActivity.this,TeachingInteractionDetailsActivity.class);
           intent.putExtra("InteractionEntity",entity);
           startActivityForResult(intent,102);
       }
   };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.right_text_btn:
                //添加问题
                intent = new Intent(TeachingInteractionActivity.this,TeachingInteractionAddActivity.class);
                startActivityForResult(intent,101);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            switch (resultCode) {
                case 101:
                    pageNum = 1;
                    thingsInternet.getJSHD_LISTDatas(String.valueOf(pageNum));
                    break;
                case 102:
                    pageNum = 1;
                    thingsInternet.getJSHD_LISTDatas(String.valueOf(pageNum));
                    break;
            }
        }
    }

    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("连接超时",TeachingInteractionActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TeachingInteractionEntity> datas = tingsPersener.parseStudnetJXHDData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterValues(datas);
                    }else {
                        stopListView();
                        teachinginteraction_lv.setPullLoadEnable(false);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",TeachingInteractionActivity.this);
                    break;
            }
        }
    };
    public void setAdapterValues(List<TeachingInteractionEntity> datas){
        if(pageNum==1) {
            if (interactionEntities.size() > 0) {
                interactionEntities.clear();
            }
        }
        if(datas.size()>=10){
            teachinginteraction_lv.setPullLoadEnable(true);
        }else{
            teachinginteraction_lv.setPullLoadEnable(false);
        }
        for(TeachingInteractionEntity entity:datas){
            interactionEntities.add(entity);
        }
        interactionAdapter.notifyDataSetChanged();
        stopListView();
    }

    @Override
    public void onRefresh() {
        pageNum=1;
        thingsInternet.getJSHD_LISTDatas(String.valueOf(pageNum));

    }

    @Override
    public void onLoadMore() {
        pageNum++;
        thingsInternet.getJSHD_LISTDatas(String.valueOf(pageNum));

    }
    /**
     * 停止列表刷新
     */
    public void stopListView() {
        teachinginteraction_lv.stopRefresh();
        teachinginteraction_lv.stopLoadMore();
        teachinginteraction_lv.setRefreshTime("刚刚");
    }
}
