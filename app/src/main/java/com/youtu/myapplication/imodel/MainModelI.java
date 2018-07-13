package com.youtu.myapplication.imodel;

import com.lzy.okgo.callback.Callback;
import com.tmgg.xbaselibrary.mvp.IBaseModel;
import com.tmgg.xbaselibrary.utils.okgoconfig.MyHttpRequest;
import com.tmgg.xbaselibrary.utils.okgoconfig.callback.JsonCallback;
import com.tmgg.xbaselibrary.utils.okgoconfig.model.LzyResponse;
import com.youtu.myapplication.okgoconfig.model.XiaoHuaModel;

import java.util.Map;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/6/14 17:36
 *         包名：com.youtu.myapplication.imodel
 *         <p>description:            </p>
 */

public class MainModelI extends IBaseModel<LzyResponse<XiaoHuaModel>> {

    @Override
    public void getNet(String url, Object tag, Map<String, String> map, Callback<LzyResponse<XiaoHuaModel>> callback) {
        MyHttpRequest.getRequest(url,this,null, (JsonCallback)callback);
    }
}
