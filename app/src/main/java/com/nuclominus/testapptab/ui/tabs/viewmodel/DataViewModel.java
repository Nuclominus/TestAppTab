package com.nuclominus.testapptab.ui.tabs.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.nuclominus.testapptab.api.ApiManager;
import com.nuclominus.testapptab.api.DataCallback;
import com.nuclominus.testapptab.model.DataType;
import com.nuclominus.testapptab.model.IDataModel;

import java.util.List;

public class DataViewModel extends ViewModel {

    private MutableLiveData<List<IDataModel>> liveDataCats = new MutableLiveData<>();
    private MutableLiveData<List<IDataModel>> liveDataDogs = new MutableLiveData<>();

    public void loadDate(int dataType) {
        ApiManager.getDataByType(dataType, new DataCallback() {
            @Override
            public void complete(List<IDataModel> data) {
                if (!data.isEmpty()) {
                    MutableLiveData<List<IDataModel>> liveData = getLiveData(dataType);
                    if (liveData != null)
                        liveData.postValue(data);
                }
            }

            @Override
            public void failure() {
                Log.e("API", "Error take a data");
            }
        });
    }

    public MutableLiveData<List<IDataModel>> getLiveData(int type) {
        switch (type) {
            case DataType.CAT:
                return liveDataCats;

            case DataType.DOG:
                return liveDataDogs;

            default:
                return null;
        }
    }
}
