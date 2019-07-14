package com.nuclominus.testapptab.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class ActivityBase extends AppCompatActivity{
    private TabFragmentAdapterBase _tabAdapter;

    protected TabFragmentAdapterBase createTabAdapter() {
        return null;
    }

    private TabFragmentAdapterBase getTabAdapter() {
        if (_tabAdapter == null) {
            _tabAdapter = createTabAdapter();
        }
        return _tabAdapter;
    }

    protected void selectTab(String fragmentTag) {
        selectTab(fragmentTag, null);
    }

    protected void selectTab(String fragmentTag, Bundle fragmentState) {
        selectTab(fragmentTag, fragmentState, false);
    }

    protected void selectTab(String fragmentTag, Bundle fragmentState, boolean replace) {
        getTabAdapter().selectTab(fragmentTag, fragmentState, replace);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TabFragmentAdapterBase tabAdapter = getTabAdapter();
        if (tabAdapter != null) {
            tabAdapter.restoreState(savedInstanceState.getBundle(FragmentTabsBase.NESTED_FRAGMENTS));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        TabFragmentAdapterBase tabAdapter = _tabAdapter;
        if (tabAdapter != null) {
            ClassLoader classLoader = outState.getClassLoader();
            Bundle bundle = tabAdapter.saveState(classLoader);
            outState.putBundle(FragmentTabsBase.NESTED_FRAGMENTS, bundle);
        }
        super.onSaveInstanceState(outState);
    }

}
