package com.nuclominus.testapptab.ui.tabs.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.base.FragmentTabsBase;
import com.nuclominus.testapptab.model.IDetailsModel;
import com.nuclominus.testapptab.utility.EventArgs;

public class FragmentDetails extends FragmentTabsBase {

    private ImageView _imageView;
    private TextView _title;
    private TextView _message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initControls(getArguments());
    }

    private void initControls(Bundle arguments) {
        if (arguments == null) return;

        IDetailsModel model = arguments.getParcelable(EventArgs.DetailsArgs);
        if (model == null) return;

        model.bindDetailsView(() -> _imageView);
        _title.setText(model.getTitle());
        _message.setText(model.getMessage());
    }

    private void initView(View view) {
        _imageView = view.findViewById(R.id.image_detail);
        _title = view.findViewById(R.id.title_detail);
        _message = view.findViewById(R.id.message_detail);
    }

}
