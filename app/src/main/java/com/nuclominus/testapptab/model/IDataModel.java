package com.nuclominus.testapptab.model;

import android.os.Parcelable;

import com.nuclominus.testapptab.view_controller.ISupportImageView;

public interface IDataModel extends IModelBase, Parcelable {
    String getTitle();
    String getMessage();
    void bindView(ISupportImageView view);
}
