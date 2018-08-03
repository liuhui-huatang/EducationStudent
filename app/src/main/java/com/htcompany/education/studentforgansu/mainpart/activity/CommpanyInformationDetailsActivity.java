package com.htcompany.education.studentforgansu.mainpart.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.companyinternet.CompanyInternet;
import com.htcompany.education.studentforgansu.internet.companyinternet.CompanyPersener;
import com.htcompany.education.studentforgansu.mainpart.entity.YBMJobEntity;

/**
 * 企业详情
 * Created by wrb on 2016/11/18.
 */
public class CommpanyInformationDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView title;
    private RelativeLayout reback_btn;
    private TextView cdetail_name_tv,cdetail_money_tv,cdetail_cname_tv,
            cdetail_city_tv,cdetail_xl_tv,cdetail_bmrs_tv,cdetail_time_tv,
            cdetail_adress_tv,cdetail_gwms_tv;
    private Button cdetail_bm_btn;
    private YBMJobEntity entity;
    private String flag="";//判断是否隐藏报名按钮
    private CompanyInternet companyInternet;
    private CompanyPersener companyPersener;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commpanyinformationdetails_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        entity = (YBMJobEntity) getIntent().getSerializableExtra("entity");
        flag = getIntent().getStringExtra("flag");
        companyInternet = new CompanyInternet(this,myHandler);
        companyPersener = new CompanyPersener(this);
        waitDialog = new WaitDialog(this,"");
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        cdetail_name_tv= (TextView)this.findViewById(R.id.cdetail_name_tv);
        cdetail_money_tv= (TextView)this.findViewById(R.id.cdetail_money_tv);
        cdetail_cname_tv= (TextView)this.findViewById(R.id.cdetail_cname_tv);
        cdetail_city_tv= (TextView)this.findViewById(R.id.cdetail_city_tv);
        cdetail_xl_tv= (TextView)this.findViewById(R.id.cdetail_xl_tv);
        cdetail_bmrs_tv= (TextView)this.findViewById(R.id.cdetail_bmrs_tv);
        cdetail_time_tv= (TextView)this.findViewById(R.id.cdetail_time_tv);
        cdetail_adress_tv= (TextView)this.findViewById(R.id.cdetail_adress_tv);
        cdetail_gwms_tv= (TextView)this.findViewById(R.id.cdetail_gwms_tv);
        cdetail_bm_btn = (Button)this.findViewById(R.id.cdetail_bm_btn);
    }
    public void initViewValues(){
        title.setText("职位详情");
        if("no".equals(flag)){
            cdetail_bm_btn.setVisibility(View.GONE);

        }else if("yes".equals(flag)){
            if("0".equals(entity.getBm())){
                cdetail_bm_btn.setText("报名");
                cdetail_bm_btn.setEnabled(true);
            }else{
                cdetail_bm_btn.setText("已报名");
                cdetail_bm_btn.setEnabled(false);
            }

        }
        if(entity!=null){
            cdetail_name_tv.setText(entity.getEp_name());
            cdetail_money_tv.setText(entity.getEp_monthly_pay());
            cdetail_cname_tv.setText(entity.getEl_name());
            cdetail_city_tv.setText(entity.getEp_address());
            cdetail_xl_tv.setText(entity.getEp_education());
            cdetail_bmrs_tv.setText(entity.getEp_num()+"人");
            cdetail_time_tv.setText(entity.getEp_start_time()+"至"+entity.getEp_end_time());
            cdetail_adress_tv.setText(entity.getEl_zone());
            cdetail_gwms_tv.setText(entity.getEp_content());
        }
    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        cdetail_bm_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                this.finish();
                break;
            case R.id.cdetail_bm_btn:
                waitDialog.show();
                companyInternet.uploadCompany_BMGWMsg(entity.getEp_id());
                break;
        }
    }
    public Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    if(companyPersener.parserGWBM((String)msg.obj)){
                        ToastUtil.showToast("报名成功",CommpanyInformationDetailsActivity.this);
                        finish();
                    }else{
                        ToastUtil.showToast("请重新报名",CommpanyInformationDetailsActivity.this);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",CommpanyInformationDetailsActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",CommpanyInformationDetailsActivity.this);
                    break;
            }
        }
    };
}
