package cn.blur.self;


import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/9/2.
 */

public class BaseApplication extends Application {


    static BaseApplication instance;

    private Handler mDelivery;

    private static int mainThreadId;

    private Gson mGson;

    public BaseApplication() {
        instance = this;
        mDelivery = new Handler(Looper.getMainLooper());
    }

    public static BaseApplication getInstance() {
        return instance;
    }


    public static int getMainThreadId() {
        return mainThreadId;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if ("cn.blur.self".equals(getCurProcessName(getApplicationContext()))) {
            Fresco.initialize(this);
            //获取主线程id
            mainThreadId = android.os.Process.myTid();
//            CrashHandler.getInstance().init(this);

        }

    }


    /**
     * 一个获得当前进程的名字的方法
     *
     * @param context
     * @return
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    /**
     * handle 执行
     *
     * @param runnable
     */
    public void post(Runnable runnable) {
        mDelivery.post(runnable);
    }

    /**
     * handle 延迟
     *
     * @param runnable 毫秒
     */
    public void postDelayed(Runnable runnable, long delayMillis) {

        mDelivery.postDelayed(runnable, delayMillis);
    }

    /**
     * 获取 gson对象
     *
     * @return
     */
    public Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }


}
