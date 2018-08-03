package com.htcompany.education.studentforgansu.commonpart.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by Administrator on 2016/10/19.
 */
public class MyExpandablelistview extends ExpandableListView{
    public MyExpandablelistview(Context context) {
        super(context);
    }

    public MyExpandablelistview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyExpandablelistview(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
