package com.nuclominus.testapptab.api.service;

import com.nuclominus.testapptab.api.pojo.DataEntry;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZimadAPI {
    @GET("xim/api.php")
    Call<DataEntry> getData(@Query("query") String param);
}
