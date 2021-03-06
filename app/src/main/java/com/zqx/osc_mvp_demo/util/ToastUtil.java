package com.zqx.osc_mvp_demo.util;

import android.widget.Toast;

import com.zqx.osc_mvp_demo.global.MyApp;


public class ToastUtil {

    private static Toast sToast;

    public static void show(String text) {
        if (sToast == null) {
            sToast = Toast.makeText(MyApp.context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(text);
        }
        sToast.show();
    }

    public static void hide(){
        sToast.cancel();
    }
}
