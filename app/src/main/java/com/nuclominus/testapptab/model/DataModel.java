package com.nuclominus.testapptab.model;

import com.nuclominus.testapptab.api.pojo.ItemEntry;
import com.nuclominus.testapptab.factory.ViewFactoryDataList;
import com.nuclominus.testapptab.utility.picasso.PicassoHelper;
import com.nuclominus.testapptab.view_controller.IViewData;

public class DataModel implements IDataModel {

    private ItemEntry _entry;
    private int _type;
    private String _message;

    public DataModel(ItemEntry data, String message, int dataType) {
        _entry = data;
        _type = dataType;
        _message = message;
    }

    @Override
    public String getTitle() {
        return _entry.getTitle();
    }

    @Override
    public String getMessage() {
        return _message;
    }

    @Override
    public void bindView(IViewData view) {
        PicassoHelper.displayImage(_entry.getUrl(),view.getImageView());
    }

    public int getType() {
        return _type;
    }

    @Override
    public int getViewType() {
        return ViewFactoryDataList.VIEW_ALL;
    }
}
