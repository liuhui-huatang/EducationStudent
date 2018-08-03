package com.htcompany.education.studentforgansu;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htcompany.education.studentforgansu.commonpart.BaseActivity;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.commonpart.tools.SharedPrefUtil;
import com.htcompany.education.studentforgansu.commonpart.tools.ToastUtil;
import com.htcompany.education.studentforgansu.commonpart.tools.VersionUpdataUtils;
import com.htcompany.education.studentforgansu.commonpart.views.HorizontalListView;
import com.htcompany.education.studentforgansu.commonpart.views.MyGdListView;
import com.htcompany.education.studentforgansu.commonpart.views.WaitDialog;
import com.htcompany.education.studentforgansu.commonpart.views.XCRoundAndOvalImageView;
import com.htcompany.education.studentforgansu.internet.InterfaceManager;
import com.htcompany.education.studentforgansu.internet.maininternet.MainInternet;
import com.htcompany.education.studentforgansu.internet.maininternet.MainPersener;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolThingsInternet;
import com.htcompany.education.studentforgansu.internet.schoolthings.SchoolTingsPersener;
import com.htcompany.education.studentforgansu.mainpart.activity.BooksFuctionActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.ClassAnnouncementActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.ClassFuctionActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.ClassTimetableActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.CompanyFunctionActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.DeYuManageActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.EvaluateFuctionActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.H5LunTanActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.LeaveApplyFuctionActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.MainNewsDetailsActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.MyClassMssageActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.PersonageActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.SchoolThingsFuctionActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.SettingActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.StudentAnnouncementActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.SubsidizeFunctionActivity;
import com.htcompany.education.studentforgansu.mainpart.activity.TeacherEvaluationActivity;
import com.htcompany.education.studentforgansu.mainpart.adapter.MainWorkNewsAdapter;
import com.htcompany.education.studentforgansu.mainpart.adapter.ToadayClassAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTableEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.DYNewsEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;

import java.util.ArrayList;
import java.util.List;

/**\
 * 程序主页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener{
    private ScrollView main_scrollview;
    private TextView title;
    private RelativeLayout reabck_btn,right_share_rel;
    private ImageView reback_btn_img,right_share_img;
    //班级课表，班级信息,校园投票，学校公告
    private RelativeLayout main_calsstabletime_rel,main_classmsg_rel,main_schoolvoting_rel,main_schoolannoncement_rel;
    //当天课程，技能大赛，学校论坛，德育新闻
    private RelativeLayout main_todayclasstable_rel,main_dyxw_rel;
    private ImageView main_shcollbook_ll,main_teacherpj_ll;//教师评教
    private LayoutInflater inflater;
    private LinearLayout mainmenu_left;
    private View lefmenuView;//左侧菜单布局
    //轮播图
    private ConvenientBanner main_banner_cb;
    private ArrayList<DYNewsEntity> bannarList=new ArrayList<DYNewsEntity>();
    //================德育新闻======================
    private SchoolThingsInternet thingsInternet;
    private SchoolTingsPersener tingsPersener;
    private MyGdListView main_tennews_gl;
    private MainWorkNewsAdapter newsAdapter;
    private ArrayList<DYNewsEntity> allnewsList=new ArrayList<DYNewsEntity>();
    //===============
    private DrawerLayout drawer_layout;
    private Intent intent=null;

    private SharedPrefUtil sharedPrefUtil=null;
    //================当天课程======================
    private HorizontalListView main_todayclass_lv;
    private ToadayClassAdapter toadayClassAdapter;
    private  List<ClassTableEntity> tableEntities;
    private ProgressBar today_classwait_pb;//当天课程进度框
    private TextView today_classnotice_tv;//没有当天课程提示

    //==============网络请求类===============
    private MainInternet mainInternet;
    private MainPersener mainPersener;
    private WaitDialog waitDialog;
    //版本更新
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);
        //版本更新
       // VersionUpdataUtils versionUpdataUtils =new VersionUpdataUtils(this);
        initData();
        initViews();
        initValues();
        initOnlcikEvents();
    }
    private void initData()
    {
        tableEntities = new ArrayList<ClassTableEntity>();
        mainInternet = new MainInternet(MainActivity.this,myHandler);
        mainPersener = new MainPersener(MainActivity.this);
        allnewsList = new ArrayList<DYNewsEntity>();
        thingsInternet = new SchoolThingsInternet(this,myHandler);
        tingsPersener = new SchoolTingsPersener(this);
        mainInternet.getTermMsg();
        mainInternet.getToadayClassTableDatas();
        thingsInternet.get_DYXWDatas("1","");
        thingsInternet.get_ALLDYXWDatas("","");
        sharedPrefUtil = new SharedPrefUtil(this, "login");
        if(bannarList.size()>0){
            bannarList.clear();
        }
    }
    public void initViews(){
        main_scrollview=(ScrollView)this.findViewById(R.id.main_scrollview);
        title = (TextView)this.findViewById(R.id.title);
        reabck_btn=(RelativeLayout)this.findViewById(R.id.reback_btn);
        right_share_rel=(RelativeLayout)this.findViewById(R.id.right_share_rel);
        reback_btn_img = (ImageView)this.findViewById(R.id.reback_btn_img);
        right_share_img = (ImageView)this.findViewById(R.id.right_share_img);
        main_calsstabletime_rel=(RelativeLayout)this.findViewById(R.id.main_calsstabletime_rel);
        main_classmsg_rel=(RelativeLayout)this.findViewById(R.id.main_classmsg_rel);
        main_schoolvoting_rel=(RelativeLayout)this.findViewById(R.id.main_schoolvoting_rel);
        main_schoolannoncement_rel=(RelativeLayout)this.findViewById(R.id.main_schoolannoncement_rel);
        main_todayclasstable_rel= (RelativeLayout)this.findViewById(R.id.main_todayclasstable_rel);
        main_teacherpj_ll = (ImageView) this.findViewById(R.id.main_teacherpj_ll);
        main_shcollbook_ll= (ImageView) this.findViewById(R.id.main_shcollbook_ll);
        main_dyxw_rel= (RelativeLayout)this.findViewById(R.id.main_dyxw_rel);
        drawer_layout = (DrawerLayout)this.findViewById(R.id.drawer_layout);
        drawer_layout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset>0){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if(tintManager!=null){
                            tintManager.setTintDrawable(getResources().getDrawable(R.mipmap.main_titlebar_cs));
                        }
                    }
                }
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if(tintManager!=null){
                        tintManager.setTintDrawable(getResources().getDrawable(R.mipmap.main_titlebar_cs));
                    }
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if(tintManager!=null){
                        tintManager.setTintDrawable(getResources().getDrawable(R.mipmap.titlebar_status_bg));
                    }
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mainmenu_left=(LinearLayout) this.findViewById(R.id.mainmenu_left);
        initMenuValues();
       //当天课程
        main_todayclass_lv = (HorizontalListView)this.findViewById(R.id.main_todayclass_lv);
        toadayClassAdapter = new ToadayClassAdapter(this,tableEntities);
        main_todayclass_lv.setAdapter(toadayClassAdapter);
        today_classwait_pb = (ProgressBar)this.findViewById(R.id.today_classwait_pb);
        today_classnotice_tv = (TextView)this.findViewById(R.id.today_classnotice_tv);
        today_classwait_pb.setVisibility(View.VISIBLE);

        main_todayclass_lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ViewGroup viewGroup = (ViewGroup) v.getParent();
                viewGroup.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        //德育新闻
        main_tennews_gl=(MyGdListView)this.findViewById(R.id.main_tennews_gl);
        newsAdapter=new MainWorkNewsAdapter(this,allnewsList);
        main_tennews_gl.setAdapter(newsAdapter);
        main_tennews_gl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DYNewsEntity entity = (DYNewsEntity) newsAdapter.getItem(position);
                intent = new Intent(MainActivity.this,MainNewsDetailsActivity.class);
                intent.putExtra("entity",entity);
                startActivity(intent);
            }
        });
        //轮播图
        main_banner_cb =(ConvenientBanner)this.findViewById(R.id.main_banner_cb);
    }
    /**
     * 初始化左侧菜单布局
     */
    public void initMenuValues(){
        lefmenuView = inflater.inflate(R.layout.residmenu_left_contentview,null);
//        ExpandableListView expandableListView = (ExpandableListView)lefmenuView.findViewById(R.id.leftmenu_listview);
//        MainLeftMenuAdpter mainLeftMenuAdpter = new MainLeftMenuAdpter(this, MenuLeftDatas.getParentDatas(),MenuLeftDatas.getChildDatas());
//        expandableListView.setAdapter(mainLeftMenuAdpter);
        LinearLayout main_grzy_ll = (LinearLayout)lefmenuView.findViewById(R.id.main_grzy_ll);
        LinearLayout main_wdbj_ll = (LinearLayout)lefmenuView.findViewById(R.id.main_wdbj_ll);
        LinearLayout main_wdpj_ll= (LinearLayout)lefmenuView.findViewById(R.id.main_wdpj_ll);
        LinearLayout main_wdqj_ll = (LinearLayout)lefmenuView.findViewById(R.id.main_wdqj_ll);
        LinearLayout main_xnsy_ll = (LinearLayout)lefmenuView.findViewById(R.id.main_xnsy_ll);
        LinearLayout main_wdzz_ll = (LinearLayout)lefmenuView.findViewById(R.id.main_wdzz_ll);
        LinearLayout main_qyxx_ll = (LinearLayout)lefmenuView.findViewById(R.id.main_qyxx_ll);
        LinearLayout main_xyts_ll = (LinearLayout)lefmenuView.findViewById(R.id.main_xyts_ll);
        main_grzy_ll.setOnClickListener(menuclicer);
        main_wdbj_ll.setOnClickListener(menuclicer);
        main_wdpj_ll.setOnClickListener(menuclicer);
        main_wdqj_ll.setOnClickListener(menuclicer);
        main_xnsy_ll.setOnClickListener(menuclicer);
        main_wdzz_ll.setOnClickListener(menuclicer);
        main_qyxx_ll.setOnClickListener(menuclicer);
        main_xyts_ll.setOnClickListener(menuclicer);
        TextView main_sname_tv = (TextView)lefmenuView.findViewById(R.id.main_sname_tv);
        main_sname_tv.setText(sharedPrefUtil.getString("sname",""));
        TextView main_snumber_tv = (TextView)lefmenuView.findViewById(R.id.main_snumber_tv);
        main_snumber_tv.setText(sharedPrefUtil.getString("number",""));
        TextView main_jxb_tv=(TextView)lefmenuView.findViewById(R.id.main_jxb_tv);
        main_jxb_tv.setText("教学班:"+sharedPrefUtil.getString("jxb",""));
        TextView main_xzb_tv=(TextView)lefmenuView.findViewById(R.id.main_xzb_tv);
        main_xzb_tv.setText("行政班:"+sharedPrefUtil.getString("xzb",""));
        XCRoundAndOvalImageView leftmenu_photo_img = (XCRoundAndOvalImageView)lefmenuView.findViewById(R.id.leftmenu_photo_img);
        Glide.with(this)
                .load(InterfaceManager.siteURLIP+sharedPrefUtil.getString("photo",""))
                .placeholder(R.mipmap.defult_photo_icon)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(leftmenu_photo_img);
        mainmenu_left.addView(lefmenuView);
    }
    public static int flag = -1;
    public void initValues(){
       title.setText("首页");
        right_share_rel.setVisibility(View.VISIBLE);
        right_share_img.setImageResource(R.mipmap.right_icon);
       reback_btn_img.setImageDrawable(getResources().getDrawable(R.mipmap.common_nav_btn_menu_n));
       title.setFocusable(true);
       title.setFocusableInTouchMode(true);
       main_scrollview.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               switch (event.getAction()) {
                   case MotionEvent.ACTION_DOWN:
                       flag = 0;
                       Log.e("test", "down");
                       break;
                   case MotionEvent.ACTION_MOVE:
                       Log.e("test", "move");
                       break;
                   case MotionEvent.ACTION_UP:
                       flag = -1;
                       Log.e("test", "up");
                       break;
               }
               return false;
           }
       });
   }
    public View.OnClickListener menuclicer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
         switch (v.getId()){
             case R.id.main_grzy_ll:
                 //个人主页
                 intent = new Intent(MainActivity.this, PersonageActivity.class);
                 startActivity(intent);
                 break;
             case R.id.main_wdbj_ll:
                 //我的班级
                 intent = new Intent(MainActivity.this, ClassFuctionActivity.class);
                 startActivity(intent);
                 break;
             case R.id.main_wdpj_ll:
                 //我的评价
                 intent = new Intent(MainActivity.this, EvaluateFuctionActivity.class);
                 startActivity(intent);
                 break;
             case R.id.main_wdqj_ll:
                 //我的请假
                 intent = new Intent(MainActivity.this, LeaveApplyFuctionActivity.class);
                 startActivity(intent);
                 break;
             case R.id.main_xnsy_ll:
                 //校内事宜
                 intent = new Intent(MainActivity.this, SchoolThingsFuctionActivity.class);
                 startActivity(intent);
                 break;
             case R.id.main_wdzz_ll:
                 //我的资助
                 intent = new Intent(MainActivity.this, SubsidizeFunctionActivity.class);
                 startActivity(intent);
                 break;
             case R.id.main_qyxx_ll:
                 //企业信息
                 intent = new Intent(MainActivity.this, CompanyFunctionActivity.class);
                 startActivity(intent);
                 break;
             case R.id.main_xyts_ll:
                 //校园图书
                 intent = new Intent(MainActivity.this, BooksFuctionActivity.class);
                 startActivity(intent);
                 break;
         }
        }
    };
    /**
     * 初始化点击事件
     */
    public void initOnlcikEvents(){
      reabck_btn.setOnClickListener(this);
        main_teacherpj_ll.setOnClickListener(this);
        main_calsstabletime_rel.setOnClickListener(this);
        main_classmsg_rel.setOnClickListener(this);
        main_schoolvoting_rel.setOnClickListener(this);
        main_schoolannoncement_rel.setOnClickListener(this);
        main_dyxw_rel.setOnClickListener(this);
        main_todayclasstable_rel.setOnClickListener(this);
        main_shcollbook_ll.setOnClickListener(this);
        right_share_rel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reback_btn:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
            case R.id.right_share_rel:
                //跳到设置界面
                intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.main_calsstabletime_rel:
                //班级课表
                intent = new Intent(MainActivity.this, ClassTimetableActivity.class);
                startActivity(intent);
                break;
            case R.id.main_classmsg_rel:
                //班级信息
                intent = new Intent(MainActivity.this, MyClassMssageActivity.class);
                startActivity(intent);
                break;
            case R.id.main_schoolvoting_rel:
                //班级公告
                intent = new Intent(MainActivity.this, ClassAnnouncementActivity.class);
                startActivity(intent);
                break;
            case R.id.main_schoolannoncement_rel:
                //学生公告
                intent = new Intent(MainActivity.this, StudentAnnouncementActivity.class);
                startActivity(intent);
                break;
            case R.id.main_todayclasstable_rel:
                //课表（当天课程）
                intent = new Intent(MainActivity.this, ClassTimetableActivity.class);
                startActivity(intent);
                break;
            case R.id.main_dyxw_rel:
                //德育新闻
                intent = new Intent(MainActivity.this, DeYuManageActivity.class);
                startActivity(intent);
                break;
            case R.id.main_teacherpj_ll:
                //教师评教
                intent = new Intent(MainActivity.this, TeacherEvaluationActivity.class);
                startActivity(intent);
                break;
            case R.id.main_shcollbook_ll:
                //校园图书改为论坛
                intent = new Intent(MainActivity.this, H5LunTanActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        // stop auto scroll when onPause
        main_banner_cb.stopTurning();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // start auto scroll when onResume
        main_banner_cb.startTurning(5000);
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
                    ToastUtil.showToast("连接超时", MainActivity.this);
                    break;
                case 200:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    List<TermEntity> entity=mainPersener.parseTermData((String)msg.obj);
                    if(entity!=null){
                       if(MyApp.termEntities.size()>0){
                           MyApp.termEntities.clear();
                       }
                        for(TermEntity entity1:entity){
                            MyApp.termEntities.add(entity1);
                        }
                    }
                    break;
                case 201:
                    //当天课表
                    List<ClassTableEntity> datas = mainPersener.parseTodayClassTableData((String)msg.obj);
                    today_classwait_pb.setVisibility(View.GONE);
                    today_classnotice_tv.setVisibility(View.GONE);
                    if(datas!=null&&datas.size()>0){
                        main_todayclass_lv.setVisibility(View.VISIBLE);
                        if(tableEntities.size()>0){
                            tableEntities.clear();
                        }
                        for(ClassTableEntity entity1:datas){
                            tableEntities.add(entity1);
                        }
                        toadayClassAdapter.notifyDataSetChanged();
                    }else{
                        main_todayclass_lv.setVisibility(View.GONE);
                        today_classnotice_tv.setVisibility(View.VISIBLE);
                        today_classnotice_tv.setText("今天无课");
                    }
                    break;
                case 202:
                    //德育新闻
                    List<DYNewsEntity> dataNews = tingsPersener.parseDYnewsData((String)msg.obj);
                    if(dataNews!=null&&dataNews.size()>0){
                        if(bannarList.size()>0){
                            bannarList.clear();
                        }
                        bannarList.addAll(dataNews);

                        setViewPagerDatas();

                    }else{
                    }
                    break;
                case 203:
                    //全部德育新闻
                    List<DYNewsEntity> allNews = tingsPersener.parseDYnewsData((String)msg.obj);
                    if(allNews!=null&&allNews.size()>0){
                        if(allnewsList.size()>0){
                            allnewsList.clear();
                        }
                        for(DYNewsEntity news:allNews){
                            if(allnewsList.size()==10){
                                break;
                            }else{
                                allnewsList.add(news);
                            }
                        }
                        newsAdapter.notifyDataSetChanged();

                    }else{
                    }
                    break;
                case 300:
                    if(waitDialog!=null){
                        waitDialog.dismiss();
                    }
                    ToastUtil.showToast("数据异常", MainActivity.this);
                    break;
            }
        }
    };

    public void setViewPagerDatas(){
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        main_banner_cb.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
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
                        startActivity(new Intent(MainActivity.this, MainNewsDetailsActivity.class)
                                .putExtra("entity",entity));
                    }
                });
        main_banner_cb.startTurning(3000);
    }
    public class LocalImageHolderView implements Holder<DYNewsEntity> {
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
//            ImageLoader.getInstance().displayImage(InterfaceManager.siteURLIP+data.getImage(),imageView, MyApp.getOptions());

            Log.e("UpdateUI",InterfaceManager.siteURLIP+data.getImage());
            Glide.with(context)
                    .load(InterfaceManager.siteURLIP+data.getImage())
                    .placeholder(R.mipmap.bottombg_show_icon)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(imageView);
        }
    }

}
