package com.tmgg.xbaselibrary.mvp;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/6/14 17:45
 *         包名：com.tmgg.xbaselibrary.mvp
 *         <p>description:  回调结果          </p>
 */

public interface IBaseCallBack<T> {
    void success(T t);

    void error(String errorMsg);

    void fail();
}
