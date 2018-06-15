package com.tmgg.xbaselibrary.mvp;

import android.app.Activity;

public class BasePresenter<V extends IBaseView> {
    /**
     * 绑定的view
     */
    protected V mvpView;
    protected Activity mContext;

    /**
    * 初始化，默认attachview
    */
    public BasePresenter(Activity context , V mvpView) {
        mContext = context;
        attachView(mvpView);
    }

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 断开view，一般在onDestroy中调用
     */
    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mvpView != null;
    }

    /**
     * 获取连接的view
     */
    public V getView() {
        return mvpView;
    }
}