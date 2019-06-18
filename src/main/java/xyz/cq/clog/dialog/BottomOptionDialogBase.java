package xyz.cq.clog.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.cq.clog.R;

/**
 * @author 程前 created on 2019/1/11.
 * blog: https://blog.csdn.net/ch1406285246
 * content:
 * modifyNote:
 */
public class BottomOptionDialogBase extends BaseCommonDialog {
    private Context context;

    public BottomOptionDialogBase(Context context) {
        super(context);
        this.context = context;
        setContentView(R.layout.dialog_bottom_option);
        getWindow().getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setGravity(Gravity.BOTTOM);
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public BottomOptionDialogBase setOnClickListener(View.OnClickListener... onClicks) {
        for (int i = 0; i < onClicks.length; i++) {
            ((ViewGroup) findViewById(R.id.dialog_bottom_base)).getChildAt(i).setOnClickListener(onClicks[i]);
        }
        return this;
    }

    public BottomOptionDialogBase setText(String... options) {
        if (options.length != 0) {
            ViewGroup dialogBottomBase = findViewById(R.id.dialog_bottom_base);
            for (int i = 0; i < options.length; i++) {
                dialogBottomBase.addView(LayoutInflater.from(context).inflate(R.layout.item_dialog_bottom, dialogBottomBase, false));
                ((TextView) dialogBottomBase.getChildAt(i).findViewById(R.id.item_bottom_option_text)).setText(options[i]);
                if (i == 0) {
                    dialogBottomBase.getChildAt(i).findViewById(R.id.item_bottom_option_text_line).setVisibility(View.GONE);
                    dialogBottomBase.getChildAt(i).findViewById(R.id.item_bottom_option_text).setBackgroundResource(R.drawable.bg_bottom_dialog_up);
                } else if (i == options.length - 1) {
                    dialogBottomBase.getChildAt(i).findViewById(R.id.item_bottom_option_text).setBackgroundResource(R.drawable.bg_bottomdialog_down);
                }
            }
        }
        return this;
    }

}
