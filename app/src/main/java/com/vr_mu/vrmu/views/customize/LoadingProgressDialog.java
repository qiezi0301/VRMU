package com.vr_mu.vrmu.views.customize;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.vr_mu.vrmu.R;

public class LoadingProgressDialog extends Dialog {

    private static LoadingProgressDialog customProgressDialog = null;


    public LoadingProgressDialog(Context context) {
        super(context);
    }

    public LoadingProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static LoadingProgressDialog createDialog(Context context) {
        customProgressDialog = new LoadingProgressDialog(context, R.style.LodingDialog);
        customProgressDialog.setContentView(R.layout.loading_dialog);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        customProgressDialog.setCancelable(true);
        return customProgressDialog;
    }


    /**
     * [Summary] setMessage 提示内容
     *
     * @param strMessage
     * @return
     */
    public static LoadingProgressDialog setMessage(String strMessage) {
        TextView tvMsg = (TextView) customProgressDialog
                .findViewById(R.id.id_tv_loadingmsg);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }

        return customProgressDialog;
    }

}
