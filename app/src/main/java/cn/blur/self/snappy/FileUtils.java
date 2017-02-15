package cn.blur.self.snappy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;

import cn.blur.self.BaseApplication;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * Created by MyPC on 2017/2/15.
 */

public class FileUtils {


    public static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    /**
     * 文件缓存路径
     */
    public final static String FILE_LOADER_CACHE_PATH = "cn.travel.qm/file";
    /**
     * http 文件缓存路径
     */
    public final static String HTTP_FILE_LOADER_CACHE_PATH = "cn.travel.qm/http";

    /**
     * 获取apk下载路径
     * @param packageName
     * @return
     */
    public static File getApkAbsolutePath(String packageName){
        return new File(FileUtils.getDownloadFilePath(), packageName.hashCode() + ".apk");
    }

    /**
     * 获取apk下载临时文件路径
     * @param packageName
     * @return
     */
    public static File getTempAbsolutePath(String packageName){
        return new File(FileUtils.getDownloadFilePath(), packageName.hashCode() + ".temp");
    }

    /**
     * 获取 文件路径
     *
     * @return
     */
    public static String getDownloadFilePath() {
        File appCacheDir = null;
        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(BaseApplication.getInstance())) {
            appCacheDir = new File(Environment.getExternalStorageDirectory(), FILE_LOADER_CACHE_PATH);
        }
        if (appCacheDir == null || (!appCacheDir.exists() && !appCacheDir.mkdirs())) {
            appCacheDir = BaseApplication.getInstance().getCacheDir();
        }

        if(appCacheDir != null){
            return appCacheDir.getAbsolutePath();
        }else{
            return "";
        }
    }

    /**
     * 判断权限
     * @param context
     * @return
     */
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

}
