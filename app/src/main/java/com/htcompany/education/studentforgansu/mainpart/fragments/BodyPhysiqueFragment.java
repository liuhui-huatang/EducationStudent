package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.grzy.MySelfPersener;
import com.htcompany.education.studentforgansu.internet.grzy.MyselfInternet;
import com.htcompany.education.studentforgansu.mainpart.entity.BodyPhysiqueEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;
import com.htcompany.education.studentforgansu.mainpart.popwindows.SelectorTermsPopwindow;

/**
 * 体质信息fragment
 * Created by wrb on 2016/10/21.
 */
public class BodyPhysiqueFragment extends Fragment implements View.OnClickListener{
    private View tzViews;
    private SelectorTermsPopwindow termsPopwindow;
    private TextView bodyphysique_selectterm_tv;
    private TextView bodytz_tjcs_tv,bodytz_yqm_tv,bodytz_fhl_tv,bodytz_wsm_tv,bodytz_ldty_tv,bodytz_wl_tv,bodytz_pjdj_tv;
    //==============网络请求类===============
    private MyselfInternet myselfInternet;
    private MySelfPersener mySelfPersener;
    private WaitDialog waitDialog;
    private String term="";
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        tzViews = inflater.inflate(R.layout.bodyphysique_fragment,null);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
        return tzViews;
    }
    public void initDatas(){
        myselfInternet = new MyselfInternet(getActivity(),myHandler);
        mySelfPersener = new MySelfPersener(getActivity());
        waitDialog = new WaitDialog(getActivity(),"");
        waitDialog.show();
        myselfInternet.getMySelfBodyTZMsg(term);
        termsPopwindow= new SelectorTermsPopwindow(getActivity(),1,myHandler);
    }
    public void initViews(){
        bodyphysique_selectterm_tv=(TextView)tzViews.findViewById(R.id.bodyphysique_selectterm_tv);
        bodytz_tjcs_tv=(TextView)tzViews.findViewById(R.id.bodytz_tjcs_tv);
        bodytz_yqm_tv=(TextView)tzViews.findViewById(R.id.bodytz_yqm_tv);
        bodytz_fhl_tv=(TextView)tzViews.findViewById(R.id.bodytz_fhl_tv);
        bodytz_wsm_tv=(TextView)tzViews.findViewById(R.id.bodytz_wsm_tv);
        bodytz_ldty_tv=(TextView)tzViews.findViewById(R.id.bodytz_ldty_tv);
        bodytz_wl_tv=(TextView)tzViews.findViewById(R.id.bodytz_wl_tv);
        bodytz_pjdj_tv=(TextView)tzViews.findViewById(R.id.bodytz_pjdj_tv);
    }
    public void initViewValues(){

    }
    public void initOnclicEvents(){
        bodyphysique_selectterm_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bodyphysique_selectterm_tv:
                termsPopwindow.showPopupWindow(bodyphysique_selectterm_tv);
                break;
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
                    ToastUtil.showToast("连接超时", getActivity());
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    BodyPhysiqueEntity msgEntity=mySelfPersener.parseMyselfBodyTZData((String)msg.obj);
                    if(msgEntity!=null){
                        setViewVlaue(msgEntity);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常", getActivity());
                    break;
                case 1000:
                    termsPopwindow.dismiss();
                    Bundle bundle = msg.getData();
                    TermEntity termEntity = (TermEntity) bundle.getSerializable("termentity");
                    bodyphysique_selectterm_tv.setText(termEntity.getName());
                    term=termEntity.getOnecode();
                    waitDialog.show();
                    myselfInternet.getMySelfBodyTZMsg(term);
                    break;
            }
        }
    };
    public void setViewVlaue(BodyPhysiqueEntity entity){
        bodyphysique_selectterm_tv.setText(entity.getSchool_date());
        bodytz_tjcs_tv.setText(entity.getTaijie_test());
        bodytz_yqm_tv.setText(entity.getYiqian()+"s");
        bodytz_fhl_tv.setText(entity.getFeihuoliang());
        bodytz_wsm_tv.setText(entity.getWushi()+"s");
        bodytz_ldty_tv.setText(entity.getTiaoyuan()+"m");
        bodytz_wl_tv.setText(entity.getWoli());
        bodytz_pjdj_tv.setText(entity.getLevel());
    }
}
