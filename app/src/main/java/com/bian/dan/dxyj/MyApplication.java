package com.bian.dan.dxyj;

import android.app.Application;
import android.content.Context;

import com.bian.dan.dxyj.utils.ActivitysLifecycle;
import com.vise.baseble.ViseBle;

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

        //初始化蓝牙配置
        initBlueTooth();

        //管理Activity
        registerActivityLifecycleCallbacks(ActivitysLifecycle.getInstance());
    }


    /**
     * 初始化蓝牙配置
     */
    private void initBlueTooth(){
        //蓝牙相关配置修改
        ViseBle.config()
                .setScanTimeout(-1)//扫描超时时间，-1表示永久扫描
                .setConnectTimeout(10 * 1000)//连接超时时间
                .setOperateTimeout(5 * 1000)//设置数据操作超时时间
                .setConnectRetryCount(3)//设置连接失败重试次数
                .setConnectRetryInterval(1000)//设置连接失败重试间隔时间
                .setOperateRetryCount(3)//设置数据操作失败重试次数
                .setOperateRetryInterval(1000)//设置数据操作失败重试间隔时间
                .setMaxConnectCount(1);//设置最大连接设备数量
        //蓝牙信息初始化，全局唯一，必须在应用初始化时调用
        ViseBle.getInstance().init(this);
    }

    public static Context getContext() {
        return context;
    }

}
