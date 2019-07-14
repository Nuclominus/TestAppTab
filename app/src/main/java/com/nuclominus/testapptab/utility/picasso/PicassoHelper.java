package com.nuclominus.testapptab.utility.picasso;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoHelper {

    public static void displayImage(String imageUrl, ImageView view) {
        Picasso.get().load(imageUrl).transform(new CropSquareTransformation()).resize(220, 220).into(view);
    }

    public static void displayDetails(String imageUrl, ImageView view){
        Picasso.get().load(imageUrl).fit().centerCrop().into(view);
    }
}
