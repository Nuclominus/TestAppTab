package com.nuclominus.testapptab.api;

import android.util.Log;

import com.nuclominus.testapptab.api.pojo.DataEntry;
import com.nuclominus.testapptab.api.service.NetworkService;
import com.nuclominus.testapptab.model.DataModel;
import com.nuclominus.testapptab.model.DataType;
import com.nuclominus.testapptab.model.IDataModel;
import com.nuclominus.testapptab.utility.Functions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {
    private static final String TAG = "API";
    private static final String PARAM_CAT = "cat";
    private static final String PARAM_DOG = "dog";

    public static void getDataByType(int type, DataCallback callback) {
        String param = "";
        switch (type) {
            case DataType.CAT:
                param = PARAM_CAT;
                break;

            case DataType.DOG:
                param = PARAM_DOG;
                break;

            default:
                return;
        }
        getData(param, type, callback);
    }

    private static void getData(String param, int dataType, DataCallback callback) {
        Call<DataEntry> call = NetworkService.getInstance()
                .getZimadApi()
                .getData(param);

        call.enqueue(new Callback<DataEntry>() {
            @Override
            public void onResponse(Call<DataEntry> call, Response<DataEntry> response) {
                if (callback == null) return;
                try {
                    if (!response.isSuccessful()) return;
                    DataEntry body = response.body();
                    if (body != null) {
                        List<IDataModel> models = Functions.map(body.getData(), data -> new DataModel(data, body.getMessage(), dataType));
                        callback.complete(models);
                    }
                } catch (Exception ex) {
                    callback.failure();
                    Log.e(TAG, ex.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<DataEntry> call, Throwable t) {
                if (callback == null) return;
                callback.failure();
                Log.e(TAG, "Zimad request error", t);
            }
        });
    }
}
