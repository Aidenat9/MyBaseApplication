package com.tmgg.xbaselibrary.anim.viewpager;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;
@SuppressLint("NewApi")
public class RotateDownPageTransformer implements PageTransformer {
    private static final float MAX_ROTATE = 20f;
    
    private float mRoate;
    
	public void transformPage(View view, float position) {
		if (position < -2) {
			return;
		}
		int pageWidth = view.getWidth();
        if (position < -1) { 
            ViewHelper.setRotation(view, 0);
        } else if (position <= 0) { // [-1,0]
        	//A
        	mRoate = position*MAX_ROTATE;
        	ViewHelper.setPivotX(view, pageWidth/2);
        	ViewHelper.setPivotY(view, view.getMeasuredHeight());
        	ViewHelper.setRotation(view, mRoate);
        } else if (position <= 1) { // (0,1]
            //B
        	mRoate = position*MAX_ROTATE;
        	ViewHelper.setPivotX(view, pageWidth/2);
        	ViewHelper.setPivotX(view, view.getMeasuredHeight());
        	ViewHelper.setRotation(view, mRoate);
        } else { 
        	ViewHelper.setRotation(view, 0);
        }
    }
}