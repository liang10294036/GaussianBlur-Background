package cn.blur.self.okHttp.gateway;

import android.text.TextUtils;
import java.io.File;
import java.util.TreeMap;
import cn.blur.self.okHttp.FileStreamCallback;
import cn.blur.self.okHttp.ResultCallback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lkk on 2016/5/10.
 * 代理操作  收集请求数据
 * 调用实现
 */
public abstract class AbsGateway implements Api {


    private static AbsGateway mInstance;
    protected OkHttpClient client;

    public static AbsGateway getInstance() {
        synchronized (AbsGateway.class) {
            if (mInstance == null) {
                mInstance = new GatewayImp();
            }
        }
        return mInstance;
    }


    public abstract void httpDownloadTask(String url, String fileName, FileStreamCallback callback);

    /**
     * 发出请求 以及处理返回结果执行回调
     *
     * @param callback
     * @param request
     */
    abstract void deliveryResult(ResultCallback callback, Request request);

    /**
     * 参数 排序加密处理
     *
     * @param url    接口地址（不包含域名）
     * @param mParam 参数集合
     * @return 返回Request对象
     */
    abstract Request getOrderParamString(String url, TreeMap mParam);

    /**
     * 参数 排序加密处理
     *
     * @param url     接口地址（不包含域名）
     * @param mParam  参数集合
     * @param fileKey 文件key
     * @param file    文件
     * @return 返回Request对象
     */
    abstract Request getOrderParamString(String url, TreeMap<String, String> mParam, String fileKey, File file);


    @Override
    public void httpGetIntegrationResource(String user_id, String nav_module_id, ResultCallback callback) {
        TreeMap<String, String> mParam = new TreeMap<String, String>();
        if (!TextUtils.isEmpty(nav_module_id)) {
            mParam.put("nav_module_id", nav_module_id);
        }
        if (!TextUtils.isEmpty(user_id)) {
            mParam.put("user_id", user_id);
        }
        deliveryResult(callback, getOrderParamString(APP_GET_INTEGRATION_RES, mParam));
    }



}

