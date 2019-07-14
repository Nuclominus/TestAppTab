package com.nuclominus.testapptab.utility.picasso;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoHelper {

    public static void displayImage(String url, ImageView view) {
        Picasso.get().load(url).transform(new CropSquareTransformation()).resize(220, 220).into(view);
    }
}
