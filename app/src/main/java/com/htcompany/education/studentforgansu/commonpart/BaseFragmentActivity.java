package com.htcompany.education.studentforgansu.commonpart;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.tools.SystemBarTintManager;

/**
 * Created by Administrator on 2016/10/26.
 */
public class BaseFragmentActivity extends FragmentActivity{
    public SystemBarTintManager tintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    /**
     * 加入浸入式
     */
    private void init(){
        //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tintManager = new SystemBarTintManager(this);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            // 激活导航栏设置
            tintManager.setNavigationBarTintEnabled(false);
            // 设置一个颜色给系统栏
//            tintManager.setTintColor(getResources().getColor(R.color.title_bar_color));
            tintManager.setTintDrawable(getResources().getDrawable(R.mipmap.titlebar_status_bg));
        }
    }
}
