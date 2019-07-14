package com.nuclominus.testapptab.factory;

import android.view.ViewGroup;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.model.IDataModel;
import com.nuclominus.testapptab.view_controller.IViewData;
import com.nuclominus.testapptab.view_controller.ViewData;

public class ViewFactoryDataList implements IViewFactoryDataList {
    public final static int VIEW_ALL = 0;
    private final static int VIEW_CAT = 1;
    private final static int VIEW_DOG = 2;

    private final ViewData.ViewDataCallBack _callBack;

    public ViewFactoryDataList(ViewData.ViewDataCallBack callback) {
        _callBack = callback;
    }

    @Override
    public IViewData create(ViewGroup parent, int viewType) {
        IViewData result = null;
        switch (viewType){
            case VIEW_CAT:
            case VIEW_DOG:
                result = new ViewData(parent, R.layout.list_item, _callBack);
        }
        return result;
        // or since we have the same twist for both lists.
//        return new ViewData(parent, R.layout.list_item, _callBack);
    }

    @Override
    public int getViewType(IDataModel model) {
        return model.getViewType();
    }
}
