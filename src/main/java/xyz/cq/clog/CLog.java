package xyz.cq.clog;

import android.annotation.SuppressLint;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import xyz.cq.clog.log.MLog;
import xyz.cq.clog.utils.Util;

/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public final class CLog {

    private static final CLog INSTANCE=new CLog();

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

    public static CLog baseLog(String baseLog) {
        MLog.BASE_TAG = baseLog;
        return INSTANCE;
    }

    public static CLog logFile(String logFilePath) {
        MLog.logFilePath = logFilePath;
        return INSTANCE;
    }

    public CLog isLog(boolean isLog) {
        MLog.isLog = isLog;
        return this;
    }

    public static String packageName() {
        return Util.getApp().getPackageName();
    }


    private static Toast toast1;
    private static Toast toast2;


    /**
     * 底部短时间Toast
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
        CLog.log("TOAST").i("SHORT-"+text);
    }

    /**
     * 底部长时间Toast
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
        CLog.log("TOAST").i("LONG-"+text);
    }


    public static void show1(String text) {
        Toast.makeText(Util.getApp(),text,Toast.LENGTH_SHORT).show();
        CLog.log("TOAST").i("SHORT-"+text);
    }

    public static void showLong1(String text) {
        Toast.makeText(Util.getApp(),text,Toast.LENGTH_LONG).show();
        CLog.log("TOAST").i("LONG-"+text);
    }

}
