package com.nuclominus.testapptab.tabs.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.base.FragmentTabsBase;

public class FragmentFirst extends FragmentTabsBase {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}
