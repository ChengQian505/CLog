package xyz.cq.clog.log;

/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
abstract class BaseLog implements ILog {
    private int dealtReturn = 0;
    abstract boolean isLog();
    abstract String baseTag();
    @Override
    public int i(String tag, String msg) {
        if (isLog()) {
            return android.util.Log.i(baseTag()+"/"+tag, msg);
        } else {
            return dealtReturn;
        }
    }

    @Override
    public int i(String tag, String msg, Throwable t) {
        if (isLog()) {
            return android.util.Log.i(baseTag()+"/"+tag, msg,t);
        } else {
            return dealtReturn;
        }
    }

    @Override
    public int d(String tag, String msg) {
        if (isLog()) {
            return android.util.Log.d(baseTag()+"/"+tag, msg);
        } else {
            return dealtReturn;
        }
    }

    @Override
    public int d(String tag, String msg, Throwable t) {
        if (isLog()) {
            return android.util.Log.d(baseTag()+"/"+tag, msg,t);
        } else {
            return dealtReturn;
        }
    }

    @Override
    public int e(String tag, String msg) {
        if (isLog()) {
            return android.util.Log.e(baseTag()+"/"+tag, msg);
        } else {
            return dealtReturn;
        }
    }

    @Override
    public int e(String tag, String msg, Throwable t) {
        if (isLog()) {
            return android.util.Log.e(baseTag()+"/"+tag, msg,t);
        } else {
            return dealtReturn;
        }
    }

    @Override
    public int w(String tag, String msg) {
        if (isLog()) {
            return android.util.Log.w(baseTag()+"/"+tag, msg);
        } else {
            return dealtReturn;
        }
    }

    @Override
    public int w(String tag, String msg, Throwable t) {
        if (isLog()) {
            return android.util.Log.w(baseTag()+"/"+tag, msg,t);
        } else {
            return dealtReturn;
        }
    }
}
