package com.nuclominus.testapptab.adapter;

import android.view.ViewGroup;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.factory.IViewFactoryDataList;
import com.nuclominus.testapptab.model.IDataModel;
import com.nuclominus.testapptab.view_controller.IViewData;
import com.nuclominus.testapptab.view_controller.ViewData;

import java.util.List;

public class AdapterListData extends AbstractSimpleAdapter<IViewData, IViewFactoryDataList, IDataModel> {

    public AdapterListData(IViewFactoryDataList iViewFactoryData) {
        super(iViewFactoryData, 100);
    }

    @Override
    protected IViewData createUnsupportedView(ViewGroup parent) {
        return new ViewData(parent, R.layout.empty_item, null);
    }

    @Override
    protected DiffResultCallback<IDataModel> getDiffCallback(List<IDataModel> oldItems, List<IDataModel> newItems) {
        return new DiffResultCallback<IDataModel>(oldItems, newItems) {
            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return false; //if the data would have oldItems.get(oldItemPosition).getEntryId() == newItems.get(newItemPosition).getEntryId();
            }
        };
    }
}
