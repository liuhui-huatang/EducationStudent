package com.htcompany.education.studentforgansu.mainpart.entity;

import com.htcompany.education.studentforgansu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wrb on 2016/10/19.
 * 主页菜单数据
 */
public class MenuLeftDatas {
    /**
     * 左侧菜单父项
     * @return
     */
     public static List<MainLeftMenuParentEntity> getParentDatas(){
         List<MainLeftMenuParentEntity> datas = new ArrayList<MainLeftMenuParentEntity>();
         MainLeftMenuParentEntity grzyEntity = new MainLeftMenuParentEntity();
         grzyEntity.setPname("个人主页");
         grzyEntity.setPdid(R.mipmap.nav_left_navleft_icon_1);
         MainLeftMenuParentEntity wdbjEntity = new MainLeftMenuParentEntity();
         wdbjEntity.setPname("我的班级");
         wdbjEntity.setPdid(R.mipmap.nav_left_navleft_icon_2);
         MainLeftMenuParentEntity pjEntity = new MainLeftMenuParentEntity();
         pjEntity.setPname("我的评价");
         pjEntity.setPdid(R.mipmap.nav_left_navleft_icon_3);
         MainLeftMenuParentEntity tpEntity = new MainLeftMenuParentEntity();
         tpEntity.setPname("请假信息");
         tpEntity.setPdid(R.mipmap.nav_left_navleft_icon_4);
         MainLeftMenuParentEntity qjEntity = new MainLeftMenuParentEntity();
         qjEntity.setPname("校内事宜");
         qjEntity.setPdid(R.mipmap.nav_left_navleft_icon_5);
         MainLeftMenuParentEntity zzEntity = new MainLeftMenuParentEntity();
         zzEntity.setPname("我的资助");
         zzEntity.setPdid(R.mipmap.nav_left_navleft_icon_6);
         MainLeftMenuParentEntity qyEntity = new MainLeftMenuParentEntity();
         qyEntity.setPname("企业信息");
         qyEntity.setPdid(R.mipmap.nav_left_navleft_icon_8);
         MainLeftMenuParentEntity xytsEntity = new MainLeftMenuParentEntity();
         xytsEntity.setPname("校园图书");
         xytsEntity.setPdid(R.mipmap.nav_left_navleft_icon_9);
         datas.add(grzyEntity);
         datas.add(wdbjEntity);
         datas.add(pjEntity);
         datas.add(tpEntity);
         datas.add(qjEntity);
         datas.add(zzEntity);
         datas.add(qyEntity);
         datas.add(xytsEntity);
         return datas;
     }
    /**
     * 左侧菜单子项
     * @return
     */
    public static List<List<MainLeftMenuChildEntity>> getChildDatas(){
       List<List<MainLeftMenuChildEntity>> datas = new ArrayList<List<MainLeftMenuChildEntity>>();
        //个人主页
        List<MainLeftMenuChildEntity> grzyList = new ArrayList<MainLeftMenuChildEntity>();
        MainLeftMenuChildEntity jbxx = new MainLeftMenuChildEntity();
        jbxx.setCname("基本信息");
        jbxx.setViewflag("JBXX");
        jbxx.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity wdcj = new MainLeftMenuChildEntity();
        wdcj.setCname("我的成绩");
        wdcj.setViewflag("WDCJ");
        wdcj.setCdid(R.drawable.main_leftchild_shape2);
        MainLeftMenuChildEntity tzxx = new MainLeftMenuChildEntity();
        tzxx.setCname("体质信息");
        tzxx.setViewflag("TJTZ");
        tzxx.setCdid(R.drawable.main_leftchild_shape3);
        MainLeftMenuChildEntity wdxxk = new MainLeftMenuChildEntity();
        wdxxk.setCname("我的选修课");
        wdxxk.setCdid(R.drawable.main_leftchild_shape4);
        wdxxk.setViewflag("WDXXK");
        grzyList.add(jbxx);
        grzyList.add(wdcj);
        grzyList.add(tzxx);
        grzyList.add(wdxxk);
        //我的班级
        List<MainLeftMenuChildEntity> wdbjList = new ArrayList<MainLeftMenuChildEntity>();
        MainLeftMenuChildEntity bjxx = new MainLeftMenuChildEntity();
        bjxx.setCname("班级信息");
        bjxx.setViewflag("BJMSG");
        bjxx.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity bjkb = new MainLeftMenuChildEntity();
        bjkb.setCname("班级课表");
        bjkb.setCdid(R.drawable.main_leftchild_shape2);
        bjkb.setViewflag("WDBJKB");
        MainLeftMenuChildEntity bjgg = new MainLeftMenuChildEntity();
        bjgg.setCname("班级公告");
        bjgg.setViewflag("BJGG");
        bjgg.setCdid(R.drawable.main_leftchild_shape3);
        MainLeftMenuChildEntity xsgg = new MainLeftMenuChildEntity();
        xsgg.setCname("学生公告");
        xsgg.setViewflag("XSGG");
        xsgg.setCdid(R.drawable.main_leftchild_shape4);
        wdbjList.add(bjxx);
        wdbjList.add(bjkb);
        wdbjList.add(bjgg);
        wdbjList.add(xsgg);
        //我的评价
        List<MainLeftMenuChildEntity> wdpjList = new ArrayList<MainLeftMenuChildEntity>();
        MainLeftMenuChildEntity zhszpj = new MainLeftMenuChildEntity();
        zhszpj.setCname("综合素质评价");
        zhszpj.setViewflag("ZHSZPJ");
        zhszpj.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity xqpy = new MainLeftMenuChildEntity();
        xqpy.setCname("学期评语");
        xqpy.setViewflag("XQPY");
        xqpy.setCdid(R.drawable.main_leftchild_shape2);
//        MainLeftMenuChildEntity cxzwpd = new MainLeftMenuChildEntity();
//        cxzwpd.setCname("操行自我评定");
//        cxzwpd.setViewflag("CXZWPD");
//        cxzwpd.setCdid(R.drawable.main_leftchild_shape3);
        MainLeftMenuChildEntity bypy = new MainLeftMenuChildEntity();
        bypy.setCname("毕业评语");
        bypy.setViewflag("BYPY");
        bypy.setCdid(R.drawable.main_leftchild_shape4);
        MainLeftMenuChildEntity jcjl = new MainLeftMenuChildEntity();
        jcjl.setCname("奖惩记录");
        jcjl.setViewflag("JCJL");
        jcjl.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity zwjy = new MainLeftMenuChildEntity();
        zwjy.setCname("自我教育");
        zwjy.setViewflag("ZWJY");
        zwjy.setCdid(R.drawable.main_leftchild_shape2);
        wdpjList.add(zhszpj);
        wdpjList.add(xqpy);
//        wdpjList.add(cxzwpd);
        wdpjList.add(bypy);
        wdpjList.add(jcjl);
        wdpjList.add(zwjy);
        //请假信息
        List<MainLeftMenuChildEntity> qjList = new ArrayList<MainLeftMenuChildEntity>();
        MainLeftMenuChildEntity qjsq = new MainLeftMenuChildEntity();
        qjsq.setCname("请假申请");
        qjsq.setViewflag("QJSQ");
        qjsq.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity qjJL = new MainLeftMenuChildEntity();
        qjJL.setCname("请假记录");
        qjJL.setViewflag("QJJL");
        qjJL.setCdid(R.drawable.main_leftchild_shape2);
        qjList.add(qjsq);
        qjList.add(qjJL);
        //校内事宜
        List<MainLeftMenuChildEntity> xnsy = new ArrayList<MainLeftMenuChildEntity>();
        MainLeftMenuChildEntity xytp = new MainLeftMenuChildEntity();
        xytp.setCname("校园投票");
        xytp.setViewflag("XYTP");
        xytp.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity zcbx = new MainLeftMenuChildEntity();
        zcbx.setCname("资产报修");
        zcbx.setCdid(R.drawable.main_leftchild_shape2);
        zcbx.setViewflag("ZCBX");
        MainLeftMenuChildEntity jqysjl = new MainLeftMenuChildEntity();
        jqysjl.setCname("假期要事记录");
        jqysjl.setCdid(R.drawable.main_leftchild_shape3);
        jqysjl.setViewflag("JQYSJL");
        MainLeftMenuChildEntity jnds = new MainLeftMenuChildEntity();
        jnds.setCname("技能大赛");
        jnds.setCdid(R.drawable.main_leftchild_shape4);
        jnds.setViewflag("JNDS");
        MainLeftMenuChildEntity jxhd = new MainLeftMenuChildEntity();
        jxhd.setCname("教学互动");
        jxhd.setCdid(R.drawable.main_leftchild_shape1);
        jxhd.setViewflag("JXHD");
        MainLeftMenuChildEntity jnjdgl = new MainLeftMenuChildEntity();
        jnjdgl.setCname("技能鉴定");
        jnjdgl.setCdid(R.drawable.main_leftchild_shape2);
        jnjdgl.setViewflag("JNJDGL");
        xnsy.add(xytp);
        xnsy.add(zcbx);
        xnsy.add(jqysjl);
        xnsy.add(jnds);
        xnsy.add(jxhd);
        xnsy.add(jnjdgl);
        //我的资助
        List<MainLeftMenuChildEntity> wdzjList = new ArrayList<MainLeftMenuChildEntity>();
        MainLeftMenuChildEntity jxj = new MainLeftMenuChildEntity();
        jxj.setCname("奖学金");
        jxj.setViewflag("JXJ");
        jxj.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity zxj = new MainLeftMenuChildEntity();
        zxj.setCname("助学金");
        zxj.setCdid(R.drawable.main_leftchild_shape2);
        zxj.setViewflag("WDZXJ");
        MainLeftMenuChildEntity lsknbzsq = new MainLeftMenuChildEntity();
        lsknbzsq.setCname("临时困难补助");
        lsknbzsq.setCdid(R.drawable.main_leftchild_shape3);
        lsknbzsq.setViewflag("LSKNBZSQ");
        MainLeftMenuChildEntity mxfsq = new MainLeftMenuChildEntity();
        mxfsq.setCname("免学费");
        mxfsq.setCdid(R.drawable.main_leftchild_shape4);
        mxfsq.setViewflag("WDMXF");
        MainLeftMenuChildEntity zxdk = new MainLeftMenuChildEntity();
        zxdk.setCname("助学贷款");
        zxdk.setCdid(R.drawable.main_leftchild_shape1);
        zxdk.setViewflag("ZXDK");
        wdzjList.add(jxj);
        wdzjList.add(zxj);
        wdzjList.add(lsknbzsq);
        wdzjList.add(mxfsq);
        wdzjList.add(zxdk);
        //企业信息
        List<MainLeftMenuChildEntity> qyList = new ArrayList<MainLeftMenuChildEntity>();
        MainLeftMenuChildEntity qyxx = new MainLeftMenuChildEntity();
        qyxx.setCname("企业招聘");
        qyxx.setViewflag("QYXX");
        qyxx.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity sxjl = new MainLeftMenuChildEntity();
        sxjl.setCname("实习记录");
        sxjl.setCdid(R.drawable.main_leftchild_shape2);
        sxjl.setViewflag("SXJL");
        MainLeftMenuChildEntity jyjl = new MainLeftMenuChildEntity();
        jyjl.setCname("就业记录");
        jyjl.setCdid(R.drawable.main_leftchild_shape3);
        jyjl.setViewflag("JYJL");
        MainLeftMenuChildEntity ybmzpgw = new MainLeftMenuChildEntity();
        ybmzpgw.setCname("已报名招聘岗位");
        ybmzpgw.setCdid(R.drawable.main_leftchild_shape4);
        ybmzpgw.setViewflag("YBMZPGW");
        qyList.add(qyxx);
        qyList.add(sxjl);
        qyList.add(jyjl);
        qyList.add(ybmzpgw);
        //校园图书
        List<MainLeftMenuChildEntity> xytuList = new ArrayList<MainLeftMenuChildEntity>();
        MainLeftMenuChildEntity tsxx = new MainLeftMenuChildEntity();
        tsxx.setCname("校园图书");
        tsxx.setViewflag("XYTS");
        tsxx.setCdid(R.drawable.main_leftchild_shape1);
        MainLeftMenuChildEntity wyddts = new MainLeftMenuChildEntity();
        wyddts.setCname("我的预定");
        wyddts.setCdid(R.drawable.main_leftchild_shape2);
        wyddts.setViewflag("WDTSYD");
        xytuList.add(tsxx);
        xytuList.add(wyddts);
        datas.add(grzyList);
        datas.add(wdbjList);
        datas.add(wdpjList);
        datas.add(qjList);
        datas.add(xnsy);
        datas.add(wdzjList);
        datas.add(qyList);
        datas.add(xytuList);
        return datas;
    }

    /**
     * 获取学期数据
     * @return
     */
    public static  List<TermEntity> getTermsDatas(){
        List<TermEntity> datas = new ArrayList<TermEntity>();
        TermEntity one = new TermEntity();
        one.setName("第一学期");
        TermEntity two = new TermEntity();
        two.setName("第二学期");
        TermEntity threes = new TermEntity();
        threes.setName("第三学期");
        TermEntity four = new TermEntity();
        four.setName("第四学期");
        TermEntity five = new TermEntity();
        five.setName("第五学期");
        TermEntity six = new TermEntity();
        six.setName("第六学期");
        TermEntity seven = new TermEntity();
        seven.setName("第七学期");
        datas.add(one);
        datas.add(two);
        datas.add(threes);
        datas.add(four);
        datas.add(five);
        datas.add(six);
        datas.add(seven);
        return datas;
    }

    /**
     * 获取我的成绩课程子项颜色
     * @return
     */
    public static List<Integer> getColorsListDatas(){
        List<Integer> datas = new ArrayList<Integer>();
//        datas.add(R.mipmap.result_content_bg_1);
        datas.add(R.mipmap.result_content_bg_2);
        datas.add(R.mipmap.result_content_bg_3);
        datas.add(R.mipmap.result_content_bg_4);
        datas.add(R.mipmap.result_content_bg_5);
        datas.add(R.mipmap.result_content_bg_6);
        datas.add(R.mipmap.result_content_bg_7);
        datas.add(R.mipmap.result_content_bg_8);
        datas.add(R.mipmap.result_content_bg_9);
        datas.add(R.mipmap.result_content_bg_2);
        datas.add(R.mipmap.result_content_bg_3);
        datas.add(R.mipmap.result_content_bg_4);
        datas.add(R.mipmap.result_content_bg_5);
        datas.add(R.mipmap.result_content_bg_6);
        datas.add(R.mipmap.result_content_bg_7);
        datas.add(R.mipmap.result_content_bg_8);
        datas.add(R.mipmap.result_content_bg_9);
        datas.add(R.mipmap.result_content_bg_2);
        datas.add(R.mipmap.result_content_bg_3);
        datas.add(R.mipmap.result_content_bg_4);
        datas.add(R.mipmap.result_content_bg_5);
        datas.add(R.mipmap.result_content_bg_6);
        datas.add(R.mipmap.result_content_bg_7);
        datas.add(R.mipmap.result_content_bg_8);
        datas.add(R.mipmap.result_content_bg_9);

        return datas;
    }
    /**
     * 获取技能鉴定报名北背景图
     * @return
     */
    public static List<Integer> getBG_JNJDDatas(){
        List<Integer> datas = new ArrayList<Integer>();
        datas.add(R.mipmap.jnbm_bg1);
        datas.add(R.mipmap.jnbm_bg2);
        datas.add(R.mipmap.jnbm_bg3);
        datas.add(R.mipmap.jnbm_bg4);
        return datas;
    }
    /**
     * 获取技能鉴定报名字体颜色
     * @return
     */
    public static List<Integer> getTVCOLOR_JNJDDatas(){
        List<Integer> datas = new ArrayList<Integer>();
        datas.add(R.color.jnjdlb_tv1color);
        datas.add(R.color.jnjdlb_tv2color);
        datas.add(R.color.jnjdlb_tv3color);
        datas.add(R.color.jnjdlb_tv4color);
        return datas;
    }
}
