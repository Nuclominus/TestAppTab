package com.nuclominus.testapptab.api.service;

import com.nuclominus.testapptab.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService INSTANCE;
    private Retrofit retrofit;

    public static NetworkService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkService();
        }
        return INSTANCE;
    }

    private NetworkService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ZimadAPI getZimadApi(){
        return retrofit.create(ZimadAPI.class);
    }

}
