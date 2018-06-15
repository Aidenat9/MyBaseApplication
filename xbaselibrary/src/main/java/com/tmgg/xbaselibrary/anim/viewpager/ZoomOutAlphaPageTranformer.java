package com.tmgg.xbaselibrary.anim.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/6/8 22:53
 *         包名：com.youtoo.publics
 *         <p>description:     缩放及透明度       </p>
 */

public class ZoomOutAlphaPageTranformer implements ViewPager.PageTransformer {


    private float MIN_SCALE = 0.8f;
    private static final float MIN_ALPHA = 0.7f;
    private float MAX_SCALE  =1f;

    public ZoomOutAlphaPageTranformer(float MIN_SCALE) {
        this.MIN_SCALE = MIN_SCALE;
    }

    public void transformPage(View view, float position) {

        if (position < -1) {
            position = -1;
            view.setAlpha(0);
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        //一个公式
        float scaleValue = MIN_SCALE + tempScale * slope;

        //设置缩放比例
        view.setScaleX(scaleValue);
        view.setScaleY(scaleValue);
        //设置透明度
//        view.setAlpha(scaleValue);
        float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
        view.setAlpha(MIN_ALPHA +
                (scaleFactor - MIN_SCALE) /
                        (1 - MIN_SCALE) * (1 - MIN_ALPHA));
    }

}
