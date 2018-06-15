package com.tmgg.xbaselibrary.utils.okgoconfig;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.tmgg.xbaselibrary.utils.okgoconfig.callback.JsonCallback;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
* okgo网络请求类工具
*/
public class MyHttpRequest {
    public static <T> void getRequest(String url, Object tag, Map<String, String> map, Callback<T> callback) {
        OkGo.<T>get(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }
    public static <T> void postRequest(String url, Object tag, Map<String, String> map, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }
    public static <T> void upLoadFiles(String url, Object tag, Map<String, String> map, String fileType, List<File> files, JsonCallback<T> callback) {
        OkGo.<T>post(url)
                .tag(tag)
                .params(map)
                .addFileParams(fileType, files)           // 这种方式为同一个key，上传多个文件
                .execute(callback);
    }
}