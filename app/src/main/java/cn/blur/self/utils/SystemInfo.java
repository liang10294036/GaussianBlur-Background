package cn.blur.self.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

/**
 * Created by MyPC on 2017/2/15.
 */

public class SystemInfo {


    private final static String TAG = "SystemInfo";
    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;

    public static String getIMEI(Context context) {
        String imei="";

        if (requestPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();
        }
        try {
            long i = Long.parseLong(imei);
            if (i == 0) { // 防止返回类似000000
                imei = "";
            }
        } catch (Exception e) {

        }
        if (TextUtils.isEmpty(imei)) {
            imei = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

//           String deviceid = android.os.Build.FINGERPRINT;
        }
        return imei;
    }

    /**
     * 获取版本名称
     */
    public static String getAppVersion(Context context) {
        String version;
        try {
            version = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("SystemTool the application not found");
        }
        return version == null ? "" : version;
    }


    /**
     * 获取application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppChannel(Context ctx) {
        String resultData = null;
        try {
            PackageManager packageManager = ctx.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(),
                        PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString("QM_CHANNEL");
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return resultData;
    }


    public static DisplayMetrics getDeviceDisplayMetrics(Context context) {
        android.view.WindowManager windowsManager = (android.view.WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        android.view.Display display = windowsManager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        return outMetrics;
    }

    /**
     * 检查是否与网络可用
     *
     * @return
     */

    public boolean isNetworkAvailable(Context activity) {
        Context context = activity.getApplicationContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) {
            return false;
        } else {
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检查网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null && requestPermissions(context, Manifest.permission.ACCESS_WIFI_STATE)) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 打电话  （需要申请权限）
     *
     * @param cxt
     * @param tel
     */
    public static void callPhone(Activity cxt, String tel) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
        if (requestPermissions(cxt, Manifest.permission.CALL_PHONE)) {
            cxt.startActivity(intent);
        }
    }

    /**
     * 跳转到发短信界面
     *
     * @param cxt
     * @param phone
     */
    public static void sendSMS(Context cxt, String phone) {
        Uri smsToUri = Uri.parse("smsto://" + phone);
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        cxt.startActivity(mIntent);
    }


    /**
     * Android M(6.0) 动态申请权限
     *
     * @param context
     * @param permission 所要申请的权限
     * @return
     */
    public static boolean requestPermissions(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(context.getApplicationContext(), permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{permission}, REQUEST_CODE_ASK_CALL_PHONE);
                //判断是否需要 向用户解释，为什么要申请该权限
                ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
                return false;
            }
        }
        return true;
    }

}
