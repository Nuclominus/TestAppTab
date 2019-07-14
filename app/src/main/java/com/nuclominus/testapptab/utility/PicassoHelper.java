package com.nuclominus.testapptab.utility;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoHelper {

    public static void displayImage(String url, ImageView view) {
        Picasso.get().load(url).into(view);
    }
}
