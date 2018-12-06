package xyz.cq.clog.log;


/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public class MLog extends BaseLog {
    public static boolean isLog=true;
    public static String BASE_TAG="CLOG";
    private String tag;
    public MLog(String tag){
        if (tag != null) {
            this.tag = tag;
        } else {
            this.tag = "DEFAULT";
        }
    }
    
    /**
     * CQLOG/DEFULAT
     * @param msg 打印信息
     */
    public int i(String msg) {
        return i(tag, msg);
    }

    /**
     * CQLOG/DEFULAT
     * @param msg 打印信息
     * @param throwable 异常
     */
    public int i(String msg, Throwable throwable) {
        return i(tag, msg, throwable);
    }

    /**
     * CQLOG/DEFULAT
     * @param cls 打印所在类
     * @param msg 打印信息
     */
    public int i(Class cls, String msg) {
        return i(tag, msg + getCodeLocation(cls.getName()));
    }


    /**
     * @param cls 打印所在类
     * @param tag 打印tag
     * @param msg 打印信息
     */
    public int i(Class cls, String tag, String msg) {
        return i(this.tag, msg + getCodeLocation(cls.getName()));
    }

    /**
     * @param msg 打印信息
     */
    public int w(String msg) {
        return w(tag, msg);
    }

    /**
     * @param msg 打印信息
     */
    public int w(String msg, Throwable throwable) {
        return w(tag, msg, throwable);
    }

    /**
     * @param msg 打印信息
     */
    public int d(String msg) {
        return d(tag, msg);
    }

    public int d(Throwable t) {
        return d(tag, "", t);
    }

    /**
     * @param msg 打印信息
     */
    public int d(String msg, Throwable t) {
        return d(tag, msg, t);
    }

    /**
     * CQLOG/DEFULAT
     * @param cls 打印所在类
     * @param msg 打印信息
     */
    public int e(Class cls, String msg) {
        return e(tag, msg + getCodeLocation(cls.getName()));
    }

    /**
     * CQLOG/DEFULAT
     * @param cls 打印所在类
     * @param t 错误信息
     */
    public int e(Class cls, Throwable t) {
        return e(tag, getCodeLocation(cls.getName()), t);
    }

    /**
     * @param msg 打印信息
     */
    public int e(String msg) {
        return e(tag, msg);
    }

    /**
     */
    public int e(Throwable t) {
        return e(tag, "", t);
    }

    /**
     * @param tag 打印tag
     * @param msg 打印信息
     */
    public int e(Class cls, String tag, String msg, Throwable tr) {
        return e(this.tag, msg + getCodeLocation(cls.getName()), tr);
    }

    /**
     * @param msg 打印信息
     */
    public int e(Class cls, String msg, Throwable tr) {
        return e(tag, msg + getCodeLocation(cls.getName()), tr);
    }

    /**
     * @param msg 打印信息
     */
    public int e(String msg, Throwable tr) {
        return e(tag, msg, tr);
    }

    /**
     * @param cls 打印所在类
     * @param tag 打印tag
     * @param msg 打印信息
     */
    public int e(Class cls, String tag, String msg) {
        return e(this.tag, msg + getCodeLocation(cls.getName()));
    }


    /**
     * 获取行数信息
     * @param tag 类名
     * @return 行数信息
     */
    private String getCodeLocation(String tag){
        StackTraceElement targetStack = getTargetStack(tag);
        try {
            return "(" + targetStack.getFileName() + ":" + targetStack.getLineNumber() + ")";
        } catch (NullPointerException e) {
            return tag;
        }

    }

    // 获取最后调用我们log的StackTraceElement
    private StackTraceElement getTargetStack(String tag){
        // 用于存储 目标类 中所有调用的方法
        for (StackTraceElement element:Thread.currentThread().getStackTrace()) {

            // 只获取目标类中的 element
            if (element.getClassName().contains(tag)) {
                return element;
            }
        }
        // 获取不到返回 null
        return null;
    }

    @Override
    boolean isLog() {
        return isLog;
    }

    @Override
    String baseTag() {
        return BASE_TAG;
    }
}
