package com.nuclominus.testapptab.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.nuclominus.testapptab.ui.tabs.viewmodel.DataViewModel;

public class FragmentTabsBase extends Fragment {

    public static final String NESTED_FRAGMENTS = "nestedFragments";

    private TabFragmentAdapterBase _tabsAdapter;
    private FragmentTabCallback _activityCallback;


    protected TabFragmentAdapterBase createTabAdapter() {
        return null;
    }

    private TabFragmentAdapterBase getTabAdapter() {
        if (_tabsAdapter == null) {
            _tabsAdapter = createTabAdapter();
        }
        return _tabsAdapter;
    }

    public boolean deactivateTab() {
        return onTabDeactivated();
    }

    public void activateTab(Bundle args) {
        onTabActivated(args);
    }

    protected void onTabActivated(Bundle args) {
        FragmentTabsBase currentTab = getCurrentTab();
        if(currentTab != null) {
            currentTab.onTabActivated(args);
        }
    }

    protected boolean onTabDeactivated() {
        FragmentTabsBase currentTab = getCurrentTab();
        return currentTab == null || currentTab.onTabDeactivated();
    }

    @SuppressWarnings("unchecked")
    protected <T extends FragmentTabsBase> T getCurrentTab() {
        TabFragmentAdapterBase adapter = _tabsAdapter;
        return adapter == null ? null : (T) adapter.getCurrentFragment();
    }

    public boolean selectTab(String fragmentTag) {
        return selectTab(fragmentTag, null);
    }

    public boolean selectTab(String fragmentTag, Bundle bundle) {
        return selectTab(fragmentTag, bundle, false);
    }

    public boolean selectTab(String fragmentTag, Bundle bundle, boolean replace) {
        boolean result = false;
        TabFragmentAdapterBase adapter = getTabAdapter();
        if (adapter != null) {
            result = adapter.selectTab(fragmentTag, bundle, replace);
        }
        return result;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onRestoreInstanceState(savedInstanceState);
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            TabFragmentAdapterBase adapter = getTabAdapter();
            if (adapter != null) {
                Bundle nestedBundle = bundle.getBundle(NESTED_FRAGMENTS);
                if (nestedBundle != null) {
                    nestedBundle.setClassLoader(bundle.getClassLoader());
                    adapter.restoreState(nestedBundle);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        TabFragmentAdapterBase adapter = _tabsAdapter;
        if (adapter != null) {
            Bundle nestedBundle = adapter.saveState(outState.getClassLoader());
            outState.putBundle(NESTED_FRAGMENTS, nestedBundle);
        }
    }

    public boolean onTabNavigation(){
        FragmentTabsBase currentTab = getCurrentTab();
        return currentTab != null && currentTab.onTabNavigation();
    }

    @SuppressWarnings("unchecked")
    public <T extends FragmentTabCallback> T getActivityCallback() {
        return (T) _activityCallback;
    }

    @SuppressWarnings("unchecked")
    public <T> T getParentCallback() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            return getActivityCallback();
        }

        try {
            return (T) parentFragment;
        } catch (Exception ex) {
            //nothing to do
        }
        return null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (context instanceof FragmentTabCallback) {
                _activityCallback = (FragmentTabCallback) context;
            }
        } catch (Exception ex) {
            //nothing to do
        }
    }

    public interface FragmentTabCallback {
        DataViewModel getViewModel();
    }
}
