package com.nuclominus.testapptab.model;

import com.nuclominus.testapptab.view_controller.IViewData;

public interface IDataModel extends IModelBase{
    String getTitle();
    String getMessage();
    void bindView(IViewData view);
}
