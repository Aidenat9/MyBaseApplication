package com.youtu.myapplication.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/3 12:19
 *         包名：com.youtu.myapplication.demo
 *         <p>description:            </p>
 */

public class LocalService extends Service{


    public class LocalBinder extends Binder{
        public LocalService getService(){
            return LocalService.this;
        }
    }

    /**
     * 自定的IBinder
     */
    private LocalBinder mBinder = new LocalBinder();
    /**
     * 提供给客户端的方法
     *
     * @return
     */
    public String getServiceInfo() {
        return "----- " + this.getClass().getSimpleName();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent intent1 = new Intent(".demo.MyLocalReceiver");
        intent1.putExtra("str","传过来的字符串，测试");
        sendBroadcast(intent1);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
