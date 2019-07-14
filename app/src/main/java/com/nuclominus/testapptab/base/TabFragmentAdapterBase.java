package com.nuclominus.testapptab.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.nuclominus.testapptab.R;

public abstract class TabFragmentAdapterBase {

    private static final String FRAGMENT_PRIMARY = "primaryFragment";
    private final FragmentManager _fm;

    public TabFragmentAdapterBase(FragmentManager fm) {
        _fm = fm;
    }

    public abstract FragmentTabsBase instantiateFragment(String fragmentTag, Bundle fragmentState);

    private FragmentTabsBase _currentFragment;

    FragmentTabsBase getCurrentFragment() {
        return _currentFragment;
    }

    private void setCurrentFragment(String fragmentTag) {
        _currentFragment = (FragmentTabsBase) _fm.findFragmentByTag(fragmentTag);
    }

    void selectTab(String fragmentTag, Bundle fragmentState, boolean replace){

        FragmentManager fragmentManager = _fm;
        String previousTag = null;
        if(_currentFragment != null){
            if(!_currentFragment.deactivateTab()){
                return;
            }
            previousTag = _currentFragment.getTag();
        }

        FragmentTabsBase newFragment = (FragmentTabsBase) fragmentManager.findFragmentByTag(fragmentTag);
        boolean isNewInstance = false;
        if (newFragment == null || (replace && newFragment.equals(_currentFragment))) {
            newFragment = instantiateFragment(fragmentTag, getArgs(fragmentState));
            isNewInstance = true;
        }

        if(newFragment == null) {
            return;
        }

        FragmentTransaction ft = fragmentManager.beginTransaction();
        initTransaction(ft, _currentFragment, newFragment, fragmentTag);
        if(replace){
            ft.replace(R.id.pnlContent, newFragment, fragmentTag);

        } else {
            if(!newFragment.equals(_currentFragment) && _currentFragment != null) {
                ft.hide(_currentFragment);
            }

            if(!newFragment.isAdded()){
                ft.add(R.id.pnlContent, newFragment, fragmentTag);
            }
        }
        ft.show(newFragment);
        commitTransaction(ft);

        if(!isNewInstance) {
            if(fragmentTag.equals(previousTag)){
                newFragment.onTabNavigation();
            }else {
                newFragment.activateTab(fragmentState);
            }
        }
        _currentFragment = newFragment;
    }

    private void initTransaction(FragmentTransaction ft, Fragment currentFragment, Fragment newFragment, String newFragmentTag){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addSharedElement(ft, currentFragment, newFragment, newFragmentTag);
        }
    }

    private void addSharedElement(FragmentTransaction ft, Fragment currentFragment, Fragment newFragment, String newFragmentTag){

    }

    private void commitTransaction(FragmentTransaction ft){
        ft.commitAllowingStateLoss();
    }

    void restoreState(Bundle bundle) {
        if (bundle != null) {
            String primaryTag = bundle.getString(FRAGMENT_PRIMARY, null);
            if(primaryTag != null) {
                setCurrentFragment(primaryTag);
            }
        }
    }

    Bundle saveState(ClassLoader classLoader) {
        Bundle result = new Bundle(classLoader);
        savePrimaryFragment(result);
        return result;
    }

    private void savePrimaryFragment(Bundle bundle){
        if(_currentFragment != null) {
            String fragmentTag = _currentFragment.getTag();
            if(fragmentTag != null) {
                bundle.putString(FRAGMENT_PRIMARY, _currentFragment.getTag());
            }
        }
    }

    private Bundle getArgs(Bundle bundle){
        return bundle == null ? new Bundle() : bundle;
    }
}
