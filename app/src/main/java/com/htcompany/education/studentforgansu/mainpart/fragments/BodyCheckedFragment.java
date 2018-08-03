package com.htcompany.education.studentforgansu.mainpart.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.grzy.MySelfPersener;
import com.htcompany.education.studentforgansu.internet.grzy.MyselfInternet;
import com.htcompany.education.studentforgansu.mainpart.entity.BodyCheckEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;
import com.htcompany.education.studentforgansu.mainpart.popwindows.SelectorTermsPopwindow;

/**
 * 体检信息
 * Created by wrb on 2016/10/21.
 */
public class BodyCheckedFragment extends Fragment implements View.OnClickListener,PopupWindow.OnDismissListener{
    private View ckViews;
    private SelectorTermsPopwindow termsPopwindow;
    private TextView bodychecked_selectterm_tv;
    private TextView bodycheck_yiyuan_tv,bodycheck_sheng_tv,bodycheck_tiz_tv,bodycheck_shil_tv,bodycheck_wgk_tv;
    //==============网络请求类===============
    private MyselfInternet myselfInternet;
    private MySelfPersener mySelfPersener;
    private WaitDialog waitDialog;
    private String term="";
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        ckViews = inflater.inflate(R.layout.bodychecked_fragment,null);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
        return ckViews;
    }
    public void initDatas(){
        myselfInternet = new MyselfInternet(getActivity(),myHandler);
        mySelfPersener = new MySelfPersener(getActivity());
        waitDialog = new WaitDialog(getActivity(),"");
        waitDialog.show();
        myselfInternet.getMySelfBodyCheckMsg(term);
        termsPopwindow= new SelectorTermsPopwindow(getActivity(),1,myHandler);
        termsPopwindow.setOnDismissListener(this);
    }
    public void initViews(){
        bodychecked_selectterm_tv=(TextView)ckViews.findViewById(R.id.bodychecked_selectterm_tv);
        bodycheck_yiyuan_tv=(TextView)ckViews.findViewById(R.id.bodycheck_yiyuan_tv);
        bodycheck_sheng_tv=(TextView)ckViews.findViewById(R.id.bodycheck_sheng_tv);
        bodycheck_tiz_tv=(TextView)ckViews.findViewById(R.id.bodycheck_tiz_tv);
        bodycheck_shil_tv=(TextView)ckViews.findViewById(R.id.bodycheck_shil_tv);
        bodycheck_wgk_tv=(TextView)ckViews.findViewById(R.id.bodycheck_wgk_tv);
    }
    public void initViewValues(){

    }
    public void initOnclicEvents(){
        bodychecked_selectterm_tv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.bodychecked_selectterm_tv:
               termsPopwindow.showPopupWindow(bodychecked_selectterm_tv);
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
                    BodyCheckEntity entity=mySelfPersener.parseMyselfBodyCheckData((String)msg.obj);
                    if(entity!=null){
                        setViewVlaue(entity);
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
                    bodychecked_selectterm_tv.setText(termEntity.getName());
                    term=termEntity.getOnecode();
                    waitDialog.show();
                    myselfInternet.getMySelfBodyCheckMsg(term);
                    break;
            }
        }
    };
    @Override
    public void onDismiss() {
        //popwindow管理处理时间
    }
    public void setViewVlaue(BodyCheckEntity entity){
        bodychecked_selectterm_tv.setText(entity.getSchool_date());
        bodycheck_yiyuan_tv.setText(entity.getHospital());
        bodycheck_sheng_tv.setText(entity.getTall()+"cm");
        bodycheck_tiz_tv.setText(entity.getWeight()+"kg");
        bodycheck_shil_tv.setText("左眼:"+entity.getLeft_eye()+"-右眼:"+entity.getRight_eye());
        bodycheck_wgk_tv.setText(entity.getWuguan());
    }
}
