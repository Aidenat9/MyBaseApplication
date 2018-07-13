package com.youtu.myapplication.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.youtu.myapplication.R;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/3 11:38
 *         包名：com.youtu.myapplication.demo
 *         <p>description:            </p>
 */

/**
* 1如果组件仅通过startService()启动服务，不论服务是否已经启动，都会回调onStartCommand()方法，而且服务会一直运行，
 * 需要调用stopSelf和stopService方法关闭服务。

2 如果组件仅通过bindService()绑定服务，则服务只有在与组件绑定时候运行，一旦所有的客户端全部取消绑定unbindService，
 系统才会销毁该服务。

 3多次启动同一个服务，只有在服务初次启动时候会回调onCreate方法，但是每次都会回调onStartCommand，
 可以利用这个向服务传递一些信息。
*/
public class MyService extends Service{

    private MyBinder binder = new MyBinder();

    public  class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    /**
    * onCreate()，在创建服务时候，可以在这个方法中执行一些的初始化操作，它在onStartCommand()
     * 和onBind()之前被调用。如果服务已经存在，调用startService()启动服务时候这个方法不会调用，
     * 只会调用onStartCommand()方法
    */
    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
    * onStartCommand()，其他组件通过startService()启动服务时候会回调这个方法，这个方法执行后，
     * 服务会启动被在后台运行，需要调用stopSelf()或者stopService()停止服务。
     *
    */
    /**
     * onStartCommand()方法有一个int的返回值，这个返回值标识服务关闭后系统的后续操作。
     *
    * 1Service.START_STICKY，启动后的服务被杀死，系统会自动重建服务并调用on onStartCommand()，
     * 但是不会传入最后一个Intent(Service可能多次执行onStartCommand)，会传入一个空的Intent，
     * 使用这个标记要注意对Intent的判空处理。这个标记适用于太依靠外界数据Intent，在特定的时间，有明确的启动和关闭的服务，例如后台运行的音乐播放。
     2Service.START_NOT_STICKY，启动后的服务被杀死，系统不会自动重新创建服务。这个标记是最安全的，
     适用于依赖外界数据Intent的服务，需要完全执行的服务。
     3Service.START_REDELIVER_INTENT，启动后的服务被杀死，系统会重新创建服务并调用onStartCommand()，
     同时会传入最后一个Intent。这个标记适用于可恢复继续执行的任务，比如说下载文件。
     4Service.START_STICKY_COMPATIBILITY，启动后的服务被杀死，不能保证系统一定会重新创建Service。
    */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Notification notification = new Notification(R.drawable.ic_launcher_background, "通知测试", System.currentTimeMillis());
        Intent notifyIntent = new Intent(this, ServiceDemoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notifyIntent,0);
        startForeground(1,notification);


        return super.onStartCommand(intent, flags, startId);

    }

    /**
    * onBind()，其他组件通过bindService()绑定服务时候会回调的方法，这是Service的一个抽象方法，
     * 如果客户端需要与服务交互，需要在这个方法中返回一个IBinder实现类实例化对象，
     * 如果不想其他客户端与服务绑定，直接返回null。
    */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
    * onDestroy()，当服务不在还是用且即将被销毁时，会回调这个方法，可以在这个方法中做
     * 一些释放资源操作，这是服务生命周期的最后一个回调。
    */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
