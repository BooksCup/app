package com.bc.app;

import com.bc.app.utils.PreferencesUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        PreferencesUtil.getInstance().init(this);
    }
}
