package com.rui.xb.purple.app;

import android.app.Application;

import com.rui.xb.rui_core.app.Rui;

/**
 * Created by Rui on 2018/4/14.
 */

public class RuiApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Rui.getAppConfigurator(this)
                //.withDefaultLoaderStyle(LoadStyle.BallClipRotateIndicator)
                .configFinish();
    }
}
