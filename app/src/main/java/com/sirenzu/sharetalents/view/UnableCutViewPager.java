package com.sirenzu.sharetalents.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/4/30.
 * 不能切换的
 */

public class UnableCutViewPager extends ViewPager {

    public UnableCutViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public UnableCutViewPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, smoothScroll);
    }

    //切换不需要转换时间
    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, false);
    }
}