package com.winklix.indu.healthcareapp.testlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

import com.winklix.indu.healthcareapp.R;


public class MyDialog {

    private ProgressDialog dialog;
    Context context;
    private ProgressBar progressBar;

    public MyDialog(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context, 0);
    }

    public void ShowProgressDialog() {
        try {
            dialog.show();
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.custom_progress_view);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProgress(int progress) {
        progressBar.setProgress(progress);
        if (progress == 100) {
            CancelProgressDialog();
        }
    }

    public void CancelProgressDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static void ShowNegativeDialog(Context context, String Message, String NegText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(Message);
        builder.setNegativeButton(NegText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

}
