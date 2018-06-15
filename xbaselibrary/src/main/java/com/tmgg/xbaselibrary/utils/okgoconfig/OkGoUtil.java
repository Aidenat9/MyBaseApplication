package com.tmgg.xbaselibrary.utils.okgoconfig;

import com.lzy.okgo.OkGo;
import com.tmgg.xbaselibrary.utils.klogutil.KLog;
import com.tmgg.xbaselibrary.utils.okgoconfig.callback.JsonCallback;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
* okgo网络请求类工具
*/
public class OkGoUtil {
    public static <T> void getRequets(String url, Object tag, Map<String, String> map, JsonCallback<T> callback) {
        KLog.i("OkGoUtil", "method get");
        OkGo.<T>get(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }
    public static <T> void postRequest(String url, Object tag, Map<String, String> map, JsonCallback<T> callback) {
        KLog.i("OkGoUtil", "method post");
        OkGo.<T>post(url)
                .tag(tag)
                .params(map)
                .execute(callback);
    }
    public static <T> void upLoadFiles(String url, Object tag, Map<String, String> map, String fileType, List<File> files, JsonCallback<T> callback) {
        KLog.i("OkGoUtil", "method post upLoadFiles");
        OkGo.<T>post(url)
                .tag(tag)
                .params(map)
                .addFileParams(fileType, files)           // 这种方式为同一个key，上传多个文件
                .execute(callback);
    }
}