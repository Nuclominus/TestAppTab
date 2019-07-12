package com.nuclominus.testapptab.tabs.tab_adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.nuclominus.testapptab.base.FragmentTabsBase;
import com.nuclominus.testapptab.base.TabFragmentAdapterBase;
import com.nuclominus.testapptab.tabs.activity.TabActivity;
import com.nuclominus.testapptab.tabs.fragments.FragmentFirst;
import com.nuclominus.testapptab.tabs.fragments.FragmentSecond;

public class MainTabAdapter extends TabFragmentAdapterBase {

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public FragmentTabsBase instantiateFragment(String fragmentTag, Bundle fragmentState) {
        FragmentTabsBase result = null;
        switch (fragmentTag) {
            case TabActivity.TAB_FRAGMENT_FIRST:
                result = new FragmentFirst();
                break;
            case TabActivity.TAB_FRAGMENT_SECOND:
                result = new FragmentSecond();
                break;
        }
        if (result != null) {
            result.setArguments(fragmentState);
        }
        return result;
    }
}
