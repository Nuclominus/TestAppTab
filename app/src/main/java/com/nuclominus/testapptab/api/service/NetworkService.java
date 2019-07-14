package com.nuclominus.testapptab.api.service;

import com.nuclominus.testapptab.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService INSTANCE;
    private Retrofit _retrofit;

    public static NetworkService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkService();
        }
        return INSTANCE;
    }

    private NetworkService(){
        _retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ZimadAPI getZimadApi(){
        return _retrofit.create(ZimadAPI.class);
    }

}
