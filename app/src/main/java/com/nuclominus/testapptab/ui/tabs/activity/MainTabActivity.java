package com.nuclominus.testapptab.ui.tabs.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.base.ActivityBase;
import com.nuclominus.testapptab.base.FragmentTabsBase;
import com.nuclominus.testapptab.base.TabFragmentAdapterBase;
import com.nuclominus.testapptab.model.DataType;
import com.nuclominus.testapptab.ui.tabs.tab_adapters.MainTabAdapter;
import com.nuclominus.testapptab.ui.tabs.viewmodel.DataViewModel;
import com.nuclominus.testapptab.utility.EventArgs;

public class MainTabActivity extends ActivityBase implements TabLayout.OnTabSelectedListener, FragmentTabsBase.FragmentTabCallback {

    private static final String ACTIVE_TAB = "active_tab";
    public static final String TAB_FRAGMENT_LIST_CATS = "tab_fragment_list_cats";
    public static final String TAB_FRAGMENT_LIST_DOG = "tab_fragment_list_dog";


    private DataViewModel _vm;
    private TabLayout _tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupViewModel();

        if (savedInstanceState == null)
            selectTab(TAB_FRAGMENT_LIST_CATS, getBundleParam(DataType.CAT));
    }

    private void initViews() {
        _tabLayout = findViewById(R.id.tabLayout);
        _tabLayout.addOnTabSelectedListener(this);
    }

    private void setupViewModel() {
        _vm = ViewModelProviders.of(this).get(DataViewModel.class);
    }

    @Override
    protected TabFragmentAdapterBase createTabAdapter() {
        return new MainTabAdapter(getSupportFragmentManager());
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                selectTab(TAB_FRAGMENT_LIST_CATS, getBundleParam(DataType.CAT));
                break;

            case 1:
                selectTab(TAB_FRAGMENT_LIST_DOG, getBundleParam(DataType.DOG));
                break;

            default:
                // do noting
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public DataViewModel getViewModel() {
        return _vm;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ACTIVE_TAB, _tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int position = savedInstanceState.getInt(ACTIVE_TAB);
        TabLayout.Tab tab = _tabLayout.getTabAt(position);
        if (tab != null)
            tab.select();
    }

    private Bundle getBundleParam(int param) {
        Bundle result = new Bundle();
        result.putInt(EventArgs.RequestType, param);
        return result;
    }
}
