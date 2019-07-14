package com.nuclominus.testapptab.ui.tabs.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.base.ActivityBase;
import com.nuclominus.testapptab.base.TabFragmentAdapterBase;
import com.nuclominus.testapptab.ui.tabs.tab_adapters.DetailsTabAdapter;

public class ActivityDetails extends ActivityBase {

    private static final String TAB_FRAGMENT_DETAILS = "tab_adapter_details";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (savedInstanceState == null) {
            selectTab(TAB_FRAGMENT_DETAILS, getIntent().getExtras());
        }
    }

    @Override
    protected TabFragmentAdapterBase createTabAdapter() {
        return new DetailsTabAdapter(getSupportFragmentManager());
    }

    public static Intent createIntent(Context context, Bundle data) {
        Intent result = new Intent(context, ActivityDetails.class);
        result.putExtras(data);
        return result;
    }
}
