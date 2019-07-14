package com.nuclominus.testapptab.ui.tabs.tab_adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.nuclominus.testapptab.base.FragmentTabsBase;
import com.nuclominus.testapptab.base.TabFragmentAdapterBase;
import com.nuclominus.testapptab.ui.tabs.fragments.FragmentDetails;

public class DetailsTabAdapter extends TabFragmentAdapterBase {

    public DetailsTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public FragmentTabsBase instantiateFragment(String fragmentTag, Bundle fragmentState) {
        FragmentTabsBase result = new FragmentDetails();
        result.setArguments(fragmentState);
        return result;
    }
}
