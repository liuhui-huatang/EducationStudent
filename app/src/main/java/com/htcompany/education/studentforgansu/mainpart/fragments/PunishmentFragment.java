package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluateInternet;
import com.htcompany.education.studentforgansu.internet.myevaluate.MyEvaluatePersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.PunishmentFragmentAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.PunishmentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 惩罚记录
 * Created by wrb on 2016/10/24.
 */
public class PunishmentFragment extends Fragment{

    private View punView;
    private ListView punishment_lv;
    private PunishmentFragmentAdapter punishmentFragmentAdapter;
    private List<PunishmentEntity> punishmentEntities;
    //网络请求类
    private MyEvaluateInternet evaluateInternet;
    private MyEvaluatePersener evaluatePersener;
    private WaitDialog waitDialog=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        punView = inflater.inflate(R.layout.punishment_fragment,null);
        initDatas();
        initViews();
        return punView;
    }
    public void initDatas(){
        punishmentEntities = new ArrayList<PunishmentEntity>();
        evaluateInternet = new MyEvaluateInternet(getActivity(),myHandler);
        evaluatePersener = new MyEvaluatePersener(getActivity());
        waitDialog = new WaitDialog(getActivity(),"");
        waitDialog.show();
        evaluateInternet.getStudentCFDatas();
    }
    public void initViews(){
        punishment_lv = (ListView)punView.findViewById(R.id.punishment_lv);
        punishmentFragmentAdapter = new PunishmentFragmentAdapter(getActivity(),punishmentEntities);
        punishment_lv.setAdapter(punishmentFragmentAdapter);
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
                    List<PunishmentEntity> datas = evaluatePersener.parseStudnetCFData((String)msg.obj);
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
    public void setAdapterValues(List<PunishmentEntity> datas){
        if(punishmentEntities.size()>0){
            punishmentEntities.clear();
        }
        for(PunishmentEntity entity:datas){
            punishmentEntities.add(entity);
        }
        punishmentFragmentAdapter.notifyDataSetChanged();
    }
}
