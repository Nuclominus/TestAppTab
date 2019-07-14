package com.nuclominus.testapptab.utility.picasso;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

public class PicassoHelper {

    public static void init(Context context) {
        Picasso picasso = new Picasso.Builder(context)
                .memoryCache(new LruCache(8 * 1024 * 1024))
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    public static void displayImage(String imageUrl, ImageView view) {
        Picasso.get().load(imageUrl)
                .transform(new CropSquareTransformation())
                .resize(220, 220)
                .into(view);
    }

    public static void displayDetails(String imageUrl, ImageView view) {
        Picasso.get().load(imageUrl)
                .fit()
                .centerInside()
                .into(view);
    }

}
