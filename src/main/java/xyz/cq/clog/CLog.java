package xyz.cq.clog;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.WeakHashMap;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import xyz.cq.clog.log.MLog;
import xyz.cq.clog.utils.Util;

/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public final class CLog {


    private static WeakHashMap<String, MLog> tags = new WeakHashMap<>();

    public static MLog log() {
        return log(null);
    }

    public static MLog log(String tag) {
        if (tags.containsKey(tag)) {
            return tags.get(tag);
        } else {
            tags.put(tag, new MLog(tag));
            return log(tag);
        }
    }

    public static void init(String baseLog,boolean isLog){
        MLog.BASE_TAG = baseLog;
        MLog.isLog = isLog;
        log().i("CLog:version is " + BuildConfig.VERSION_NAME + ",TAG is " + baseLog);
    }

    public static void logFile(final String logFilePath) {
        if (!logFilePath.equals("")){
            MLog.logFilePath=logFilePath;
            HiPermission.create(Util.getApp())
                    .checkSinglePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                        @Override
                        public void onClose() {

                        }

                        @Override
                        public void onFinish() {

                        }

                        @Override
                        public void onDeny(String permission, int position) {
                            log().i("logFile fail,permissiong onDeny");
                        }

                        @Override
                        public void onGuarantee(String permission, int position) {
                            File logFile = new File(logFilePath);
                            if (logFile.exists()) {
                                logFile.delete();
                            }
                            try {
                                File logFileDirectory = new File(logFilePath.substring(0, logFilePath.lastIndexOf("/")));
                                if (!logFileDirectory.isDirectory()) {
                                    logFileDirectory.mkdirs();
                                }
                                File file = new File(logFilePath);
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            log().iFile("CLog:version is " + BuildConfig.VERSION_NAME + ",TAG is " + MLog.BASE_TAG+",logPath is " + logFilePath);
                        }

                    });
        }else{

        }
    }

    public static String packageName() {
        return Util.getApp().getPackageName();
    }


    private static Toast toast1;
    private static Toast toast2;


    /**
     * 底部短时间Toast
     *
     * @param text 吐司内容
     */
    @SuppressLint("ShowToast")
    public static void show(String text) {
        if (toast1 == null) {
            toast1 = Toast.makeText(Util.getApp(), text, Toast.LENGTH_SHORT);
        } else {
            toast1.setText(text);
        }
        toast1.show();
        CLog.log("TOAST").i("SHORT-" + text);
    }

    /**
     * 底部长时间Toast
     *
     * @param text 吐司内容
     */
    @SuppressLint("ShowToast")
    public static void showLong(String text) {
        if (toast2 == null) {
            toast2 = Toast.makeText(Util.getApp(), text, Toast.LENGTH_LONG);
        } else {
            toast2.setText(text);
        }
        toast2.show();
        CLog.log("TOAST").i("LONG-" + text);
    }


    public static void show1(String text) {
        Toast.makeText(Util.getApp(), text, Toast.LENGTH_SHORT).show();
        CLog.log("TOAST").i("SHORT-" + text);
    }

    public static void showLong1(String text) {
        Toast.makeText(Util.getApp(), text, Toast.LENGTH_LONG).show();
        CLog.log("TOAST").i("LONG-" + text);
    }

}
