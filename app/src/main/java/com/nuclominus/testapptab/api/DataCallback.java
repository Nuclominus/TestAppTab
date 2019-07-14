package com.nuclominus.testapptab.api;

import com.nuclominus.testapptab.model.IDataModel;

import java.util.List;

public abstract class DataCallback {
    public void complete(List<IDataModel> data){}
    public void failure(){}
}
