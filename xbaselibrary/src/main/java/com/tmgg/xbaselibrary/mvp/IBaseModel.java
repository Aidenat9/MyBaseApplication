package com.tmgg.xbaselibrary.mvp;

import com.lzy.okgo.callback.Callback;

import java.util.Map;

public abstract class IBaseModel<T> {
    public abstract void getNet(String url, Object tag, Map<String, String> map, Callback<T> callback);
}