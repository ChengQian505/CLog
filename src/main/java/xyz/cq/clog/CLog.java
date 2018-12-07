package xyz.cq.clog;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import xyz.cq.clog.log.MLog;
import xyz.cq.clog.toast.MToast;

/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public final class CLog {
    private static List<Integer> inits = new ArrayList<>();
    private static MToast mToast = new MToast();
    public static final CLog INSTANCE=new CLog();

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

    public static void show(Context context,String text) {
        mToast.show(context,text);
    }

    public static void showLong(Context context,String text) {
        mToast.showLong(context,text);
    }

    public CLog baseLog(String baseLog) {
        MLog.BASE_TAG = baseLog;
        return this;
    }

    public CLog isLog(boolean isLog) {
        MLog.isLog = isLog;
        return this;
    }


}
