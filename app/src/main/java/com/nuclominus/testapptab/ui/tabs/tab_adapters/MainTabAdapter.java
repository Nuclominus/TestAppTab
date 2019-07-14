package com.nuclominus.testapptab.ui.tabs.tab_adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.nuclominus.testapptab.base.FragmentTabsBase;
import com.nuclominus.testapptab.base.TabFragmentAdapterBase;
import com.nuclominus.testapptab.ui.tabs.activity.MainTabActivity;
import com.nuclominus.testapptab.ui.tabs.fragments.FragmentList;

public class MainTabAdapter extends TabFragmentAdapterBase {

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public FragmentTabsBase instantiateFragment(String fragmentTag, Bundle fragmentState) {
        FragmentTabsBase result = null;
        switch (fragmentTag) {
            case MainTabActivity.TAB_FRAGMENT_LIST_CATS:
            case MainTabActivity.TAB_FRAGMENT_LIST_DOG:
                result = new FragmentList();
                break;
            case MainTabActivity.TAB_FRAGMENT_DETAILS:
                // result = new FragmentDetails();
                break;
        }
        if (result != null) {
            result.setArguments(fragmentState);
        }
        return result;
    }
}
