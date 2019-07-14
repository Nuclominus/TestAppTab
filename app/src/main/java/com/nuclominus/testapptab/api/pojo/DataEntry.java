package com.nuclominus.testapptab.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataEntry {
    @SerializedName("message")
    @Expose
    private String _message;
    @SerializedName("data")
    @Expose
    private List<ItemEntry> _data = null;

    public String getMessage() {
        return _message;
    }

    public void setMessage(String message) {
        _message = message;
    }

    public List<ItemEntry> getData() {
        return _data;
    }

    public void setData(List<ItemEntry> data) {
        _data = data;
    }
}
