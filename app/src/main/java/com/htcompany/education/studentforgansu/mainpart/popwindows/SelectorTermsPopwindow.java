package com.htcompany.education.studentforgansu.mainpart.popwindows;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.htcompany.education.studentforgansu.R;
import com.htcompany.education.studentforgansu.commonpart.MyApp;
import com.htcompany.education.studentforgansu.mainpart.adapter.SelectorTermsPopwindowAdapter;
import com.htcompany.education.studentforgansu.mainpart.entity.TermEntity;

/**
 * 选择学期popwindow
 * Created by wrb on 2016/10/21.
 */
public class SelectorTermsPopwindow extends PopupWindow{
    private Context context;
    private LayoutInflater inflater;
    private WindowManager windowManager;
    private View view;
    private int showKd;
    private ListView selectorterm_lv;
    private Handler myHandler;
    private SelectorTermsPopwindowAdapter termsPopwindowAdapter;
    public SelectorTermsPopwindow(Context context,int showKd,Handler myHandler){
        this.context = context;
        this.myHandler = myHandler;
        inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.select_terms_popwindow,null);
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        initViews();
        int h = windowManager.getDefaultDisplay().getHeight();
        int w = windowManager.getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
//        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
    }

    public void initViews(){
        selectorterm_lv = (ListView)view.findViewById(R.id.selectorterm_lv);
        termsPopwindowAdapter = new SelectorTermsPopwindowAdapter(context, MyApp.termEntities);
        selectorterm_lv.setAdapter(termsPopwindowAdapter);
        selectorterm_lv.setOnItemClickListener(termClcier);
    }
    public AdapterView.OnItemClickListener termClcier = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TermEntity entity = (TermEntity) termsPopwindowAdapter.getItem(position);
            Message message = new Message();
            message.what=1000;
            Bundle bundle = new Bundle();
            bundle.putSerializable("termentity",entity);
            message.setData(bundle);
            myHandler.sendMessage(message);
        }
    };
    /**
     * 从底部展
     * @param parent
     */
    public void showPopupWindowBottom(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(
                    parent,
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else {
            this.dismiss();
        }
    }
    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
//            this.showAsDropDown(parent,0, 0);
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }
}

