package com.zavaly.services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogService {

    private Context mContext;

    public DialogService(Context mContext) {
        this.mContext = mContext;
    }

    public void showDialog(String title, String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
