package cn.blur.self.okHttp;

import java.io.File;

/**
 * Created by Administrator on 2016/8/6.
 * <p>
 * 文件下载
 * 回调接口
 */

public interface FileStreamCallback {

    /**
     * 下载文件开始
     *
     * @param
     */
    void onDownloadStart();

    /**
     * 文件下载进度
     *
     * @param val
     */
    void onDownloadProgress(int val);

    /**
     * 下载文件完成
     *
     * @param file
     */
    void onDownloadFinish(File file);

    /**
     * 下载文件失败
     *
     * @param exception
     */
    void onDownloadFailure(String exception);

    /**
     * 找不到文件
     *
     * @param code
     */
    void onDownloadNotFile(int code);
}
