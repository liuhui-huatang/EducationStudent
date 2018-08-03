package com.htcompany.education.studentforgansu.mainpart.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * 我的成绩滑动界面适配器
 * Created by wrb on 2016/10/20.
 */
public class MyExamViewpagerAdapter extends PagerAdapter{
    private List<View> viewLists;

    public MyExamViewpagerAdapter(List<View> lists) {
        viewLists = lists;
    }

    /**
     * 获得页面数量
     */
    @Override
    public int getCount() { // 获得页面数量
        // TODO Auto-generated method stub
        return viewLists.size();
    }

    /**
     * 这个方法我感觉应该是判断list存放的对象是否为View
     */
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    /**
     * 销毁Item
     */
    @Override
    public void destroyItem(View view, int position, Object object) {
        if (position < viewLists.size()) {
            ((ViewPager) view).removeView(viewLists.get(position));
        }
    }

    /**
     * 网上找的这些注释，看看吧
     *
     * 该方法声明了返回值不一定是view，可以是任意对象。要知道view的添加是在该方法内部，通过container来添加的，
     * 所以这个方法不一定要返回view。
     *
     * 而isViewFromObject方法是用来判断pager的一个view是否和instantiateItem方法返回的object有关联，
     * 如果有关联做什么呢？去看代码吧
     * ViewPager源码，你去看下addNewItem方法，会找到instantiateItem的使用方法，注意这里的mItems变量
     * 。然后你再搜索下isViewFromObject
     * ，会发现其被infoForChild方法调用，返回值是ItemInfo。再去看下ItemInfo的结构
     * ，其中有一个object对象，该值就是instantiateItem返回的。
     *
     * 也就是说，ViewPager里面用了一个mItems(ArrayList)来存储每个page的信息(ItemInfo)，当界面要展示或者发生变化时
     * ，需要依据page的当前信息来调整，但此时只能通过view来查找，
     * 所以只能遍历mItems通过比较view和object来找到对应的ItemInfo。
     */
    @Override
    public Object instantiateItem(View view, int position) // 实例化Item
    {
        ((ViewPager) view).addView(viewLists.get(position), 0);

        return viewLists.get(position);
    }
}
