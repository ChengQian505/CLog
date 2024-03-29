package xyz.cq.clog.log;


/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public class MLog extends BaseLog {
    private String tag;

    public MLog(String tag) {
        if (tag != null) {
            this.tag = tag;
        } else {
            this.tag = "";
        }
    }

    /**
     * CQLOG/DEFULAT
     *
     * @param msg 打印信息
     */
    public int i(String msg) {
        return i(tag, msg);
    }

    /**
     * CQLOG/DEFULAT
     *
     * @param msg       打印信息
     * @param throwable 异常
     */
    public int i(String msg, Throwable throwable) {
        return i(tag, msg, throwable);
    }

    /**
     * CQLOG/DEFULAT
     *
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
     *
     * @param cls 打印所在类
     * @param msg 打印信息
     */
    public int e(Class cls, String msg) {
        return e(tag, msg + getCodeLocation(cls.getName()));
    }

    /**
     * CQLOG/DEFULAT
     *
     * @param cls 打印所在类
     * @param t   错误信息
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
     *
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

    public void eFile(String msg, Throwable t) {
        eFile(tag, msg, t);
    }

    public void eFile(String msg) {
        eFile(tag, msg);
    }

    public void iFile(String msg, Throwable t) {
        iFile(tag, msg, t);
    }

    public void iFile(String msg) {
        iFile(tag, msg);
    }

}
