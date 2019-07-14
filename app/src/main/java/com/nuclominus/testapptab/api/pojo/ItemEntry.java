package com.nuclominus.testapptab.api.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemEntry implements Parcelable {
    @SerializedName("url")
    @Expose
    private String _url;
    @SerializedName("title")
    @Expose
    private String _title;

    public static final Creator<ItemEntry> CREATOR = new Creator<ItemEntry>() {
        @Override
        public ItemEntry createFromParcel(Parcel in) {
            return new ItemEntry(in);
        }

        @Override
        public ItemEntry[] newArray(int size) {
            return new ItemEntry[size];
        }
    };

    private ItemEntry(Parcel in) {
        _url = in.readString();
        _title = in.readString();
    }

    public String getUrl() {
        return _url;
    }

//    public void setUrl(String url) {
//        _url = url;
//    }

    public String getTitle() {
        return _title;
    }

//    public void setTitle(String title) {
//        _title = title;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_url);
        dest.writeString(_title);
    }
}
