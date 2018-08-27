package com.htcompany.education.studentforgansu.internet;

import java.util.HashMap;

/**
 * 路径设置类
 *
 * @author wrb
 */
public class InterfaceManager {
    /**
     * 、、、、、、、、、、、、、、、、、公共数据、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String ALL_TERME = "ALL_TERME";//所有学期
    /**
     * 、、、、、、、、、、、、、、、、、登录模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String lOGIN_LOGIN = "lOGIN_LOGIN";//登录接口
    public static final String LUNTAN_BBS = "LUNTAN_BBS";//论坛接口
    /**
     * 、、、、、、、、、、、、、、、、、个人主页模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String GRZY_JBXX = "GRZY_JBXX";//基本信息接口
    public static final String GRZY_WDCJ = "GRZY_WDCJ";//我的成绩接口
    public static final String GRZY_TJXX = "GRZY_TJXX";//体检信息接口
    public static final String GRZY_TZXX = "GRZY_TZXX";//体质信息接口
    public static final String GRZY_WDXXK = "GRZY_WDXXK";//我的选修课接口
    /**
     * 、、、、、、、、、、、、、、、、、我的班级模块模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String MYCLASS_CLASSTABLE = "WROK_TEACHER_CLASSTABLE";//班级课表
    public static final String MYCLASS_STUDENT_TXL = "MYCLASS_STUDENT_TXL";//班级通讯录
    public static final String MYCLASS_CLASS_MSG = "MYCLASS_CLASS_MSG";//班级信息
    public static final String MYCLASS_CLASS_GG = "MYCLASS_CLASS_GG";//班级公告
    public static final String MYCLASS_STUDENT_GG = "MYCLASS_STUDENT_GG";//学生公告
    /**
     * 、、、、、、、、、、、、、、、、、我的评价模块模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String MYEVALUATE_STUDENTJL = "MYEVALUATE_STUDENTJL";//学生奖励
    public static final String MYEVALUATE_STUDENTCF = "MYEVALUATE_STUDENTCF";//学生惩罚
    public static final String MYEVALUATE_BYPY = "MYEVALUATE_BYPY";//毕业评语
    public static final String MYEVALUATE_TERMPY = "MYEVALUATE_TERMPY";//学期评语

    public static final String MYQJ_LIST = "MYQJ_LIST";//学生请假列表
    public static final String MYQJ_WANT = "MYQJ_WANT";//学生请假申请
    public static final String MYQJ_WANT_TYPES = "MYQJ_WANT_TYPES";//学生请假类型
    /**
     * 、、、、、、、、、、、、、、、、、校内事宜模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String MYEVALUATE_STUDENT_JXHDLIST = "MYEVALUATE_STUDENT_JXHDLIST";//教学互动列表
    public static final String MYEVALUATE_STUDENT_KECHENG = "MYEVALUATE_STUDENT_KECHENG";//获取课程
    public static final String MYEVALUATE_CLASS_TEACJERS = "MYEVALUATE_CLASS_TEACJERS";//获取课程教师
    public static final String MYEVALUATE_JXHD_UPLODE = "MYEVALUATE_JXHD_UPLODE";//提交教学互动问题
    public static final String MYEVALUATE_JXHD_UPLODE_SFJJ = "MYEVALUATE_JXHD_UPLODE_SFJJ";//提交教学互动问题是否解决
    public static final String SCHOOLTHINGS_DYNEWS = "SCHOOLTHINGS_DYNEWS";//德育新闻
    public static final String SCHOOLTHINGS_SHILL_LIST = "SCHOOLTHINGS_SHILL_LIST";//技能大赛
    public static final String SCHOOLTHINGS_SHILL_DETAILS = "SCHOOLTHINGS_SHILL_DETAILS";//技能大赛详情
    public static final String SCHOOLTHINGS_ZCWH_LIST = "SCHOOLTHINGS_ZCWH_LIST";//资产列表接口
    public static final String SCHOOLTHINGS_ADDZCWH = "SCHOOLTHINGS_ADDZCWH";//添加资产报修列表

    /**
     * 、、、、、、、、、、、、、、、、、我的资助模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String MYSUBSIDIZE_JXJ_LIST = "MYSUBSIDIZE_JXJ_LIST";//奖学金列表
    public static final String MYSUBSIDIZE_ZXJ_LIST = "MYSUBSIDIZE_ZXJ_LIST";//助学金学金列表
    public static final String MYSUBSIDIZE_ZXJTYPE_LIST = "MYSUBSIDIZE_ZXJTYPE_LIST";//助学金类型列表
    public static final String MYSUBSIDIZE_ZXJ_ADD = "MYSUBSIDIZE_ZXJ_ADD";//助学金申请
    public static final String MYSUBSIDIZE_LSKNBZ_LIST = "MYSUBSIDIZE_LSKNBZ_LIST";//临时困难补助列表
    public static final String MYSUBSIDIZE_LSKNBZ_ADD = "MYSUBSIDIZE_LSKNBZ_ADD";//临时困难补助申请

    public static final String MYSUBSIDIZE_MXF_LIST = "MYSUBSIDIZE_MXF_LIST";//免学费列表
    public static final String MYSUBSIDIZE_ZXDK_LIST = "MYSUBSIDIZE_ZXDK_LIST";//助学贷款列表
    public static final String MYSUBSIDIZE_ZXDK_ADD = "MYSUBSIDIZE_ZXDK_ADD";//助学贷款申请

    public static final String Teacher_evaluation_list = "Teacher_evaluation_list";//教师评教
    /**
     * 、、、、、、、、、、、、、、、、、企业信息模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String COMPANY_QYZP_LIST = "COMPANY_QYZP_LIST";//企业招聘
    public static final String COMPANY_JYXX_LIST = "COMPANY_JYXX_LIST";//就业信息
    public static final String COMPANY_YBMGW_LIST = "COMPANY_YBMGW_LIST";//已报名岗位数据
    public static final String COMPANY_SXJL_LIST = "COMPANY_SXJL_LIST";//实习记录数据
    public static final String COMPANY_GWBM_ADD = "COMPANY_GWBM_ADD";//岗位报名
    /**
     * 、、、、、、、、、、、、、、、、、图书管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String SCHOLLBOOKS_LIST = "SCHOLLBOOKS_LIST";//图书列表
    public static final String SCHOLLBOOKS_TYPES = "SCHOLLBOOKS_TYPES";//图书分类
    public static final String SCHOLLBOOKS_MYREADJL = "SCHOLLBOOKS_MYREADJL";//图书借阅记录
    /**
     * 、、、、、、、、、、、、、、、、、招生模块模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
     */
    public static final String ZS_STUDENT_SJY = "ZS_STUDENT_SJY";//招生数据源
    public static final String ZS_STUDENT_ADDSJY = "ZS_STUDENT_ADDSJY";//招生添加
    public static final String ZS_STUDENT_PDYQM = "ZS_STUDENT_PDYQM";//判断邀请码
    public static final String SCHOOLTHINGS_ZHUANYES = "SCHOOLTHINGS_ZHUANYES";//专业列表
    public static final String ZS_STUDENT_DYXW = "ZS_STUDENT_DYXW";//德育新闻

    private static HashMap<String, String> urlManager = new HashMap<String, String>();
    private static InterfaceManager manager;

    //		public static final String siteURLIP="http://192.168.1.176:8089";//测试
    public static final String siteURLIP = "http://ecampus.gipc.edu.cn:8081";//山西


    public static InterfaceManager getInstance() {
        if (manager == null) {
            manager = new InterfaceManager();
            /**
             * 、、、、、、、、、、、、、、、、、公共数据、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.ALL_TERME, siteURLIP + "/api/student/allterm");//所有学期
            /**
             * 、、、、、、、、、、、、、、、、、登录模块 、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.LUNTAN_BBS, siteURLIP + "/api/user/bbslogin");//论坛路劲
            urlManager.put(InterfaceManager.lOGIN_LOGIN, siteURLIP + "/api/user/student/login");//登录
            /**
             * 、、、、、、、、、、、、、、、、、个人主页模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.GRZY_JBXX, siteURLIP + "/api/student/info");//基本信息
            urlManager.put(InterfaceManager.GRZY_WDCJ, siteURLIP + "/api/xscj");//我的成绩
            urlManager.put(InterfaceManager.GRZY_TJXX, siteURLIP + "/api/student/phyexam");//体检
            urlManager.put(InterfaceManager.GRZY_TZXX, siteURLIP + "/api/student/phyinfo");//体质
            urlManager.put(InterfaceManager.GRZY_WDXXK, siteURLIP + "/api/student/xuanxiu");//我的选修课
/**
 * 、、、、、、、、、、、、、、、、、我的班级、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.MYCLASS_STUDENT_TXL, siteURLIP + "/api/student/txl");//学生通讯录
            urlManager.put(InterfaceManager.MYCLASS_CLASSTABLE, siteURLIP + "/api/student/kebiao");//教师课表
            urlManager.put(InterfaceManager.MYCLASS_CLASS_MSG, siteURLIP + "/api/student/jxbinfo");//班级信息
            urlManager.put(InterfaceManager.MYCLASS_CLASS_GG, siteURLIP + "/api/student/jxbnotice");//班级公告
            urlManager.put(InterfaceManager.MYCLASS_STUDENT_GG, siteURLIP + "/api/student/stunotice");//学生公告
            /**
             * 、、、、、、、、、、、、、、、、、我的评价模块模块 、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.MYEVALUATE_STUDENTJL, siteURLIP + "/api/student/reward");//学生奖励
            urlManager.put(InterfaceManager.MYEVALUATE_STUDENTCF, siteURLIP + "/api/student/punishment");//学生惩罚
            urlManager.put(InterfaceManager.MYEVALUATE_BYPY, siteURLIP + "/api/bypy");//毕业评语
            urlManager.put(InterfaceManager.MYEVALUATE_TERMPY, siteURLIP + "/api/xqpy");//学期评语

            urlManager.put(InterfaceManager.MYQJ_LIST, siteURLIP + "/api/student/stuleave");//学生请假列表
            urlManager.put(InterfaceManager.MYQJ_WANT, siteURLIP + "/api/student/addstuleave");//学生请假申请
            urlManager.put(InterfaceManager.MYQJ_WANT_TYPES, siteURLIP + "/api/bzr/qingjiatype");//学生请假类型


            /**
             * 、、、、、、、、、、、、、、、、、校内事宜模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.MYEVALUATE_STUDENT_JXHDLIST, siteURLIP + "/api/shouke/hudonglist");//教学互动列表
            urlManager.put(InterfaceManager.MYEVALUATE_STUDENT_KECHENG, siteURLIP + "/api/shouke/getkecheng");//获取课程
            urlManager.put(InterfaceManager.MYEVALUATE_CLASS_TEACJERS, siteURLIP + "/api/shouke/get_teachers");//获取课程教师
            urlManager.put(InterfaceManager.MYEVALUATE_JXHD_UPLODE, siteURLIP + "/api/shouke/tijiao");//提交教学互动问题
            urlManager.put(InterfaceManager.MYEVALUATE_JXHD_UPLODE_SFJJ, siteURLIP + "/api/shouke/if_jiejue");//提交教学互动问题
            urlManager.put(InterfaceManager.SCHOOLTHINGS_DYNEWS, siteURLIP + "/api/student/moraledunews");//德育新闻
            urlManager.put(InterfaceManager.SCHOOLTHINGS_SHILL_LIST, siteURLIP + "/api/student/skill");//技能大赛
            urlManager.put(InterfaceManager.SCHOOLTHINGS_SHILL_DETAILS, siteURLIP + "/api/student/skilldetails");//技能大赛详情
            urlManager.put(InterfaceManager.SCHOOLTHINGS_ZCWH_LIST, siteURLIP + "/api/repair/info");//资产列表接口
            urlManager.put(InterfaceManager.SCHOOLTHINGS_ADDZCWH, siteURLIP + "/api/repair/add");//添加资产报修接口

            /**
             * 、、、、、、、、、、、、、、、、、我的资助模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.MYSUBSIDIZE_JXJ_LIST, siteURLIP + "/api/student/scholarship");//奖学金列表
            urlManager.put(InterfaceManager.MYSUBSIDIZE_ZXJ_LIST, siteURLIP + "/api/student/grantinad");///助学金学金列表
            urlManager.put(InterfaceManager.MYSUBSIDIZE_ZXJTYPE_LIST, siteURLIP + "/api/student/getdictionary");///助学金类型列表
            urlManager.put(InterfaceManager.MYSUBSIDIZE_ZXJ_ADD, siteURLIP + "/api/student/addgrantinad");//助学金申请
            urlManager.put(InterfaceManager.MYSUBSIDIZE_LSKNBZ_LIST, siteURLIP + "/api/student/subsidy");//临时困难补助列表
            urlManager.put(InterfaceManager.MYSUBSIDIZE_LSKNBZ_ADD, siteURLIP + "/api/student/addsubsidy");//临时困难补助申请
            urlManager.put(InterfaceManager.MYSUBSIDIZE_MXF_LIST, siteURLIP + "/api/student/free");//免学费列表
            urlManager.put(InterfaceManager.MYSUBSIDIZE_ZXDK_LIST, siteURLIP + "/api/student/loan");//助学贷款列表
            urlManager.put(InterfaceManager.MYSUBSIDIZE_ZXDK_ADD, siteURLIP + "/api/student/addloan");//助学贷款申请

            urlManager.put(InterfaceManager.Teacher_evaluation_list, siteURLIP + "/api/pingjiao/getlist");//教师评教
            /**
             * 、、、、、、、、、、、、、、、、、企业信息模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.COMPANY_QYZP_LIST, siteURLIP + "/api/gangwei/info");//就业信息
            urlManager.put(InterfaceManager.COMPANY_JYXX_LIST, siteURLIP + "/api/jiuye/info");//就业信息
            urlManager.put(InterfaceManager.COMPANY_YBMGW_LIST, siteURLIP + "/api/zp/info");//已报名岗位
            urlManager.put(InterfaceManager.COMPANY_SXJL_LIST, siteURLIP + "/api/Intership/info");//实习记录
            urlManager.put(InterfaceManager.COMPANY_GWBM_ADD, siteURLIP + "/api/enter/info");//岗位报名

            /**
             * 、、、、、、、、、、、、、、、、、图书管理模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
             */
            urlManager.put(InterfaceManager.SCHOLLBOOKS_LIST, siteURLIP + "/api/book/getbook");//图书列表
            urlManager.put(InterfaceManager.SCHOLLBOOKS_TYPES, siteURLIP + "/api/book/getcategory");//图书分类
            urlManager.put(InterfaceManager.SCHOLLBOOKS_MYREADJL, siteURLIP + "/api/book/getjieyue");//图书借阅记录
/**
 * 、、、、、、、、、、、、、、、、、招生模块模块、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
 */
            urlManager.put(InterfaceManager.ZS_STUDENT_SJY, siteURLIP + "/api/zhaosheng");//下拉列表数据源
            urlManager.put(InterfaceManager.ZS_STUDENT_ADDSJY, siteURLIP + "/api/zhaosheng/add");//下拉列表数据源
            urlManager.put(InterfaceManager.ZS_STUDENT_PDYQM, siteURLIP + "/api/system/codeValid");//判断邀请码
            urlManager.put(InterfaceManager.ZS_STUDENT_DYXW, siteURLIP + "/api/moraledunews");//德育新闻

            urlManager.put(InterfaceManager.SCHOOLTHINGS_ZHUANYES, siteURLIP + "/api/zhuanyes");//专业列表

        }
        return manager;
    }

    /**
     * 获取路径
     *
     * @param name
     * @return
     */
    public String getURL(String name) {
        return urlManager.get(name);
    }
}
