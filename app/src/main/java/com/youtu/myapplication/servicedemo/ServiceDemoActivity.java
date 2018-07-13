package com.youtu.myapplication.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.tmgg.xbaselibrary.ui.BaseActivity;
import com.tmgg.xbaselibrary.utils.klogutil.KLog;
import com.youtu.myapplication.R;

public class ServiceDemoActivity extends BaseActivity {


    private LocalService mService;
    private boolean mBound = false;

    @Override
    protected void initPresenter() {
//        service_demo();
        intentService_demo();
        IntentFilter intentFilter = new IntentFilter(".demo.MyLocalReceiver");
        registerReceiver(new MyLocalReceiver(),intentFilter);
    }

    private void intentService_demo() {
        Intent intent = new Intent(mContext, MIntentService.class);
        startService(intent);
    }

    private void service_demo() {
        Intent intent = new Intent(mContext, LocalService.class);
        //        bindService(intent,conn, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_service_demo;
    }

    @Override
    protected void onStart() {
        super.onStart();
        KLog.i("onstart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        KLog.i("onStop");
        if (mBound) {
            unbindService(conn);
            mBound = false;
        }
    }

    public ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalService.LocalBinder localBinder = (LocalService.LocalBinder) iBinder;
            mService = localBinder.getService();
            mBound = true;
            KLog.i("onServiceConnected ");

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
            KLog.i("onServiceDisconnected ");

        }
    };


    @Override
    protected void processLogic() {
        findViewById(R.id.btn_show).setOnClickListener(v -> {
            KLog.i("click " + mBound);
            if (mBound) {
                String info = mService.getServiceInfo();
                KLog.i(info);
            }
        });
    }

    @Override
    protected void initView() {

    }


    @Override
    public void showErrMsg(String errMsg) {

    }
}
