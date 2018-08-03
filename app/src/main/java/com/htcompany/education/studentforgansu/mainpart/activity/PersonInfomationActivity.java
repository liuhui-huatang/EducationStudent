package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.education.studentforgansu.internet.grzy.MySelfPersener;
import com.htcompany.education.studentforgansu.internet.grzy.MyselfInternet;
import com.htcompany.education.studentforgansu.mainpart.entity.MySelfMsgEntity;

/**
 * Created by wrb on 2016/10/20.
 * 个人基本信息
 */
public class PersonInfomationActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    //基本信息，入学信息
    private RelativeLayout person_basemsg_btn,person_insckollmsg_btn;
    private TextView person_basemsg_tv,person_insckollmsg_tv;
    private LinearLayout person_basemsg_ll,person_insckoolmsg_ll;
    private int viewShowFlag=1;
    private TextView personinformation_studentname_tv;
    private XCRoundAndOvalImageView userphoto;
   //==================================基本信息部分===========================================
    private TextView persioninfomation_name_tv,persioninfomation_sex_tv,persioninfomation_zy_tv,persioninfomation_xz_tv,persioninfomation_cc_tv,
           persioninfomation_nj_tv,persioninfomation_class_tv,persioninfomation_xsphone_tv,persioninfomation_xh_tv,persioninfomation_xjh_tv,
           persioninfomation_csrq_tv,persioninfomation_mz_tv,persioninfomation_sfswyxmz_tv,persioninfomation_myxuexing_tv,persioninfomation_sfzjlx_tv,
           persioninfomation_sfzjcode_tv,persioninfomation_xsjkzk_tv,persioninfomation_xszzmm_tv,persioninfomation_sfldrk_tv,persioninfomation_sfsqzn_tv;
    //==================================入学信息部分===========================================
    private TextView persioninfomation_zsfs_tv,persioninfomation_bmrq_tv,persioninfomation_rxrq_tv,persioninfomation_rxfs_tv,persioninfomation_zsdx_tv,
            persioninfomation_xxxs_tv,persioninfomation_jdfs_tv,persioninfomation_xslb_tv,persioninfomation_xsly_tv,persioninfomation_sfgfyl_tv,persioninfomation_lzhzxx_tv,
            persioninfomation_zsry_tv,persioninfomation_byxx_tv,persioninfomation_bysj_tv,persioninfomation_sflb_tv;
    //==============网络请求类===============
    private MyselfInternet myselfInternet;
    private MySelfPersener mySelfPersener;
    private WaitDialog waitDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personinfomation_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        myselfInternet = new MyselfInternet(this,myHandler);
        mySelfPersener = new MySelfPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        myselfInternet.getMySelfMsg();
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        person_basemsg_btn =(RelativeLayout)this.findViewById(R.id.person_basemsg_btn);
        person_insckollmsg_btn=(RelativeLayout)this.findViewById(R.id.person_insckollmsg_btn);

        person_basemsg_tv=(TextView)this.findViewById(R.id.person_basemsg_tv);
                person_insckollmsg_tv=(TextView)this.findViewById(R.id.person_insckollmsg_tv);

        person_basemsg_ll =(LinearLayout)this.findViewById(R.id.person_basemsg_ll);
        person_insckoolmsg_ll=(LinearLayout)this.findViewById(R.id.person_insckoolmsg_ll);
        personinformation_studentname_tv = (TextView)this.findViewById(R.id.personinformation_studentname_tv);
        userphoto=(XCRoundAndOvalImageView)this.findViewById(R.id.userphoto);
        //==================================基本信息部分===========================================
        persioninfomation_name_tv= (TextView)this.findViewById(R.id.persioninfomation_name_tv);
        persioninfomation_sex_tv= (TextView)this.findViewById(R.id.persioninfomation_sex_tv);
        persioninfomation_zy_tv= (TextView)this.findViewById(R.id.persioninfomation_zy_tv);
        persioninfomation_xz_tv= (TextView)this.findViewById(R.id.persioninfomation_xz_tv);
        persioninfomation_cc_tv= (TextView)this.findViewById(R.id.persioninfomation_cc_tv);
        persioninfomation_nj_tv= (TextView)this.findViewById(R.id.persioninfomation_nj_tv);
        persioninfomation_class_tv= (TextView)this.findViewById(R.id.persioninfomation_class_tv);
        persioninfomation_xsphone_tv= (TextView)this.findViewById(R.id.persioninfomation_xsphone_tv);
        persioninfomation_xh_tv= (TextView)this.findViewById(R.id.persioninfomation_xh_tv);
        persioninfomation_xjh_tv= (TextView)this.findViewById(R.id.persioninfomation_xjh_tv);
        persioninfomation_csrq_tv= (TextView)this.findViewById(R.id.persioninfomation_csrq_tv);
        persioninfomation_mz_tv= (TextView)this.findViewById(R.id.persioninfomation_mz_tv);
        persioninfomation_sfswyxmz_tv= (TextView)this.findViewById(R.id.persioninfomation_sfswyxmz_tv);
        persioninfomation_myxuexing_tv= (TextView)this.findViewById(R.id.persioninfomation_myxuexing_tv);
        persioninfomation_sfzjlx_tv= (TextView)this.findViewById(R.id.persioninfomation_sfzjlx_tv);
        persioninfomation_sfzjcode_tv= (TextView)this.findViewById(R.id.persioninfomation_sfzjcode_tv);
        persioninfomation_xsjkzk_tv= (TextView)this.findViewById(R.id.persioninfomation_xsjkzk_tv);
        persioninfomation_xszzmm_tv= (TextView)this.findViewById(R.id.persioninfomation_xszzmm_tv);
        persioninfomation_sfldrk_tv= (TextView)this.findViewById(R.id.persioninfomation_sfldrk_tv);
        persioninfomation_sfsqzn_tv= (TextView)this.findViewById(R.id.persioninfomation_sfsqzn_tv);
        //==================================入学信息部分===========================================
        persioninfomation_zsfs_tv= (TextView)this.findViewById(R.id.persioninfomation_zsfs_tv);
        persioninfomation_bmrq_tv= (TextView)this.findViewById(R.id.persioninfomation_bmrq_tv);
        persioninfomation_rxrq_tv= (TextView)this.findViewById(R.id.persioninfomation_rxrq_tv);
        persioninfomation_rxfs_tv= (TextView)this.findViewById(R.id.persioninfomation_rxfs_tv);
        persioninfomation_zsdx_tv= (TextView)this.findViewById(R.id.persioninfomation_zsdx_tv);
        persioninfomation_xxxs_tv= (TextView)this.findViewById(R.id.persioninfomation_xxxs_tv);
        persioninfomation_jdfs_tv= (TextView)this.findViewById(R.id.persioninfomation_jdfs_tv);
        persioninfomation_xslb_tv= (TextView)this.findViewById(R.id.persioninfomation_xslb_tv);
        persioninfomation_xsly_tv= (TextView)this.findViewById(R.id.persioninfomation_xsly_tv);
        persioninfomation_sfgfyl_tv= (TextView)this.findViewById(R.id.persioninfomation_sfgfyl_tv);
        persioninfomation_lzhzxx_tv= (TextView)this.findViewById(R.id.persioninfomation_lzhzxx_tv);
        persioninfomation_zsry_tv= (TextView)this.findViewById(R.id.persioninfomation_zsry_tv);
        persioninfomation_byxx_tv= (TextView)this.findViewById(R.id.persioninfomation_byxx_tv);
        persioninfomation_bysj_tv= (TextView)this.findViewById(R.id.persioninfomation_bysj_tv);
        persioninfomation_sflb_tv= (TextView)this.findViewById(R.id.persioninfomation_sflb_tv);
    }
    public void initViewValues(){
        title.setText("基本信息");
      initSelectViews(viewShowFlag);
    }
    public void initOnclicEvents(){
        person_basemsg_btn.setOnClickListener(this);
        person_insckollmsg_btn.setOnClickListener(this);
        reback_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.reback_btn:
               finish();
               break;
           case R.id.person_basemsg_btn:
               viewShowFlag=1;
               initSelectViews(viewShowFlag);
               break;
           case R.id.person_insckollmsg_btn:
               viewShowFlag=2;
               initSelectViews(viewShowFlag);
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
                    ToastUtil.showToast("连接超时", PersonInfomationActivity.this);
                break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    MySelfMsgEntity msgEntity=mySelfPersener.parseMyselfMsgData((String)msg.obj);
                    if(msgEntity!=null){
                        setViewVlaue(msgEntity);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常", PersonInfomationActivity.this);
                    break;
            }
        }
    };

    /**
     * 设置界面数据
     * @param msgEntity
     */
    public void setViewVlaue(MySelfMsgEntity msgEntity){
        Glide.with(this)
                .load(msgEntity.getPhoto())
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(userphoto);
        personinformation_studentname_tv.setText(msgEntity.getTruename());
        //==================================基本信息部分===========================================
        persioninfomation_name_tv.setText(msgEntity.getTruename());
        persioninfomation_sex_tv.setText(msgEntity.getGender());
        persioninfomation_zy_tv.setText(msgEntity.getMajor());
        persioninfomation_xz_tv.setText(msgEntity.getEducational());
        persioninfomation_cc_tv.setText(msgEntity.getUnity());
        persioninfomation_nj_tv.setText(msgEntity.getGrade());
        persioninfomation_class_tv.setText(msgEntity.getXzb());
        persioninfomation_xsphone_tv.setText(msgEntity.getPhone());
        persioninfomation_xh_tv.setText(msgEntity.getNumber());
        persioninfomation_xjh_tv.setText(msgEntity.getXji_num());
        persioninfomation_csrq_tv.setText(msgEntity.getBirthday());
        persioninfomation_mz_tv.setText(msgEntity.getNation());
        persioninfomation_sfswyxmz_tv.setText(msgEntity.getIs_swyxmz());
        persioninfomation_myxuexing_tv.setText(msgEntity.getBlood());
        persioninfomation_sfzjlx_tv.setText(msgEntity.getIdentity());
        persioninfomation_sfzjcode_tv.setText(msgEntity.getIdentitynum());
        persioninfomation_xsjkzk_tv.setText(msgEntity.getHealth());
        persioninfomation_xszzmm_tv.setText(msgEntity.getPolitical());
        persioninfomation_sfldrk_tv.setText(msgEntity.getIs_ldrk());
        persioninfomation_sfsqzn_tv.setText(msgEntity.getIs_sqzn());
        //==================================入学信息部分===========================================
        persioninfomation_zsfs_tv.setText(msgEntity.getZs_method());
        persioninfomation_bmrq_tv.setText(msgEntity.getEntry_date());
        persioninfomation_rxrq_tv.setText(msgEntity.getSchool_term());
        persioninfomation_rxfs_tv.setText(msgEntity.getRx_method());
        persioninfomation_zsdx_tv.setText(msgEntity.getZsdx());
        persioninfomation_xxxs_tv.setText(msgEntity.getStudy_xs());
        persioninfomation_jdfs_tv.setText(msgEntity.getJiudu_method());
        persioninfomation_xslb_tv.setText(msgEntity.getStu_type());
        persioninfomation_xsly_tv.setText(msgEntity.getStu_source());
        persioninfomation_sfgfyl_tv.setText(msgEntity.getIs_gfyl());
        persioninfomation_lzhzxx_tv.setText(msgEntity.getUnion_school());
        persioninfomation_zsry_tv.setText(msgEntity.getZs_people());
        persioninfomation_byxx_tv.setText(msgEntity.getGrad_school());
        persioninfomation_bysj_tv.setText(msgEntity.getGrad_time());
        persioninfomation_sflb_tv.setText(msgEntity.getCharge_type());
    }
    public void initNormalViews(){
        person_basemsg_ll.setVisibility(View.GONE);
        person_insckoolmsg_ll.setVisibility(View.GONE);
        person_basemsg_btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.personage_button_s));
        person_insckollmsg_btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.personage_button_s));
        person_basemsg_tv.setTextColor(getResources().getColor(R.color.pinfomation_off));
        person_insckollmsg_tv.setTextColor(getResources().getColor(R.color.pinfomation_off));
    }
    public void initSelectViews(int viewFlag){
        initNormalViews();
        switch (viewFlag){
            case 1:
                person_basemsg_ll.setVisibility(View.VISIBLE);
                person_basemsg_btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.personage_button_n));
                person_basemsg_tv.setTextColor(getResources().getColor(R.color.pinfomation_on));
                break;
            case 2:
                person_insckoolmsg_ll.setVisibility(View.VISIBLE);
                person_insckollmsg_btn.setBackgroundDrawable(getResources().getDrawable(R.mipmap.personage_button_n));
                person_insckollmsg_tv.setTextColor(getResources().getColor(R.color.pinfomation_on));
                break;
        }
    }
}
