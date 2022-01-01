package com.zavaly.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import androidx.appcompat.widget.AppCompatImageView;

import com.zavaly.R;

import java.util.Timer;
import java.util.TimerTask;


public class Helper {

    static AlertDialog dialog;

    public static void showLoader(final Context context, String msg) {
        final Activity activity = (Activity) context;

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = activity.getLayoutInflater().inflate(R.layout.loader, null);


        builder.setView(view);

        dialog = builder.create();
        dialog.requestWindowFeature(DialogFragment.STYLE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // dialog.getWindow().setLayout(100,100);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        //setAnimation
        AppCompatImageView imageView = view.findViewById(R.id.loader_image);
        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(3000);
        imageView.setAnimation(anim);
        imageView.startAnimation(anim);

        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                dialog.dismiss();
                timer2.cancel(); //this will cancel the timer of the system
            }
        }, 3000);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;

        // Initialize a new window manager layout parameters
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        // Copy the alert dialog window attributes to new layout parameter instance
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        // Set alert dialog width equal to screen width 70%
        int dialogWindowWidth = (int) (displayWidth * 0.5f);
        // Set alert dialog height equal to screen height 70%
        int dialogWindowHeight = (int) (displayHeight * 0.1f);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;

        // Apply the newly created layout parameters to the alert dialog window
        dialog.getWindow().setAttributes(layoutParams);

    }

    public static void cancelLoader() {
        dialog.dismiss();
    }


    //check internet connection
    public static boolean isOnline(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {

            return true;

        }

        return false;
    }
}
