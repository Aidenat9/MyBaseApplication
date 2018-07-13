package com.youtu.myapplication.animdemo;

import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.tmgg.xbaselibrary.ui.BaseActivity;
import com.youtu.myapplication.R;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         <p>description:  属性动画demo页         </p>
 */
public class AnimDemoActivity extends BaseActivity {


    private ImageView mImg1;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_anim_demo;
    }

    @Override
    protected void processLogic() {
        mImg1.setOnClickListener(v -> {
            processAnimator();
        });
    }

    private void processAnimator() {
        /**
         * 1.ObjectAnimator：继承自ValueAnimator，允许你指定要进行动画的对象以及该对象的一个属性。该类会根据计算得到
         * 的新值自动更新属性。大多数的情况使用ObjectAnimator就足够了
         */
//        ObjectAnimator.ofFloat(mImg1,"rotationX",0.0f,360.0f).setDuration(3600).start();
//        ObjectAnimator.ofFloat(mImg1,"rotationY",0.0f,360.0f).setDuration(3600).start();
        /**
         * 2.PropertyValuesHolder：多属性动画同时工作管理类。有时候我们需要同时修改多个属性，那就可以用到此类
         */
//        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.2f, 1.0f);
//        PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationX", 0, 300);
//        ObjectAnimator.ofPropertyValuesHolder(mImg1,alpha,translationY).setDuration(4000).start();
        /**
         * 3.ValueAnimator：属性动画中的时间驱动，管理着动画时间的开始、结束属性值，相应时间属性值计算方法等。包含
         * 所有计算动画值的核心函数以及每一个动画时间节点上的信息、一个动画是否重复、是否监听更新事件等，并且还可以
         * 设置自定义的计算类型。
         * todo :特别注意：ValueAnimator只是动画计算管理驱动，设置了作用目标，但没有设置属性，
         *  需要通过updateListener里设置属性才会生效。
         *
         *  大眼看上去可以发现和ObjectAnimator没啥区别，实际上正是由于ValueAnimator不直接操作属性值，所以要
         *  操作对象的属性可以不需要setXXX与getXXX方法，你完全可以通过当前动画的计算去修改任何属性。
         */
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
//        valueAnimator.setTarget(mImg1);
//        valueAnimator.setDuration(4000).start();
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float animatedValue = (float) animation.getAnimatedValue();
//                mImg1.setTranslationX(animatedValue);
//                mImg1.setTranslationY(animatedValue);//必须通过这里设置属性值才有效
////                view.mXXX = value;  //不需要setXXX属性方法
//            }
//        });
        /**
         * 4、AnimationSet：动画集合，提供把多个动画组合成一个组合的机制，并可设置动画的时序关系，
         * 如同时播放、顺序播放或延迟播放。具体使用方法比较简单，如下：
         */
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mImg1, "alpha", 0, 1.0f);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(mImg1, "translationY", 0f, 120f);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.setDuration(3800);
        set.playTogether(alphaAnim, translationY);
//        set.play(alphaAnim).after(translationY);
        set.start();
        /**
         * 5、Evaluators相关类解释： Evaluators就是属性动画系统如何去计算一个属性值。
         * 它们通过Animator提供的动画的起始和结束值去计算一个动画的属性值。
         */
        /**
         * 6.6、Interpolators相关类解释：

         AccelerateDecelerateInterolator：先加速后减速。

         AccelerateInterpolator：加速。

         DecelerateInterpolator：减速。

         AnticipateInterpolator：先向相反方向改变一段再加速播放。

         AnticipateOvershootInterpolator：先向相反方向改变，再加速播放，会超出目标值然后缓慢移动至目标值，类似于弹簧回弹。

         BounceInterpolator：快到目标值时值会跳跃。

         CycleIinterpolator：动画循环一定次数，值的改变为一正弦函数：Math.sin(2 * mCycles * Math.PI * input)。

         LinearInterpolator：线性均匀改变。

         OvershottInterpolator：最后超出目标值然后缓慢改变到目标值。

         TimeInterpolator：一个允许自定义Interpolator的接口，以上都实现了该接口。
         */
        // TODO: 2018/7/5
        /**
        * viewgroup的动画
        */
//        LayoutTransition mTransitioner = new LayoutTransition();
//        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "scaleX", 0, 1);
//        //设置更多动画
//        mTransitioner.setAnimator(LayoutTransition.APPEARING, anim);
//        //设置更多类型的动画
//        mViewGroup.setLayoutTransition(mTransitioner);


    }

    @Override
    protected void initView() {
        mImg1 = (ImageView) findViewById(R.id.img_1);
    }

    /**
     * 动画在这里可以执行，不能在oncreate里执行
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        processAnimator();
    }

    @Override
    public void showErrMsg(String errMsg) {

    }
}
