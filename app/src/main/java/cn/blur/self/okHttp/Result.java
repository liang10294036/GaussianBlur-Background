package cn.blur.self.okHttp;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/5 0005.
 * <p>
 * 网络请求数据结果
 */
public class Result<T> implements Serializable {

    int code;
    int error_code;
    T result;
    String msg;
    boolean failed;
    boolean success;
    boolean error;
    /**
     * 1.弹出交互框
     * 其他.不做操作
     */
    int is_msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getIs_msg() {
        return is_msg;
    }

    public void setIs_msg(int is_msg) {
        this.is_msg = is_msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", error_code=" + error_code +
                ", result=" + result +
                ", msg='" + msg + '\'' +
                ", failed=" + failed +
                ", success=" + success +
                ", error=" + error +
                ", is_msg=" + is_msg +
                '}';
    }
}
