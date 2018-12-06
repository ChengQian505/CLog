package xyz.cq.clog.toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import xyz.cq.clog.CLog;


/**
 * @author 程前 created on 2018/12/6.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public class MToast {
    private Toast toast1;
    private Toast toast2;

    public Context context;

    /**
     * 底部短时间Toast
     * @param text 吐司内容
     */
    @SuppressLint("ShowToast")
    private void show1(String text) {
        if (toast1 == null) {
            toast1 = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast1.setText(text);
        }
        toast1.show();
    }

    /**
     * 底部长时间Toast
     * @param text 吐司内容
     */
    @SuppressLint("ShowToast")
    private void show1long(String text) {
        if (toast2 == null) {
            toast2 = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            toast2.setText(text);
        }
        toast2.show();
    }


    public void show(String text) {
        show1(text);
        CLog.log("TOAST").i("SHORT-$text");
    }

    public void showLong(String text) {
        show1long(text);
        CLog.log("TOAST").i("LONG-$text");
    }


}
