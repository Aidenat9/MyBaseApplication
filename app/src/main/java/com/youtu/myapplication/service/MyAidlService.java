package com.youtu.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.tmgg.xbaselibrary.utils.klogutil.KLog;
import com.youtu.myapplication.IMyAidlInterface;
import com.youtu.myapplication.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/13 10:31
 *         包名：com.youtu.myapplication.service
 *         <p>description:            </p>
 */

public class MyAidlService extends Service {
    private ArrayList<Person> mPersons;

    /**
     * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
     */
    private IBinder mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            Random random = new Random();
            if(person==null){
                person = new Person();
                person.setmName("服务端张三"+random.nextInt(10));
            }
            else{
                //person，主要是为了观察其到客户端的反馈
                person.setmName("客户端弄得"+random.nextInt(10));
            }
            if(!mPersons.contains(person)){
                mPersons.add(person);
            }
//打印mBooks列表，观察客户端传过来的值
            KLog.i("服务端："+mPersons.toString());
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }
    };

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        return mBinder;
    }
}
