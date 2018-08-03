package com.htcompany.education.studentforgansu.commonpart;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatActivity;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.chatpart.DemoHelper;
import com.htcompany.education.studentforgansu.mainpart.entity.ClassTxlEntity;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wrb on 2016/10/8.
 */
public class MyApp extends MultiDexApplication {
    private static MyApp instance;
    public static Context context;
    public static Context applicationContext;
    public static List<TermEntity> termEntities = new ArrayList<TermEntity>();
    //通讯录集合
    public static List<ClassTxlEntity> contactsEntities= new ArrayList<ClassTxlEntity>();
    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        context = getApplicationContext();
        applicationContext=this;
        DemoHelper.getInstance().init(applicationContext);
        initImageLoader(getApplicationContext());

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    private List<AppCompatActivity> activityList = new ArrayList<AppCompatActivity>();
    /**
     * 返回imageloader实例化的对象
     * @return
     */
    public static MyApp getInstance() {
        if (null == instance) {
            instance = new MyApp();
        }
        return instance;
    }
    /**
     * 添加
     * @param activity
     */
    public void addActivity(AppCompatActivity activity) {
        activityList.add(activity);
    }
    //退出
    public void exit() {
        for (AppCompatActivity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 学期数据调用
     * @return
     */
    public static List<TermEntity> getTermEntities(){

        return termEntities;
    }

    //---------------------------------------imageloder---------------------------------------------------------
    private static DisplayImageOptions options;
    /**
     * 跟中情况对应的显示结果，例如找不到对应的网络资源时显示什么图片，等等
     * @return
     */
    public static DisplayImageOptions getOptions() {
        if (options == null) {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.bottombg_show_icon)
                    .showImageForEmptyUri(R.mipmap.bottombg_show_icon)
                    .showImageOnFail(R.mipmap.bottombg_show_icon)
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                    .cacheInMemory(true).cacheOnDisk(true)
                    .considerExifParams(true).build();
        }
        return options;
    }
    public static DisplayImageOptions getPhotoOptions() {
        if (options == null) {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.defult_photo_icon)
                    .showImageForEmptyUri(R.mipmap.defult_photo_icon)
                    .showImageOnFail(R.mipmap.defult_photo_icon)
                    .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                    .cacheInMemory(true).cacheOnDisk(true)
                    .considerExifParams(true).build();
        }
        return options;
    }
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .memoryCacheExtraOptions(480, 800)
                // default = device screen dimensions
                .threadPoolSize(3)
                // default
                .threadPriority(Thread.NORM_PRIORITY - 2)
                // default
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13)
                // default
                .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }
}
