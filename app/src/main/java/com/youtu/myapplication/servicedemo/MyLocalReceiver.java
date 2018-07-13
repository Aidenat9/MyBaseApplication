package com.youtu.myapplication.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tmgg.xbaselibrary.utils.StringUtils;
import com.tmgg.xbaselibrary.utils.klogutil.KLog;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/3 15:12
 *         包名：com.youtu.myapplication.demo
 *         <p>description:            </p>
 */

public class MyLocalReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        String str = intent.getStringExtra("str");
        if(StringUtils.isEmpty(str))return;
        else KLog.i(str);
    }
}
