package com.tmgg.xbaselibrary.anim.viewpager;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;


@SuppressLint("NewApi")
public class AlphaPageTransformer implements PageTransformer {
	public void transformPage(View view, float position) {
		if (position <= -1) {
			return;
		}
		if (position < -1) { // [-Infinity,-1)
			ViewHelper.setAlpha(view, 0);
		} else if (position <= 0) { // [-1,0]
			// Use the default slide transition when moving to the left page
			ViewHelper.setAlpha(view, 1+position/2);
		} else if (position <= 1) { // (0,1]
			// Fade the page out.
			ViewHelper.setAlpha(view, 1 - position/2);
		} else { // (1,+Infinity]
			// This page is way off-screen to the right.
			ViewHelper.setAlpha(view, 0);
		}
	}
}