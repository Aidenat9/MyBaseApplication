package com.tmgg.xbaselibrary.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmgg.xbaselibrary.mvp.IBaseView;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/6/14 11:14
 *         包名：com.tmgg.xbaselibrary.ui
 *         <p>description:            </p>
 */

public abstract class BaseFragment extends Fragment implements IBaseView{

    //初始化Presenter
    protected abstract void initPresenter();

    protected AppCompatActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);    //处理逻辑
    protected abstract void processLogic();
    //获取布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected AppCompatActivity getHoldingActivity() {
        return mActivity;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (AppCompatActivity) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        initPresenter();
        processLogic();
        return view;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        ((BaseActivity) mActivity).showLoading();
    }
    @Override
    public void showLoading(String msg) {
        checkActivityAttached();
        ((BaseActivity) mActivity).showLoading(msg);
    }

    @Override
    public void hideLoading() {
        checkActivityAttached();
        ((BaseActivity) mActivity).hideLoading();
    }

    @Override
    public void showToast(String msg) {
        checkActivityAttached();
        ((BaseActivity) mActivity).showToast(msg);
    }
    @Override
    public void showErr(String s) {
        checkActivityAttached();
        ((BaseActivity) mActivity).showToast(s);
    }

    @Override
    public void showNetErr() {
        checkActivityAttached();
        ((BaseActivity) mActivity).showNetErr();
    }

    protected boolean isAttachedContext(){
        return getActivity() != null;
    }
    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }
    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }
}
