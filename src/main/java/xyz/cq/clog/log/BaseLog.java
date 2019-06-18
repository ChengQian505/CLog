package xyz.cq.clog.log;

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
    public static boolean isLog = true;
    public static String BASE_TAG = "CLog";
    public static String logFilePath = "";


    private boolean isrun = false;
    private LoggerThreadFactory loggerThreadFactory = new LoggerThreadFactory();
    private Thread logThread = loggerThreadFactory.newThread(new Runnable() {
        @Override
        public void run() {
            if (!logList.isEmpty() && !isrun) {
                isrun = true;
                try {
                    File logFileDirectory = new File(logFilePath.substring(0, logFilePath.lastIndexOf("/")));
                    if (!logFileDirectory.isDirectory()) {
                        logFileDirectory.mkdirs();
                    }
                    File file = new File(logFilePath);
                    if (!file.isFile()) {
                        file.createNewFile();
                    }
                    RandomAccessFile raf = new RandomAccessFile(file, "rwd");
                    raf.seek(file.length());
                    raf.write(logList.get(0).getBytes());
                    logList.remove(logList.get(0));
                    raf.close();
                    isrun = false;
                    logThread.run();
                } catch (Exception ignored) {
                    e("", "", ignored);
                }
            }
        }
    });

    private static ArrayList<String> logList = new ArrayList<>();

    private class LoggerThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    eFile("", "", e);
                }
            });
            return t;
        }
    }

    @Override
    public int i(String tag, String msg) {
        if (isLog) {
            return android.util.Log.i(BASE_TAG + "/" + tag, msg);
        }
        return 0;
    }

    @Override
    public int i(String tag, String msg, Throwable t) {
        if (isLog) {
            return android.util.Log.i(BASE_TAG + "/" + tag, msg, t);
        }
        return 0;
    }

    @Override
    public int d(String tag, String msg) {
        if (isLog) {
            return android.util.Log.d(BASE_TAG + "/" + tag, msg);
        }
        return 0;
    }

    @Override
    public int d(String tag, String msg, Throwable t) {
        if (isLog) {
            return android.util.Log.d(BASE_TAG + "/" + tag, msg, t);
        }
        return 0;
    }

    @Override
    public int e(String tag, String msg) {
        if (isLog) {
            return android.util.Log.e(BASE_TAG + "/" + tag, msg);
        }
        return 0;
    }

    @Override
    public int e(String tag, String msg, Throwable t) {
        if (isLog) {
            return android.util.Log.e(BASE_TAG + "/" + tag, msg, t);
        }
        return 0;
    }

    @Override
    public int w(String tag, String msg) {
        if (isLog) {
            return android.util.Log.w(BASE_TAG + "/" + tag, msg);
        }
        return 0;
    }

    @Override
    public int w(String tag, String msg, Throwable t) {
        if (isLog) {
            return android.util.Log.w(BASE_TAG + "/" + tag, msg, t);
        }
        return 0;
    }

    void eFile(String tag, String msg, Throwable t) {
        if (!logFilePath.equals("")) {
            writeTxtToFile("ERROR/" + BASE_TAG + "/" + tag + ": " + msg + "\r\n" + t.getCause() + "\r\n" + Arrays.toString(t.getStackTrace()));
        }
    }

    void eFile(String tag, String msg) {
        if (!logFilePath.equals("")) {
            writeTxtToFile("ERROR/" + BASE_TAG + "/" + tag + ": " + msg);
        }
    }

    void iFile(String tag, String msg, Throwable t) {
        if (!logFilePath.equals("")) {
            writeTxtToFile("INFO/" + BASE_TAG + "/" + tag + ": " + msg + "\r\n" + t.getCause() + "\r\n" + Arrays.toString(t.getStackTrace()));
        }
    }

    void iFile(String tag, String msg) {
        if (!logFilePath.equals("")) {
            writeTxtToFile("INFO/" + BASE_TAG + "/" + tag + ": " + msg);
        }
    }

    /**
     * 将字符串写入到文本文件中
     */
    private void writeTxtToFile(String strcontent) {
        if (!"".equals(logFilePath)) {
            logList.add(getTime() + " " + CLog.packageName() + " " + strcontent + "\r\n");
            logThread.run();
        }
    }

    private static String getTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        return sf.format(new Date());
    }

    /**
     * 获取行数信息
     *
     * @param tag 类名
     * @return 行数信息
     */
    String getCodeLocation(String tag) {
        StackTraceElement targetStack = getTargetStack(tag);
        try {
            return "(" + targetStack.getFileName() + ":" + targetStack.getLineNumber() + ")";
        } catch (NullPointerException e) {
            return tag;
        }

    }

    // 获取最后调用我们log的StackTraceElement
    private StackTraceElement getTargetStack(String tag) {
        // 用于存储 目标类 中所有调用的方法
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {

            // 只获取目标类中的 element
            if (element.getClassName().contains(tag)) {
                return element;
            }
        }
        // 获取不到返回 null
        return null;
    }

}
