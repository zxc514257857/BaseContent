package com.zhr.bottomnavigationbar.test2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 不能左右滑动的ViewPager
 */
public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context mContext) {
        super(mContext);
    }

    public NoScrollViewPager(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
    }

    private boolean noScroll = false;

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (noScroll) {
            return false;
        } else {
            return super.onTouchEvent(arg0);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll) {
            return false;
        } else {
            return super.onInterceptTouchEvent(arg0);
        }
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

}