package com.youtu.ipcclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.youtu.myapplication.IMyAidlInterface;
import com.youtu.myapplication.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface mAidl;
    private TextView mTvResult;
    private boolean mBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvResult = findViewById(R.id.tv_Result);

//        attemptToBindService();
        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addPerson();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void addPerson() throws RemoteException {
        //如果与服务端的连接处于未连接状态，则尝试连接
        if (!mBound) {
            attemptToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (null == mAidl) return;
        Person p = new Person("客户端张三");
        mAidl.addPerson(p);
        Log.e(getLocalClassName(), p.toString());
    }

    private void attemptToBindService() {
        Intent intent1 = new Intent();
        intent1.setAction("com.youtu.myapplication.AIDL_SERVICE");
        intent1.setPackage("com.youtu.myapplication");
//        ComponentName componentName = new ComponentName("com.youtu.myapplication.service"
//                , "com.youtu.myapplication.service.MyAidlService");
//        Intent intent2 = new Intent(createExplicitFromImplicitIntent(this, intent1));
//        intent1.setComponent(componentName);
//        startService(intent1);
        bindService(intent1, mConnection, BIND_AUTO_CREATE);
        Log.e("===","启动服务");
    }

    List<Person> personList = null;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("===","mBound = true;");
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            mAidl = IMyAidlInterface.Stub.asInterface(service);
            mBound = true;
            if (mAidl != null) {
                try {
                    personList = mAidl.getPersonList();
                    mTvResult.setText(personList.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("===","mBound = false;");
            mAidl = null;
            mBound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


    /**
     * 兼容Android5.0中service的intent一定要显性声明
     *
     * @param context
     * @param implicitIntent
     * @return
     */
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        //通过queryIntentActivities()方法，查询Android系统的所有具备ACTION_MAIN和CATEGORY_LAUNCHER
        //的Intent的应用程序，点击后，能启动该应用，说白了就是做一个类似Home程序的简易Launcher 。
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }


}
