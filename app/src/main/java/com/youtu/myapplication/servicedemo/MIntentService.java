package com.youtu.myapplication.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.tmgg.xbaselibrary.utils.klogutil.KLog;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/3 15:30
 *         包名：com.youtu.myapplication.demo
 *         <p>description:   IntentService          </p>
 */

public class MIntentService extends IntentService {

    private int count;
    private boolean isRunning;

    public MIntentService() {
        super("MIntentService");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        KLog.i("IntentService----onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        KLog.i("IntentService----onDestroy");
    }

    /**
     * 里面有一个onHandleIntent(Intent intent)抽象方法，这个方法是在非UI线程调用的，在这里执行耗时的操作。
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        try {
            Thread.sleep(1000);
            count = 1;
            isRunning = true;
            while (isRunning){
                count++;
                if(count>10){
                    isRunning =false ;
                }
                Thread.sleep(50);
                sendThreadStatus("线程运行中...", count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendThreadStatus(String s, int count) {
        Intent intent = new Intent(".demo.MyLocalReceiver");
        intent.putExtra("str","从intentService里传的数据"+count);
        sendBroadcast(intent);
    }
}
