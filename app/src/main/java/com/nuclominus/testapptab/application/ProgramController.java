package com.nuclominus.testapptab.application;

import android.app.Application;

import com.nuclominus.testapptab.utility.picasso.PicassoHelper;

public class ProgramController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PicassoHelper.init(this);
    }
}
