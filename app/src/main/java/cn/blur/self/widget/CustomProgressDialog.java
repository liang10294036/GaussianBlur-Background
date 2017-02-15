package cn.blur.self.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

import cn.blur.self.R;

/**
 * Created by MyPC on 2017/2/15.
 */


public class CustomProgressDialog extends AlertDialog {

    public CustomProgressDialog(Context context) {
        this(context, R.style.MyDialog);
    }

    public CustomProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom_progress_dialog);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        this.getWindow().setAttributes(lp);
        setCanceledOnTouchOutside(false);
    }

    public void showDialog() {
        try {
            this.show();
        } catch (Exception e) {
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        } catch (Exception e) {
        }
    }
}