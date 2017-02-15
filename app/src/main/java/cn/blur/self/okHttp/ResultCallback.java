package cn.blur.self.okHttp;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2015/12/5 0005.
 * <p/>
 * 回调函数
 * 请求事件结果通知
 */
public abstract class ResultCallback<T> {

    public Type mType;

    public ResultCallback() {

        mType = getSuperclassTypeParameter(getClass());
    }

    Type getSuperclassTypeParameter(Class<?> subclass) {

        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter");
        }
        ParameterizedType parameTerized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameTerized.getActualTypeArguments()[0]);
    }

    /**
     * 网路请求失败
     *
     * @param e
     */
    public void onFailure(Exception e) {
    }

    /**
     * 网路请求错误
     *
     * @param code
     * @param message
     */
    public void onError(int code, String message) {
    }

    /**
     * 请求服务器成功
     *
     * @param response
     */
    public void onResponse(T response) {
    }

    /**
     * 请求服务器成功 code值是2
     *
     * @param code
     * @param msg
     */
    public void onResponse(int code, String msg) {
    }

    /**
     * 请求完成
     */
    public void onFinish() {
    }

    /**
     * 请求开始
     */
    public void onStart() {
    }

}
