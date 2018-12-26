package xyz.cq.clog.log;

import android.os.MessageQueue;
import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadFactory;

import xyz.cq.clog.CLog;

/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
abstract class BaseLog implements ILog {
    private int dealtReturn = 0;
    public static String BASE_TAG = "CLOG";
    public static String logFilePath = "";

    abstract boolean isLog();

    abstract String baseTag();
    BaseLog(){
        logThread.start();
    }
    private static boolean isrun=false;
    private static LoggerThreadFactory loggerThreadFactory = new LoggerThreadFactory();
    private static Thread logThread = loggerThreadFactory.newThread(new Runnable() {
        @Override
        public void run() {
            if (!logList.isEmpty()&& !isrun) {
                isrun=true;
                try {
                    File file = new File(logFilePath);
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    raf.seek(file.length());
                    raf.write(logList.get(0).getBytes());
                    logList.remove(logList.get(0));
                    raf.close();
                    isrun=false;
                    logThread.run();
                } catch (Exception ignored) {
                }
            }
        }
    });

    private static ArrayList<String> logList = new ArrayList<>();

    private static class LoggerThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    writeTxtToFile("E/" + e.getMessage());
                }
            });
            return t;
        }
    }

    @Override
    public int i(String tag, String msg) {
        if (isLog()) {
            return android.util.Log.i(baseTag() + "/" + tag, msg);
        } else {
            writeTxtToFile("I/" + baseTag() + "/" + tag + ": " + msg);
            return dealtReturn;
        }
    }

    @Override
    public int i(String tag, String msg, Throwable t) {
        if (isLog()) {
            return android.util.Log.i(baseTag() + "/" + tag, msg, t);
        } else {
            writeTxtToFile("I/" + baseTag() + "/" + tag + ": " + msg + "\r\n" + t.getCause() + "\r\n" + Arrays.toString(t.getStackTrace()));
            return dealtReturn;
        }
    }

    @Override
    public int d(String tag, String msg) {
        if (isLog()) {
            return android.util.Log.d(baseTag() + "/" + tag, msg);
        } else {
            writeTxtToFile("D/" + baseTag() + "/" + tag + ": " + msg);
            return dealtReturn;
        }
    }

    @Override
    public int d(String tag, String msg, Throwable t) {
        if (isLog()) {
            return android.util.Log.d(baseTag() + "/" + tag, msg, t);
        } else {
            writeTxtToFile("D/" + baseTag() + "/" + tag + ": " + msg + "\r\n" + t.getCause() + "\r\n" + Arrays.toString(t.getStackTrace()));
            return dealtReturn;
        }
    }

    @Override
    public int e(String tag, String msg) {
        if (isLog()) {
            return android.util.Log.e(baseTag() + "/" + tag, msg);
        } else {
            writeTxtToFile("E/" + baseTag() + "/" + tag + ": " + msg);
            return dealtReturn;
        }
    }

    @Override
    public int e(String tag, String msg, Throwable t) {
        if (isLog()) {
            return android.util.Log.e(baseTag() + "/" + tag, msg, t);
        } else {
            writeTxtToFile("E/" + baseTag() + "/" + tag + ": " + msg + "\r\n" + t.getCause() + "\r\n" + Arrays.toString(t.getStackTrace()));
            return dealtReturn;
        }
    }

    @Override
    public int w(String tag, String msg) {
        if (isLog()) {
            return android.util.Log.w(baseTag() + "/" + tag, msg);
        } else {
            writeTxtToFile("W/" + baseTag() + "/" + tag + ": " + msg);
            return dealtReturn;
        }
    }

    @Override
    public int w(String tag, String msg, Throwable t) {
        if (isLog()) {
            return android.util.Log.w(baseTag() + "/" + tag, msg, t);
        } else {
            writeTxtToFile("W/" + baseTag() + "/" + tag + ": " + msg + "\r\n" + t.getCause() + "\r\n" + Arrays.toString(t.getStackTrace()));
            return dealtReturn;
        }
    }

    /**
     * 将字符串写入到文本文件中
     */
    private static void writeTxtToFile(String strcontent) {
        if (!"".equals(logFilePath)) {
            logList.add(getTime() + " " + CLog.packageName() + " " + strcontent + "\r\n");
            logThread.run();
        }
    }

    private static String getTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        return sf.format(new Date());
    }

}
