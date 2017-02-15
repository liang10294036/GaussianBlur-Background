package cn.blur.self.okHttp.gateway;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import cn.blur.self.BaseApplication;
import cn.blur.self.okHttp.CacheInterceptor;
import cn.blur.self.okHttp.FileStreamCallback;
import cn.blur.self.okHttp.HTTP;
import cn.blur.self.okHttp.Result;
import cn.blur.self.okHttp.ResultCallback;
import cn.blur.self.okHttp.URLEncodedUtils;
import cn.blur.self.snappy.FileUtils;
import cn.blur.self.utils.SystemInfo;
import cn.blur.self.utils.Tools;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lkk on 2016/5/10.
 * <p/>
 * 实现发起网络请求事件
 * 实现请求数据排序加密操作
 * 处理网络请求返回的数据
 * 执行回调事件
 */
public class GatewayImp extends AbsGateway {


    public GatewayImp() {
        client = new OkHttpClient();
        Cache cache = new Cache(new File(FileUtils.getDownloadFilePath(),  FileUtils.HTTP_FILE_LOADER_CACHE_PATH),  10 * 1024 * 1024);
        client.newBuilder().connectTimeout(15000, TimeUnit.MILLISECONDS)
                .readTimeout(15000, TimeUnit.MILLISECONDS)
                .writeTimeout(15000, TimeUnit.MILLISECONDS)
        .cache(cache).addNetworkInterceptor(new CacheInterceptor());
    }

    @Override
    void deliveryResult(final ResultCallback callback, Request request) {
        if (callback != null) {
            callback.onStart();
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onFailureMessage(callback, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    if (response.code() != 200) {
                        onErrorMessage(callback, response.code(), response.message());
                        return;
                    }
                    String str = response.body().string().toString();
                    Log.d("AbsGateWay", "-->Result: " + str);
                    if (!TextUtils.isEmpty(str.trim())) {
                        Result mResult = BaseApplication.getInstance().getGson().fromJson(str, callback.mType);
                        parse(callback, mResult);
                    }
                } catch (Exception e) {
                } finally {
                    response.close();
                }
            }


        });
    }


    @Override
    Request getOrderParamString(String url, TreeMap mParam) {
        FormBody.Builder builder = new FormBody.Builder();
        Iterator<Map.Entry<String, String>> iterator = mParam.entrySet().iterator();
        Map.Entry<String, String> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            builder.add(entry.getKey(), entry.getValue());
        }


        return buildRequest(BaseApplication.getInstance(), builder.build(), url, mParam);
    }

    @Override
    Request getOrderParamString(String url, TreeMap<String, String> mParam, String fileKey, File file) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        Iterator<String> iterator = mParam.keySet().iterator();
        String key;
        while (iterator.hasNext()) {
            key = iterator.next();
            builder.addFormDataPart(key, String.valueOf(mParam.get(key)));
        }
        if (file != null) {
            String fileName = file.getName();
            RequestBody fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
            builder.addFormDataPart(fileKey, fileName, fileBody);
        }
        return buildRequest(BaseApplication.getInstance(), builder.build(), url, mParam);
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * <br>post异步请求<br>
     * 获取请求头信息
     *
     * @param url
     * @return
     */
    private Request buildRequest(Context cxt, RequestBody build, String url, TreeMap<String, String> mParam) {
        String paramsStr = URLEncodedUtils.format(mParam, HTTP.UTF_8);
        String timestamp = String.valueOf(System.currentTimeMillis());
        String uuid = SystemInfo.getIMEI(cxt);
        String version = SystemInfo.getAppVersion(cxt);
        String app_key = "";
        String signature = Tools.getMD5("1" + app_key + uuid + version + paramsStr + timestamp);
        Request request = new Request.Builder()
                .url(API_BASE_DOCTOR + url)
//                .header("User-Agent", "OkHttp Headers.java")
//                .addHeader("Accept", "application/json; q=0.5")
//                .addHeader("Accept", "application/vnd.github.v3+json")
                .addHeader("X-Platform", "1")//用户来源，1-Android，2-IOS
                .addHeader("X-Channel", SystemInfo.getAppChannel(cxt))
                .addHeader("X-Version", version)
                .addHeader("X-Uuid", uuid)
//                .addHeader("X-Device", Build.DEVICE == null ? "" : Build.DEVICE)
                .addHeader("X-OS", "android" + Build.VERSION.RELEASE)
                .addHeader("X-Model", Build.MANUFACTURER + Build.MODEL)
                .addHeader("timestamp", timestamp)
                .addHeader("signature", signature)
                .addHeader("app_key", "travel")
                .post(build)
                .build();
        return request;
    }

    /**
     * 服务器没有响应
     *
     * @param callback
     * @param e
     */
    private void onFailureMessage(final ResultCallback callback, final IOException e) {
        BaseApplication.getInstance().post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                    callback.onFinish();
                }
            }
        });
    }


    /**
     * 服务器没有响应
     *
     * @param callback
     * @param code
     * @param message
     */
    private void onErrorMessage(final ResultCallback callback, final int code, final String message) {
        BaseApplication.getInstance().post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onError(code, message);
                    callback.onFinish();
                }

            }
        });
    }

    /**
     * 解析数据
     * 转成主线程回调
     *
     * @param callback
     * @param mResult
     */
    private void parse(final ResultCallback callback, final Result mResult) {
        BaseApplication.getInstance().post(new Runnable() {
            @Override
            public void run() {
                callback.onFinish();
                if (mResult.getCode() == 1) {
                    callback.onResponse(mResult);
                } else  { // 如果已经弹出 不执行toast
                    callback.onResponse(mResult.getCode(), mResult.getMsg());
                }
            }
        });
    }


    long startPoints = 0;

    @Override
    public void httpDownloadTask(String url, final String packageName, final FileStreamCallback callback) {
        startPoints = 0;
        final File file = FileUtils.getTempAbsolutePath(packageName);
        if (file.exists()) {
            startPoints = file.length();
        }
        Request request = new Request.Builder().url(url)
                .header("RANGE", "bytes=" + startPoints + "-") //断点续传要用到的，指示下载的区间
                .addHeader("Accept-Encoding", "identity")
                .build();
        callback.onDownloadStart();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) {

                if (response.code() != 200 && response.code() != 206) {
                    callback.onDownloadNotFile(response.code());
                    return;
                }
                InputStream is = null;
                byte[] buf = new byte[100 * 1024];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    if (total < 0) {
                        call.cancel();
                        callback.onDownloadNotFile(response.code());
                        return;
                    }

                    fos = new FileOutputStream(file, true);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) ((sum + startPoints) * 1.0f / (total + startPoints) * 100);
                        callback.onDownloadProgress(progress);
                    }
                    fos.flush();
                    File apkFile = FileUtils.getApkAbsolutePath(packageName);
                    boolean s = file.renameTo(apkFile);
                    file.delete();
                    callback.onDownloadFinish(apkFile);
                } catch (Exception e) {
                    callback.onDownloadFailure(e.toString());
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }

            @Override
            public void onFailure(Call call, final IOException e) {
                callback.onDownloadFailure(e.toString());
            }
        });
    }



}
