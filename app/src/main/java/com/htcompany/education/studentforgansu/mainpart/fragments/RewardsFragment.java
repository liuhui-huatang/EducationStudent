package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluateInternet;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluatePersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.RewardsFragmentAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.RewardsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 奖励记录
 * Created by wrb on 2016/10/24.
 */
public class RewardsFragment extends Fragment{
    private View rewardView;
    private RecyclerView rewards_recy;
    private RewardsFragmentAdapter rewardsFragmentAdapter;
    private List<RewardsEntity> rewardsEntities;
    //网络请求类
    private MyEvaluateInternet evaluateInternet;
    private MyEvaluatePersener evaluatePersener;
    private WaitDialog waitDialog=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rewardView = inflater.inflate(R.layout.rewards_fragment,null);
        initDatas();
        initViews();
        return rewardView;
    }
    public void initDatas(){
        rewardsEntities = new ArrayList<RewardsEntity>();
        evaluateInternet = new MyEvaluateInternet(getActivity(),myHandler);
        evaluatePersener = new MyEvaluatePersener(getActivity());
        waitDialog = new WaitDialog(getActivity(),"");
        waitDialog.show();
        evaluateInternet.getStudentJLDatas();
    }
    public void initViews(){
        rewards_recy = (RecyclerView)rewardView.findViewById(R.id.rewards_recy);
        rewardsFragmentAdapter = new RewardsFragmentAdapter(getActivity(),rewardsEntities);
        rewards_recy.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置Item增加、移除动画
        rewards_recy.setItemAnimator(new DefaultItemAnimator());
        rewards_recy.setAdapter(rewardsFragmentAdapter);
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
                    ToastUtil.showToast("服务器连接失败",getActivity());
                    break;
                case 200:
                    //网络请求成功，解析数据
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<RewardsEntity> datas = evaluatePersener.parseStudnetJLData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterValues(datas);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",getActivity());
                    break;
            }
        }
    };
    public void setAdapterValues(List<RewardsEntity> datas){
        if(rewardsEntities.size()>0){
            rewardsEntities.clear();
        }
        for(RewardsEntity entity:datas){
            rewardsEntities.add(entity);
        }
        rewardsFragmentAdapter.notifyDataSetChanged();
    }
}
