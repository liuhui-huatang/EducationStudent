package com.htcompany.education.studentforgansu.commonpart.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.widget.EditText;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.MyApp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 公共工具
 * @author WRB
 * @createtime2105/9/26
 */
public class BaseUtils {
	/**
	 * 判断是否是正确手机号
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobiles(String mobiles) {
		Pattern p = Pattern
				.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 判断字符串
	 * @param str
	 * @return
	 */
	public static boolean isString(String str){
		if(!"".equals(str)&&str!=null&&!"null".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断手机号是联通 、移动、电信
	 * 
	 * @param mobiles
	 * @return
	 */
	public static int isYYSMobiles(String mobiles) {
		String gsm = "^((13[4-9])|(147)|(15[0-2,7-9])|(18[2-3,7-8]))\\d{8}$";
		String wcdma = "^((13[0-2])|(145)|(15[5-6])|(186))\\d{8}$";
		String cdma = "^((133)|(153)|(18[0,9]))\\d{8}$";
		int flag = 0;
		if (mobiles.matches(wcdma)) {
		flag = 1;
		System.out.println("联通号码");
		} else if (mobiles.matches(gsm)) {
		flag = 2;
		System.out.println("移动号码");
		} else if (mobiles.matches(cdma)) {
		flag = 3;
		System.out.println("电信号码");
		} else {
		flag = 4;
		System.out.println("号码有误");
		}
		return flag;
	}
	/**
	 * 判断是否是正确身份证
	 * 
	 * @param idcard
	 * @return
	 */
	public static boolean isSFZCards(String idcard) {
		Pattern p = Pattern
				.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])(\\d{4}|\\d{3}[x|X])$");
		Matcher m = p.matcher(idcard);
		return m.matches();
	}
	/**
	 * 判断字符串
	 * @param str
	 * @return
	 */
	public static boolean isStringNull(String str){
		if(!"".equals(str)&&str!=null&&!"null".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断Editext是否为null
	 * @param editText
	 * @return
     */
	public static boolean isNullEditText(EditText editText) {
		if (editText != null) {
			if (editText.getText()!= null && editText.getText().length() > 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}

	}
	
	/**
	 * 判断账号是否符合 6-8位字母加数字
	 * @param account
	 * @return
	 */
	public static boolean isAccounts(String account){
		Pattern p = Pattern
				.compile("^(?![0-9])[a-zA-Z0-9_]{6,8}$");
		Matcher m = p.matcher(account);
		return m.matches();
	}
	
	
	/**
	 * 只能输入数字
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean onlynumber(String mobiles) {
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 判断是否网络链接正常
	 * 
	 * @param context
	 * @return true or false
	 * @author: WRB
	 * @Createtime: 2015/9/26
	 */
	public static boolean isNetworkAvailable(Context context) {
		boolean flag = false;
		ConnectivityManager localConnectivityManager = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (localConnectivityManager != null) {
			try {
				NetworkInfo localNetworkInfo = localConnectivityManager
						.getActiveNetworkInfo();
				if ((localNetworkInfo == null)
						|| (!localNetworkInfo.isAvailable())) {
					flag = false;
				} else {
					flag = true;
				}
			} catch (Exception e) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 判断是否存在SD�?
	 * 
	 * @author WRB
	 * @return true or false
	 */
	public static boolean hasSDCard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
	public static String getTime(Date date) {//可根据需要自行截取数据显示
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static String getSystemTime(){
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate =  new Date(System.currentTimeMillis());
		String   str   =   dateFormat.format(curDate);
		return str;
	}
	/**
	 * 比较两个日期
	 * @param s1
	 * @param s2
	 * @throws Exception
	 */
	public static boolean DateCompare(String s1, String s2){
		try {
			//设定时间的模板
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//得到指定模范的时间
			Date d1 = sdf.parse(s1);

			Date d2 = sdf.parse(s2);
			long time = ((d1.getTime() - d2.getTime())/(24*3600*1000));
			//比较
			if( d1.getTime()>d2.getTime()) {
				System.out.println("大于三天");
				return true;
			}else{
				System.out.println("小于三天");
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 讲毫秒字符串转换为日期时间格式字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String letStringToDate(String str) {
		SimpleDateFormat dataDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		long times = Long.valueOf(str);
		Date datas = new Date(times);
		String timeStr = dataDateFormat.format(datas);
		return timeStr;
	}
	/**
	 * 将Unix时间转换为普通�?时间格式
	 * @param timestampString
	 * @return
	 */
	public static String TimeStamp2Date(String timestampString){
		  Long timestamp = Long.parseLong(timestampString)*1000;
		  String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
		  return date;  
		}

	/**
	 * 获取系统当前日期
	 * @return
     */
	public static String getSystemtData() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = df.format(curDate);
		return str;
	}

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

    //二进
    public static String getBitmapByte(Bitmap bitmap){
    	   ByteArrayOutputStream out = new ByteArrayOutputStream();
    	   bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
    	   try {  
    	       out.flush();  
    	       out.close();  
    	   } catch (IOException e) {
    	       e.printStackTrace();  
    	   }  
    	   return out.toByteArray().toString();  
    	}  

    /**
     * 退出程序清空数据
     * @param context
     */
    public static void exitApp(Context context){
    	 SharedPrefUtil sharedPrefUtil= new SharedPrefUtil(context, "login");
    	 sharedPrefUtil.clear();
    	 sharedPrefUtil.putString("isExitOne", "1");
		 sharedPrefUtil.commit();
    	 MyApp.getInstance().exit();
    }
    /**
     * 判断是否安装微信
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }
    /**
     * 判断qq是否可用
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 系统提示音播放
     * @param context
     */
    public static void soundRing(Context context) {
  	  
        MediaPlayer mp = new MediaPlayer();
        mp.reset();  
        try {
			mp.setDataSource(context,  
			        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
			mp.prepare();  
	        mp.start();  
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    }  
    /**
     * 比较两个日期
     * @param s1
     * @param s2
     * @throws Exception
     */
    public static void Date2Compare(String s1, String s2) throws Exception {
		//设定时间的模板
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//得到指定模范的时间
		Date d1 = sdf.parse(s1);
		Date d2 = sdf.parse(s2);
		//比较
		if(Math.abs(((d1.getTime() - d2.getTime())/(24*3600*1000))) >=3) {
			System.out.println("大于三天");
		}else{
			System.out.println("小于三天");
		}
	}
    /**
     * 判断网络是否连接 true 连接 ；false 断开
     * @param context
     * @return
     */
	public static boolean InternetStatus(Context context){
		ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {// unconnect network
			return false;
		}else {// connect network
			return true;
		}
	}

	/**
	 * 判断手机系统版本
	 * @return
     */
	public static int getAndroidSDKVersion() {
		int version=0;
		try {
			version = Integer.valueOf(android.os.Build.VERSION.SDK);
		} catch (NumberFormatException e) {

		}
		return version;
	}
	public static int dip2px(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}
	public static int px2dip(Context context, float pxValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue / scale + 0.5f);
	}

	//获取当天课程背景
	public static List<Integer> getBackgrounImage(){
		List<Integer> datas = new ArrayList<Integer>();
		datas.add(R.mipmap.home_bg1);
		datas.add(R.mipmap.home_bg2);
		datas.add(R.mipmap.home_bg3);
		datas.add(R.mipmap.home_bg5);
		datas.add(R.mipmap.home_bg4);
		return datas;
	}
	//获取课程图标
	public static List<Integer> getClassIconImage() {
		List<Integer> datas = new ArrayList<Integer>();
		datas.add(R.mipmap.home_dq_icon1);
		datas.add(R.mipmap.home_dq_icon2);
		datas.add(R.mipmap.home_dq_icon3);
		datas.add(R.mipmap.home_dq_icon4);
		datas.add(R.mipmap.home_dq_icon5);
		return datas;
	}
	//获取颜色字体
	public static List<Integer> getClassColor() {
		List<Integer> datas = new ArrayList<Integer>();
		datas.add(R.color.todayclass_t1_color);
		datas.add(R.color.todayclass_t2_color);
		datas.add(R.color.todayclass_t3_color);
		datas.add(R.color.todayclass_t4_color);
		datas.add(R.color.todayclass_t5_color);
		return datas;
	}
	//获取颜色字体阴影
	public static List<Integer> getClassYYColor() {
		List<Integer> datas = new ArrayList<Integer>();
		datas.add(R.color.todayclass_yt1_color);
		datas.add(R.color.todayclass_yt2_color);
		datas.add(R.color.todayclass_yt3_color);
		datas.add(R.color.todayclass_yt4_color);
		datas.add(R.color.todayclass_yt5_color);
		return datas;
	}
	//获取学生奖惩图标
	public static List<Integer> getRwardsIconImage() {
		List<Integer> datas = new ArrayList<Integer>();
		datas.add(R.mipmap.dj_icon_trophy_01);
		datas.add(R.mipmap.jiangc_icon1);
		datas.add(R.mipmap.jiangc_icon2);
		return datas;
	}
	//获取班级公告图标
	public static List<Integer> getClassnoticeIconImage() {
		List<Integer> datas = new ArrayList<Integer>();
		datas.add(R.mipmap.classnotice_icon1);
		datas.add(R.mipmap.classnotice_icon2);
		datas.add(R.mipmap.classnotice_icon3);
		return datas;
	}
}
