package com.nuclominus.testapptab.model;

import android.os.Parcel;

import com.nuclominus.testapptab.api.pojo.ItemEntry;
import com.nuclominus.testapptab.factory.ViewFactoryDataList;
import com.nuclominus.testapptab.utility.picasso.PicassoHelper;
import com.nuclominus.testapptab.view_controller.ISupportImageView;

public class DataModel implements IDataModel, IDetailsModel {

    private final ItemEntry _entry;
    private final int _type;
    private final String _message;

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public DataModel(ItemEntry data, String message, int dataType) {
        _entry = data;
        _type = dataType;
        _message = message;
    }

    private DataModel(Parcel parcel) {
        _entry = parcel.readParcelable(ItemEntry.class.getClassLoader());
        _type = parcel.readInt();
        _message = parcel.readString();
    }

    @Override
    public String getTitle() {
        return _entry.getTitle();
    }

    @Override
    public String getMessage() {
        return _message;
    }

    @Override
    public void bindDetailsView(ISupportImageView view) {
        PicassoHelper.displayDetails(_entry.getUrl(), view.getImageView());
    }

    @Override
    public void bindView(ISupportImageView view) {
        PicassoHelper.displayImage(_entry.getUrl(), view.getImageView());
    }

//    public int getType() {
//        return _type;
//    }

    @Override
    public int getViewType() {
        return ViewFactoryDataList.VIEW_ALL;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(_entry, flags);
        dest.writeInt(_type);
        dest.writeString(_message);
    }
}
