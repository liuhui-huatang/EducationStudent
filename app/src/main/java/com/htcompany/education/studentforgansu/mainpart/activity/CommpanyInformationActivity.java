package com.htcompany.education.studentforgansu.mainpart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.xlistview.XListView;
import com.htcompany.education.studentforgansu.internet.companyinternet.CompanyInternet;
import com.htcompany.education.studentforgansu.internet.companyinternet.CompanyPersener;
import com.htcompany.education.studentforgansu.mainpart.adapter.CommpanyInformationAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.BookEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.YBMJobEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业信息
 * Created by wrb on 2016/11/17.
 */
public class CommpanyInformationActivity extends BaseActivity implements View.OnClickListener,XListView.IXListViewListener{
    private RelativeLayout reback_btn;
    private XListView commpanyinformation_lv;
    private int pageNum=1;
    private CommpanyInformationAdapter informationAdapter;
    private CompanyInternet companyInternet;
    private CompanyPersener companyPersener;
    private WaitDialog waitDialog=null;
    private List<YBMJobEntity> ybmJobEntities;
    private String searchername="";
    private EditText commpanys_name_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commpanyinformation_activity);
        initDatas();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    public void initDatas(){
        ybmJobEntities = new ArrayList<YBMJobEntity>();
        companyInternet = new CompanyInternet(this,myHandler);
        companyPersener = new CompanyPersener(this);
        waitDialog = new WaitDialog(this,"");
        waitDialog.show();
        companyInternet.getCompany_ZPGWMsg(searchername,String.valueOf(pageNum));
    }
    public void initViews(){
        reback_btn = (RelativeLayout)this.findViewById(R.id.company_reback_btn);
        commpanyinformation_lv=(XListView) this.findViewById(R.id.commpanyinformation_lv);
        commpanyinformation_lv.setPullRefreshEnable(true);
        commpanyinformation_lv.setPullLoadEnable(false);
        commpanyinformation_lv.setXListViewListener(this);
        informationAdapter = new CommpanyInformationAdapter(this,ybmJobEntities);
        commpanyinformation_lv.setAdapter(informationAdapter);
        commpanyinformation_lv.setOnItemClickListener(commpanyClicer);
        commpanys_name_edt = (EditText)this.findViewById(R.id.commpanys_name_edt);
    }
    public void initViewValues(){

    }
    public void initOnclicEvents(){
        reback_btn.setOnClickListener(this);
        commpanys_name_edt.addTextChangedListener(serchareclicer);
    }
  public AdapterView.OnItemClickListener commpanyClicer = new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          YBMJobEntity jobEntity = (YBMJobEntity) informationAdapter.getItem(position-1);
          Intent intent = new Intent(CommpanyInformationActivity.this,CommpanyInformationDetailsActivity.class);
          intent.putExtra("entity",jobEntity);
          intent.putExtra("flag","yes");
          startActivity(intent);
      }
  };

    public TextWatcher serchareclicer = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchername = commpanys_name_edt.getText().toString();
           if(!"".equals(commpanys_name_edt.getText().toString())){
               companyInternet.getCompany_ZPGWMsg(searchername,String.valueOf(pageNum));
           }else{
               companyInternet.getCompany_ZPGWMsg(searchername,String.valueOf(pageNum));
           }
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.company_reback_btn:
                this.finish();
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
                    List<YBMJobEntity> datas = companyPersener.parseCompany_YBMJobData((String)msg.obj);
                    if(datas!=null&&datas.size()>0){
                        setAdapterDatas(datas);
                    }else{
                        stopListView();
                        commpanyinformation_lv.setPullLoadEnable(false);
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",CommpanyInformationActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",CommpanyInformationActivity.this);
                    break;
            }
        }
    };
    public void setAdapterDatas(List<YBMJobEntity> datas){
        if(pageNum==1) {
            if (ybmJobEntities.size() > 0) {
                ybmJobEntities.clear();
            }
        }
        if(datas.size()>=20){
            commpanyinformation_lv.setPullLoadEnable(true);
        }else{
            commpanyinformation_lv.setPullLoadEnable(false);
        }
        for(YBMJobEntity entity:datas){
            ybmJobEntities.add(entity);
        }
        informationAdapter.notifyDataSetChanged();
        stopListView();
    }
    @Override
    public void onRefresh() {
        pageNum=1;
        companyInternet.getCompany_ZPGWMsg(searchername,String.valueOf(pageNum));
    }

    @Override
    public void onLoadMore() {
        pageNum++;
        companyInternet.getCompany_ZPGWMsg(searchername,String.valueOf(pageNum));

    }
    /**
     * 停止列表刷新
     */
    public void stopListView() {
        commpanyinformation_lv.stopRefresh();
        commpanyinformation_lv.stopLoadMore();
        commpanyinformation_lv.setRefreshTime("刚刚");
    }
}
