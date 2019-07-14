package com.nuclominus.testapptab.view_controller;

import android.view.View;
import com.nuclominus.testapptab.model.IModelBase;
import java.util.List;

public interface IViewControllerBase<TModel extends IModelBase> {

    View getView();
    void setModel(TModel model);
    void receivePayloads(List<Object> payloads, TModel model);
}
