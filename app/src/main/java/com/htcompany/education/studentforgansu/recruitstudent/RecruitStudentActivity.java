package com.htcompany.education.studentforgansu.recruitstudent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.views.MyGdListView;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.internet.zhaosheng.ZhaoShengInternet;
import com.htcompany.education.studentforgansu.internet.zhaosheng.ZhaoShengPersoner;
import com.htcompany.education.studentforgansu.mainpart.activity.DeYuManageActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.MainNewsDetailsActivity;
import com.htcompany.education.studentforgansu.mainpart.entity.DYNewsEntity;
import com.htcompany.education.studentforgansu.recruitstudent.activity.RecruitStudentBMActivity;
import com.htcompany.education.studentforgansu.recruitstudent.activity.ZyJsDetailsActivity;
import com.htcompany.education.studentforgansu.recruitstudent.activity.ZyJsListActivity;
import com.htcompany.education.studentforgansu.recruitstudent.adapter.RecruitStudentZYAdapter;
import com.htcompany.education.studentforgansu.recruitstudent.dialog.PleseYQMDailog;
import com.htcompany.education.studentforgansu.recruitstudent.entity.RecruitStudentZYEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 招生信息介绍界面
 * Created by wrb on 2017/1/16.
 */
public class RecruitStudentActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,recruitstudent_bm_tv,right_btn_tv;
    private RelativeLayout reback_btn,right_text_btn;
    Intent intent=null;
    //轮播图
    private ConvenientBanner recruits_banner_cb;
    //================德育新闻======================
    private List<DYNewsEntity> bannarList;
    private ProgressBar recruits_dynewswait_pb;//德育新闻进度框
    private TextView recruits_dynewswait_tv;//德育新闻提示
    private RelativeLayout recruits_dyxw_rel;//德育新闻部分
    private LinearLayout recruits_dynews_ll;
    private ImageView recruits_dynewsone_img,recruits_dynewstwo_img,recruits_dynewsthree_img;//德育新闻部分
    private TextView recruits_dynewsone_tv,recruits_dynewstwo_tv,recruits_dynewsthree_tv;//德育新闻部分
    private RelativeLayout recruits_twonewspart_rel,recruits_twonewspartbottome_rel;
    //专业介绍
    private RelativeLayout recruitsstudent_zymore_rel;
    private MyGdListView recruitsstudent_zyjs_lv;
    private RecruitStudentZYAdapter studentZYAdapter;
    private List<RecruitStudentZYEntity> studentZYEntities;
    private PleseYQMDailog yqmDailog;
    private String yqm="";

    private ZhaoShengInternet zhaoShengInternet;
    private ZhaoShengPersoner zhaoShengPersoner;
    private WaitDialog waitDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recruitstudent_activity);
        initData();
        initViews();
        initViewValues();
        initOnclicEvents();
    }
    private void initData()
    {
        bannarList=new ArrayList<DYNewsEntity>();
        studentZYEntities=new ArrayList<RecruitStudentZYEntity>();
        zhaoShengInternet = new ZhaoShengInternet(this,handler);
        zhaoShengPersoner = new ZhaoShengPersoner(this,handler);
        waitDialog = new WaitDialog(this,"");
        zhaoShengInternet.get_DYXWDatas("1");
        zhaoShengInternet.get_zyDatas();
        if(bannarList.size()>0){
            bannarList.clear();
        }
    }
    public void initViews(){
        title = (TextView)this.findViewById(R.id.title);
        reback_btn = (RelativeLayout)this.findViewById(R.id.reback_btn);
        recruitsstudent_zymore_rel=(RelativeLayout)this.findViewById(R.id.recruitsstudent_zymore_rel);
        recruitsstudent_zyjs_lv = (MyGdListView)this.findViewById(R.id.recruitsstudent_zyjs_lv);
        studentZYAdapter = new RecruitStudentZYAdapter(this,studentZYEntities);
        recruitsstudent_zyjs_lv.setAdapter(studentZYAdapter);
        recruitsstudent_zyjs_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecruitStudentZYEntity entity = (RecruitStudentZYEntity) studentZYAdapter.getItem(position);
                intent = new Intent(RecruitStudentActivity.this, ZyJsDetailsActivity.class);
                intent.putExtra("entity",entity);
                startActivity(intent);
            }
        });
        //德育新闻
        recruits_dynews_ll=(LinearLayout)this.findViewById(R.id.recruits_dynews_ll);
        recruits_dyxw_rel = (RelativeLayout)this.findViewById(R.id.recruits_dyxw_rel);
        recruits_dynewswait_pb=(ProgressBar)this.findViewById(R.id.recruits_dynewswait_pb);
        recruits_dynewswait_tv=(TextView)this.findViewById(R.id.recruits_dynewswait_tv);
        recruits_dynewsone_img=(ImageView)this.findViewById(R.id.recruits_dynewsone_img);
        recruits_dynewstwo_img=(ImageView)this.findViewById(R.id.recruits_dynewstwo_img);
        recruits_dynewsthree_img=(ImageView)this.findViewById(R.id.recruits_dynewsthree_img);
        recruits_dynewsone_tv=(TextView)this.findViewById(R.id.recruits_dynewsone_tv);
        recruits_dynewstwo_tv=(TextView)this.findViewById(R.id.recruits_dynewstwo_tv);
        recruits_dynewsthree_tv=(TextView)this.findViewById(R.id.recruits_dynewsthree_tv);
        recruits_twonewspart_rel=(RelativeLayout)this.findViewById(R.id.recruits_twonewspart_rel);
        recruits_twonewspartbottome_rel=(RelativeLayout)this.findViewById(R.id.recruits_twonewspartbottome_rel);


        right_text_btn= (RelativeLayout)this.findViewById(R.id.right_text_btn);
        right_btn_tv= (TextView)this.findViewById(R.id.right_btn_tv);
        //lunbotu
        recruits_banner_cb=(ConvenientBanner)this.findViewById(R.id.recruits_banner_cb);
    }
    public void initViewValues(){
        title.setText("学院介绍");
        reback_btn.setVisibility(View.GONE);
        right_text_btn.setVisibility(View.VISIBLE);
        right_btn_tv.setText("报名");
    }
    public void initOnclicEvents(){
        recruits_dyxw_rel.setOnClickListener(this);
        right_text_btn.setOnClickListener(this);
        recruitsstudent_zymore_rel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recruits_dyxw_rel:
                //跳到新闻页
                intent = new Intent(RecruitStudentActivity.this, DeYuManageActivity.class);
                startActivity(intent);
                break;
            case R.id.recruitsstudent_zymore_rel:
                //调到专业介绍
                intent = new Intent(RecruitStudentActivity.this, ZyJsListActivity.class);
                startActivity(intent);
                break;
            case R.id.right_text_btn:
                //报名
                yqmDailog = new PleseYQMDailog(this,R.style.MyDialog,handler);
                yqmDailog.show();
                break;
        }
    }
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    yqm = (String) msg.obj;
                    //请求网络
                    waitDialog.show();
                    zhaoShengInternet.getPanDuanYQMDatas(yqm);
                case 200:
                    zhaoShengPersoner.parserPDYQM((String)msg.obj);
                    break;
                case 222:
                    List<RecruitStudentZYEntity> zydatas = zhaoShengPersoner.parseZYLISTData((String)msg.obj);
                    if(zydatas!=null&&zydatas.size()>0){
                        if(studentZYEntities.size()>0){
                            studentZYEntities.clear();
                        }
                        for(RecruitStudentZYEntity entity:zydatas){
                            if(studentZYEntities.size()==10){
                                break;
                            }
                            studentZYEntities.add(entity);
                        }

                        studentZYAdapter.notifyDataSetChanged();
                    }
                    break;
                case 201:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    yqmDailog.dismiss();
                    Intent intent2 = new Intent(RecruitStudentActivity.this, RecruitStudentBMActivity.class);
                    intent2.putExtra("code",yqm);
                    startActivity(intent2);
                    break;
                case 202:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast((String)msg.obj,RecruitStudentActivity.this);
                    break;
                case 208:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<DYNewsEntity> datas = zhaoShengPersoner.parseDYnewsData((String)msg.obj);
                    if(datas!=null&&datas.size()>0) {
                        if (bannarList.size() > 0) {
                            bannarList.clear();
                        }
                        for (DYNewsEntity entity : datas) {
                            bannarList.add(entity);
                        }
                        setViewPagerDatas();
                    }
//                    recruits_dynews_ll.setVisibility(View.VISIBLE);
//                    if(bannarList.size()>0&&bannarList.size()==1){
//                        recruits_dynewsone_tv.setText(bannarList.get(0).getTitle());
//                        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+bannarList.get(0).getImage(),recruits_dynewsone_img, MyApp.getOptions());
//                        recruits_twonewspart_rel.setVisibility(View.GONE);
//                    }else if(bannarList.size()>0&&bannarList.size()==2){
//                        recruits_dynewsone_tv.setText(bannarList.get(0).getTitle());
//                        recruits_dynewstwo_tv.setText(bannarList.get(1).getTitle());
//                        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+bannarList.get(0).getImage(),recruits_dynewsone_img, MyApp.getOptions());
//                        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+bannarList.get(1).getImage(),recruits_dynewstwo_img, MyApp.getOptions());
//                        recruits_twonewspartbottome_rel.setVisibility(View.GONE);
//                    }else if(bannarList.size()>=3){
//                        recruits_dynewsone_tv.setText(bannarList.get(0).getTitle());
//                        recruits_dynewstwo_tv.setText(bannarList.get(1).getTitle());
//                        recruits_dynewsthree_tv.setText(bannarList.get(2).getTitle());
//                        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+bannarList.get(0).getImage(),recruits_dynewsone_img, MyApp.getOptions());
//                        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+bannarList.get(1).getImage(),recruits_dynewstwo_img, MyApp.getOptions());
//                        ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+bannarList.get(2).getImage(),recruits_dynewsthree_img, MyApp.getOptions());
//                        recruits_twonewspart_rel.setVisibility(View.VISIBLE);
//                        recruits_twonewspartbottome_rel.setVisibility(View.VISIBLE);
//                    }
//                }else{
//                        recruits_dynews_ll.setVisibility(View.GONE);
//                        recruits_dynewswait_tv.setVisibility(View.VISIBLE);
//                        recruits_dynewswait_tv.setText("暂无新闻");
//                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常",RecruitStudentActivity.this);
                    break;
                case 400:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("服务器连接失败",RecruitStudentActivity.this);
                    break;
            }
        }
    };
    public void setViewPagerDatas(){
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        recruits_banner_cb.setPages(
                new CBViewHolderCreator<ShowImageHolderView>() {
                    @Override
                    public ShowImageHolderView createHolder() {
                        return new ShowImageHolderView();
                    }
                }, bannarList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        DYNewsEntity entity = bannarList.get(position);
                        startActivity(new Intent(RecruitStudentActivity.this, MainNewsDetailsActivity.class)
                                .putExtra("entity",entity));
                    }
                });
        recruits_banner_cb.startTurning(3000);
    }

    public class ShowImageHolderView implements Holder<DYNewsEntity> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, DYNewsEntity data) {
//            imageView.setImageResource(data);
            ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+data.getImage(),imageView, MyApp.getOptions());
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        recruits_banner_cb.stopTurning();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recruits_banner_cb.startTurning(5000);
        // start auto scroll when onResume
    }
}
