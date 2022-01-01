package com.zavaly.services;

import android.app.ProgressDialog;
import android.content.Context;

public class LoaderService {
    private Context context;
    private ProgressDialog progress;

    public LoaderService(Context context) {
        this.context = context;

        initLoader();
    }

    private void initLoader() {
        progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);


    }

    public void start() {
        progress.show();
    }

    public void stop() {
        progress.dismiss();
    }

}
