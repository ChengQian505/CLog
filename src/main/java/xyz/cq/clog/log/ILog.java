package xyz.cq.clog.log;

/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public interface ILog {

    int i(String tag, String msg);

    int i(String tag, String msg, Throwable t);

    int d(String tag, String msg);

    int d(String tag, String msg, Throwable t);

    int e(String tag, String msg);

    int e(String tag, String msg, Throwable t);

    int w(String tag, String msg);

    int w(String tag, String msg, Throwable t);
}
