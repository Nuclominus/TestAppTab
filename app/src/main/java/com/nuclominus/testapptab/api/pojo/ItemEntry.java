package com.nuclominus.testapptab.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemEntry {
    @SerializedName("url")
    @Expose
    private String _url;
    @SerializedName("title")
    @Expose
    private String _title;

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }
}
