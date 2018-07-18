package com.youtu.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/17 16:39
 *         包名：com.youtu.myapplication.widget
 *         <p>description:  随着列表滚动 根据传入的值移动然后显示图片          </p>
 */

public class AdImageView extends AppCompatImageView{

    private int mDy;
    private int mMinDy;

    public AdImageView(Context context) {
        super(context);
    }

    public AdImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        int w = getWidth();
        int h = (int) (getWidth() * 1.0f / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, w, h);
        canvas.save();
        canvas.translate(0, -getDy());
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMinDy = h;
    }

    public void setDy(int dy){
        if(getDrawable()==null)return;
        mDy = dy -mMinDy;
        if (mDy <= 0) {
            mDy = 0;
        }
        if(mDy>getDrawable().getBounds().height()-mMinDy){
            mDy = getDrawable().getBounds().height() - mMinDy;
        }
        invalidate();
    }
    public int getDy() {
        return mDy;
    }


}
