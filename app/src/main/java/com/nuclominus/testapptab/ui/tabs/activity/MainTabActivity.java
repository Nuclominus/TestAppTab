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

public class MainTabActivity extends ActivityBase implements TabLayout.OnTabSelectedListener, FragmentTabsBase.FragmentTabCallback{

    public static final String TAB_FRAGMENT_LIST_CATS = "tab_fragment_list_cats";
    public static final String TAB_FRAGMENT_LIST_DOG = "tab_fragment_list_dog";
    public static final String TAB_FRAGMENT_DETAILS = "tab_fragment_details";

    private DataViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            setContentView(R.layout.activity_main);
            initViews();
            setupViewModel();
            selectTab(TAB_FRAGMENT_LIST_CATS, getBundleParam(DataType.CAT));
        }
    }

    private void initViews() {
        ((TabLayout) findViewById(R.id.tabLayout)).addOnTabSelectedListener(this);
    }

    private void setupViewModel(){
        vm = ViewModelProviders.of(this).get(DataViewModel.class);
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
    public void onTabUnselected(TabLayout.Tab tab) {}

    @Override
    public void onTabReselected(TabLayout.Tab tab) {}

    @Override
    public DataViewModel getViewModel() {
        return vm;
    }

    private Bundle getBundleParam(int param){
        Bundle result = new Bundle();
        result.putInt(EventArgs.RequestType, param);
        return result;
    }
}
