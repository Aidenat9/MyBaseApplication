package com.youtu.myapplication.presenter;

import android.app.Activity;
import android.util.Log;

import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.tmgg.xbaselibrary.mvp.BasePresenter;
import com.tmgg.xbaselibrary.utils.okgoconfig.callback.JsonCallback;
import com.tmgg.xbaselibrary.utils.okgoconfig.model.LzyResponse;
import com.youtu.myapplication.imodel.MainModelI;
import com.youtu.myapplication.iview.MainView;
import com.youtu.myapplication.okgoconfig.model.XiaoHuaModel;

import java.util.List;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/6/14 17:34
 *         包名：com.youtu.myapplication.presenter
 *         <p>description:            </p>
 */

public class MainPresenter extends BasePresenter<MainView> {

    private final MainModelI mainModel;

    /**
     * 初始化，默认attachview
     *
     * @param context
     * @param mvpView
     */
    public MainPresenter(Activity context, MainView mvpView) {
        super(context, mvpView);
        mainModel = new MainModelI();
    }

    /**
     * 初始化，默认attachview
     */

    public void getNetData(String url){

        mainModel.getNet(url, mContext, null, new JsonCallback<LzyResponse<XiaoHuaModel>>() {
            @Override
            public void onStart(Request<LzyResponse<XiaoHuaModel>, ? extends Request> request) {
                super.onStart(request);
                mvpView.showLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mvpView.hideLoading();
            }

            @Override
            public void onError(Response<LzyResponse<XiaoHuaModel>> response) {
                super.onError(response);
                mvpView.showData(response.getException().getMessage());
            }

            @Override
            public void onSuccess(Response<LzyResponse<XiaoHuaModel>> response) {
                XiaoHuaModel result = response.body().result;
                List<XiaoHuaModel.Content> data = result.list;
                for (XiaoHuaModel.Content content :
                        data) {
                    Log.e("tag", "onSuccess: "+content.source );
                    mvpView.showData(content.title+"---"+content.source);
                }
            }
        });

    }
}
