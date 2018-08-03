package com.htcompany.education.studentforgansu;

import android.os.Bundle;

import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.PullMoreListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrb on 2016/12/22.
 */
public class DemoList extends BaseActivity implements PullMoreListView.MyPullUpListViewCallBack{
    private PullMoreListView punishmenssst_lv;
    private DemoListAdapter adapter;
    private List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demolist);
        intdatas();
        initViews();

    }
    public void intdatas(){
        datas = new ArrayList<String>();
        datas.add("1");
        datas.add("2");
        datas.add("3");
        datas.add("4");
        datas.add("1");
        datas.add("2");
        datas.add("3");
//        datas.add("4");
//        datas.add("1");
//        datas.add("2");
//        datas.add("3");
//        datas.add("4");
//        datas.add("1");
//        datas.add("2");
//        datas.add("3");
//        datas.add("4");
    }
     public void initViews(){
         punishmenssst_lv = (PullMoreListView)this.findViewById(R.id.punishmenssst_lv);
         adapter = new DemoListAdapter(this,datas);
         punishmenssst_lv.setAdapter(adapter);
         punishmenssst_lv.setMyPullUpListViewCallBack(this);
     }
    @Override
    public void scrollBottomState() {
        ToastUtil.showToast("项目数："+datas.size(),DemoList.this);
        if(datas.size()>30){
            punishmenssst_lv.setPullMoreHiden();
        }else{
            datas.add("1");
            datas.add("2");
            datas.add("3");
            adapter.notifyDataSetChanged();
            punishmenssst_lv.setPullMoreVisible();
        }

    }
}
