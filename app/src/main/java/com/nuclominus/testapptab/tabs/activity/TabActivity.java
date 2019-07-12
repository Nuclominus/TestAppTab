package com.nuclominus.testapptab.tabs.activity;

import android.os.Bundle;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.base.ActivityBase;
import com.nuclominus.testapptab.base.TabFragmentAdapterBase;
import com.nuclominus.testapptab.tabs.tab_adapters.MainTabAdapter;

public class TabActivity extends ActivityBase {

    public static final String TAB_FRAGMENT_FIRST = "tabFirst";
    public static final String TAB_FRAGMENT_SECOND = "tabSecond";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected TabFragmentAdapterBase createTabAdapter() {
        return new MainTabAdapter(getSupportFragmentManager());
    }
}
