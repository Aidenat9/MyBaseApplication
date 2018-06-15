package com.tmgg.xbaselibrary;

import android.app.Application;

import com.tmgg.xbaselibrary.utils.okgoconfig.OkGoConfig;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/6/14 15:22
 *         包名：com.tmgg.xbaselibrary
 *         <p>description:            </p>
 */

public class BaseApp extends Application{
    public static BaseApp mInstance ;
    @Override
    public void onCreate() {
        super.onCreate();
        //这2局要在自己的app里设置最好
        OkGoConfig.init(this);
        mInstance = this;
    }
}
