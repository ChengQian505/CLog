package xyz.cq.clog.dialog;

import android.content.Context;
import android.view.View;

import xyz.cq.clog.R;

/**
 * @author 程前 created on 2019/1/11.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public class LoadingDialog extends BaseDialog {
    private View view;

    public LoadingDialog(Context context, View view) {
        super(context, R.style.common_dialog);
        this.view = view;
        setContentView(R.layout.layout_http);
        getWindow().getAttributes().dimAmount = 0.1F;
        setCancelable(false);
    }

    public LoadingDialog(Context context) {
        this(context, null);
    }

    @Override
    public void show() {
        super.show();
        if (view != null) {
            view.setEnabled(false);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (view != null) {
            view.setEnabled(true);
        }
    }
}
