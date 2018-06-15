package com.tmgg.xbaselibrary.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tmgg.xbaselibrary.mvp.IBaseView;
import com.tmgg.xbaselibrary.utils.AppManger;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/6/14 11:01
 *         包名：com.tmgg.xbaselibrary.ui
 *         <p>description:            </p>
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView{


    //未指定类型的Presenter
    //初始化Presenter

    protected boolean isTrans;
    private BaseActivity mContext;

    protected abstract void initPresenter();
    //设置布局
    protected abstract int getLayout();
    //处理逻辑
    protected abstract void processLogic();
    //初始化布局
    protected abstract void initView();
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        AppManger.getAppManager().addActivity(this);

        setContentView(getLayout());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initStatusBar(true);
        }
        initView();
        initPresenter();
        processLogic();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initStatusBar(boolean isTransparent) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if(isTransparent){
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }else{
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        isTrans = isTransparent;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManger.getAppManager().finishActivity(this);
    }
    public void exitApp(){
        AppManger.getAppManager().AppExit(this);
    }



    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }
    @Override
    public void hideLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showErr(String s) {
        showToast(s);
    }

    @Override
    public void showLoading(String msg) {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(msg);
            mProgressDialog.show();
        }
    }

    @Override
    public void showNetErr() {
        showToast("网络异常，请检查网络设置");
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
