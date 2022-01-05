package com.zavaly.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zavaly.utils.ApplicationMode;
import com.zavaly.utils.ExceptionHandler;


public class CrashHandleActivity extends AppCompatActivity {

    CrashHandleActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_crash_handle);
        activity = this;

        if (!ApplicationMode.devMode) {
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        }

    }
}