package com.nuclominus.testapptab.view_controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.model.IDataModel;

public class ViewData extends ViewControllerBase<IDataModel> implements IViewData, View.OnClickListener {

    private final ViewDataCallBack _callBack;
    private TextView _messageView;
    private TextView _titleView;
    private ImageView _imageView;

    public ViewData(ViewGroup parent, int viewId, ViewDataCallBack callBack) {
        super(parent, viewId);
        _callBack = callBack;
    }

    @Override
    protected void loadControls(View view) {
        super.loadControls(view);
        _imageView = view.findViewById(R.id.item_image);
        _messageView = view.findViewById(R.id.item_message);
        _titleView = view.findViewById(R.id.item_title);
        view.setOnClickListener(this);
    }

    @Override
    protected void afterSetModel(IDataModel model) {
        super.afterSetModel(model);
        model.bindView(this);

        _titleView.setText(model.getTitle());
        _messageView.setText(model.getMessage());
    }

    @Override
    public void onClick(View v) {
        if (_callBack != null)
            _callBack.onItemClick(getModel());
    }

    @Override
    public ImageView getImageView() {
        return _imageView;
    }

    public interface ViewDataCallBack {
        void onItemClick(IDataModel model);
    }
}
