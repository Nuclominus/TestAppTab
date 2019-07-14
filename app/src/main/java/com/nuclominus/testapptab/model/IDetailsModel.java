package com.nuclominus.testapptab.model;

import com.nuclominus.testapptab.view_controller.ISupportImageView;

public interface IDetailsModel {
    String getTitle();
    String getMessage();
    void bindDetailsView(ISupportImageView view);
}
