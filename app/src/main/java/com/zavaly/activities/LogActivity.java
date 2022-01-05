package com.zavaly.activities;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.zavaly.R;

import java.util.List;
import java.util.Stack;

public class LogActivity extends AppCompatActivity {

    Button btnReport;
    LogActivity activity;
    ImageView ivCloseErrorReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        activity = this;
        ivCloseErrorReport = findViewById(R.id.ivCloseErrorReport);
        ivCloseErrorReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, SplashActivity.class));
                finish();
            }
        });

        btnReport = findViewById(R.id.btnReport);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendErrorMail();
            }
        });

    }

    private void sendErrorMail() {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("*/*");
        //i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(crashLogFile));
        //i.putExtra(Intent.EXTRA_EMAIL, new String[]{"ibrar.tanim@gmail.com"});
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"ibrar.tanim@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Alif Mart Crash report");
        i.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("error"));

        startActivity(createEmailOnlyChooserIntent(i, "Send via email"));
    }


    public Intent createEmailOnlyChooserIntent(Intent source, CharSequence chooserTitle) {
        Stack<Intent> intents = new Stack<Intent>();
        //Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "ibrar.tanim@gmail.com", null));
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "ibrar.tanim@gmail.com", null));
        List<ResolveInfo> activities = getPackageManager().queryIntentActivities(i, 0);

        for (ResolveInfo ri : activities) {
            Intent target = new Intent(source);
            target.setPackage(ri.activityInfo.packageName);
            intents.add(target);
        }

        if (!intents.isEmpty()) {
            Intent chooserIntent = Intent.createChooser(intents.remove(0), chooserTitle);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toArray(new Parcelable[intents.size()]));
            return chooserIntent;
        } else {
            return Intent.createChooser(source, chooserTitle);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(activity, SplashActivity.class));
        finish();
    }


}