package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.grzy.MySelfPersener;
import com.htcompany.education.studentforgansu.internet.grzy.MyselfInternet;
import com.htcompany.education.studentforgansu.mainpart.adapter.MyExamViewpagerAdapter;
import com.htcompany.education.studentforgansu.mainpart.adapter.MyExamVpRecyleAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.MyExamClassEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.MyExamEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的成绩
 * Created by wrb on 2016/10/20.
 * 1.学期传空时返回当前学期成绩
 * 2.学期不为空时返回现在所传成绩数据
 */
public class MyExamFragment extends Fragment implements ViewPager.OnPageChangeListener{
    private View examFramentView;
    //=======================viewpager============================
    private TextView myexma_one_tv,myexma_two_tv,myexma_three_tv;
    private ViewPager myexam_vp;
    private List<View> examViews;
    private LayoutInflater inflater;
    private MyExamViewpagerAdapter examViewpagerAdapter;
    private View vpItemView;//viewpager子项view
    private GridView myexamp_vp_recyle;
    private TextView myexamvp_notice_tv,myexamvp_pm_tv,myexamvp_source_tv;
    private MyExamVpRecyleAdapter myExamVpRecyleAdapter;
    private List<MyExamClassEntity> myExamEntities;

    private int viewIndex=0;
    private String termkey="";
    //==============网络请求类===============
    private MyselfInternet myselfInternet;
    private MySelfPersener mySelfPersener;
    private WaitDialog waitDialog;
    private MyExamEntity examEntity=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        this.inflater = inflater;
        examFramentView = inflater.inflate(R.layout.myexamresult_fragment,null);
        initDatas();
        initViews();
        initViewValues();
        return examFramentView;
    }
    public void initDatas(){
        myExamEntities = new ArrayList<MyExamClassEntity>();
        examViews= new ArrayList<View>();
        myselfInternet = new MyselfInternet(getActivity(),myHandler);
        mySelfPersener = new MySelfPersener(getActivity());
        waitDialog = new WaitDialog(getActivity(),"");
        waitDialog.show();
        myselfInternet.getMySelfExamMsg(termkey);
    }
    public void initViews(){
        myexma_one_tv=(TextView)examFramentView.findViewById(R.id.myexma_one_tv);
        myexma_two_tv=(TextView)examFramentView.findViewById(R.id.myexma_two_tv);
        myexma_three_tv=(TextView)examFramentView.findViewById(R.id.myexma_three_tv);
        myexam_vp=(ViewPager)examFramentView.findViewById(R.id.myexam_vp);
        myexam_vp.setOnPageChangeListener(this);
    }
    //获取数据成功后，执行此方法，并设置界面数据
    public void initViewValues(){
        if(isAdded()) {
            initViewPagerViews();
        }
    }
    //初始化标题数据
    public void initTiteValue(){
        if (MyApp.termEntities.size() > 0) {
            if (viewIndex == 0) {
                myexma_one_tv.setText("");
                myexma_two_tv.setText(MyApp.termEntities.get(viewIndex).getName());
                // arg0+1
                myexma_three_tv.setText(MyApp.termEntities.get(viewIndex + 1).getName());
            } else if (viewIndex == (MyApp.termEntities.size() - 1)) {
                // viewlist.size() - 1
                myexma_one_tv.setText(MyApp.termEntities.get(MyApp.termEntities.size() - 2).getName());
                // viewlist.size();
                myexma_two_tv.setText(MyApp.termEntities.get(MyApp.termEntities.size() - 1).getName());
                myexma_three_tv.setText("");
            } else {
                // arg0-1
                myexma_one_tv.setText(MyApp.termEntities.get(viewIndex - 1).getName());
                myexma_two_tv.setText(MyApp.termEntities.get(viewIndex).getName());
                if (MyApp.termEntities.size() > viewIndex) {
                    myexma_three_tv.setText(MyApp.termEntities.get(viewIndex + 1).getName());
                } else {
                    myexma_three_tv.setText("");
                }
            }
        } else {
            myexma_one_tv.setText("");
            myexma_two_tv.setText(MyApp.termEntities.get(viewIndex).getName());
            myexma_three_tv.setText("");
        }
    }
    public void initViewPagerViews(){
        if(MyApp.termEntities.size()>0){
            for(int i=0;i<MyApp.termEntities.size();i++){
                View view = inflater.inflate(R.layout.myexam_vp_view,null);
                examViews.add(view);
            }
            examViewpagerAdapter = new MyExamViewpagerAdapter(examViews);
            myexam_vp.setAdapter(examViewpagerAdapter);
//            myexam_vp.setCurrentItem(viewIndex);
            initTiteValue();
//            initVpItemViewDatas(viewIndex);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        viewIndex=position;
        if (position == 0) {
            myexma_one_tv.setText("");
            myexma_two_tv.setText(MyApp.termEntities.get(position).getName());
            // arg0+1
            myexma_three_tv.setText(MyApp.termEntities.get(position + 1).getName());
        } else if (position == (MyApp.termEntities.size() - 1)) {
            // viewlist.size() - 1
            myexma_one_tv.setText(MyApp.termEntities.get(MyApp.termEntities.size() - 2).getName());
            // viewlist.size();
            myexma_two_tv.setText(MyApp.termEntities.get(MyApp.termEntities.size() - 1).getName());
            myexma_three_tv.setText("");
        } else {
            // arg0-1
            myexma_one_tv.setText(MyApp.termEntities.get(position - 1).getName());
            myexma_two_tv.setText(MyApp.termEntities.get(position).getName());
            if (MyApp.termEntities.size() > position) {
                myexma_three_tv.setText(MyApp.termEntities.get(position + 1).getName());
            } else {
                myexma_three_tv.setText("");
            }
        }
//        initVpItemViewDatas(position);
        //获取学期请求网络接口
        waitDialog.show();
        termkey=MyApp.termEntities.get(position).getOnecode();
        myselfInternet.getMySelfExamMsg(termkey);

    }
    public void initVpItemViewDatas(int position){
        vpItemView = examViews.get(position);
        myexamvp_notice_tv=(TextView) vpItemView.findViewById(R.id.myexamvp_notice_tv);
        myexamvp_notice_tv.setVisibility(View.GONE);
        myexamvp_pm_tv=(TextView) vpItemView.findViewById(R.id.myexamvp_pm_tv);
        myexamvp_source_tv=(TextView) vpItemView.findViewById(R.id.myexamvp_source_tv);
        myexamp_vp_recyle = (GridView) vpItemView.findViewById(R.id.myexamp_vp_recyle);
        //设置数据
        myExamVpRecyleAdapter = new MyExamVpRecyleAdapter(getActivity(),myExamEntities);
        myexamp_vp_recyle.setAdapter(myExamVpRecyleAdapter);
    }
    //滑动刷新数据
    public void setAdapterDatas(MyExamEntity examEntity){
        if(examEntity.getChengji()!=null&&examEntity.getChengji().size()>0) {
            myexamvp_notice_tv.setVisibility(View.GONE);
            if(!"".equals(examEntity.getPaiming())&&examEntity.getPaiming()!=null){
                myexamvp_pm_tv.setText("名次:"+examEntity.getPaiming()+"名");
            }else{
                myexamvp_pm_tv.setText("暂无排名");
            }
            if(!"".equals(examEntity.getTotal())&&examEntity.getTotal()!=null){
                myexamvp_source_tv.setText("总分:"+examEntity.getTotal()+"分");
            }else {
                myexamvp_source_tv.setText("暂无总分");
            }

            if (myExamEntities.size() > 0) {
                myExamEntities.clear();
            }
            for (MyExamClassEntity entity : examEntity.getChengji()) {
                myExamEntities.add(entity);
            }
            myExamVpRecyleAdapter.notifyDataSetChanged();
        }else{
            if (myExamEntities.size() > 0) {
                myExamEntities.clear();
            }
            myExamVpRecyleAdapter.notifyDataSetChanged();
            myexamvp_source_tv.setText("暂无总分");
            myexamvp_pm_tv.setText("暂无排名");
            myexamvp_notice_tv.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

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
                    ToastUtil.showToast("连接超时", getActivity());
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    examEntity = mySelfPersener.parseMyExamMsgData((String)msg.obj);
                    if(examEntity!=null){
                        //1.判断当前学期位置
                        if(!"".equals(examEntity.getCurrentTerm())){
                            for(int i=0;i<MyApp.termEntities.size();i++){
                                if(examEntity.getCurrentTerm().equals(MyApp.termEntities.get(i).getOnecode())){
                                    viewIndex = i;
                                    break;
                                }
                            }
                                myexam_vp.setCurrentItem(viewIndex);
                                initVpItemViewDatas(viewIndex);
                                setAdapterDatas(examEntity);
                        }else{
                            viewIndex=0;
                            initViewValues();
                        }
                    }else{
                        viewIndex=0;
                        initViewValues();
                    }

                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常", getActivity());
                    break;
            }
        }
    };


}
