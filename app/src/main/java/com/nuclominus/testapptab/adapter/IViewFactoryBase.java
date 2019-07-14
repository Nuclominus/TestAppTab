package com.nuclominus.testapptab.adapter;

import android.view.ViewGroup;
import com.nuclominus.testapptab.model.IModelBase;
import com.nuclominus.testapptab.view_controller.IViewControllerBase;

public interface IViewFactoryBase<TView extends IViewControllerBase, TModel extends IModelBase> {

    TView create(ViewGroup parent, int viewType);
    int getViewType(TModel model);
}
