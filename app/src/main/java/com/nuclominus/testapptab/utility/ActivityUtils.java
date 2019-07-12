package com.nuclominus.testapptab.utility;

import android.content.Intent;
import android.os.Bundle;

public class ActivityUtils {

    private static final int FLAG_FRAGMENT_NONE = 0;
    public static final int FLAG_FRAGMENT_REPLACE = 1;

    private ActivityUtils() {

    }

    public static Bundle addFragment(Intent intent, String fragmentTag){
        return addFragment(intent, fragmentTag, FLAG_FRAGMENT_NONE);
    }

    public static Bundle addFragment(Intent intent, String fragmentTag, int flags){
        Bundle bundle = new Bundle();
        bundle.putString(EventArgs.FragmentTag, fragmentTag);
        bundle.putInt(EventArgs.FragmentFlags, flags);
        intent.putExtra(EventArgs.Fragment, bundle);
        return bundle;
    }

    public static Bundle addFragment(Intent intent, Bundle args){
        intent.putExtra(EventArgs.Fragment, args);
        return args;
    }

    public static Bundle getFragmentBundle(String fragmentTag){
        Bundle bundle = new Bundle();
        bundle.putString(EventArgs.FragmentTag, fragmentTag);
        bundle.putInt(EventArgs.FragmentFlags, 0);
        return bundle;
    }

    public static Bundle addFragment(Bundle args, String fragmentTag) {
        return addFragment(args, fragmentTag, 0);
    }

    public static Bundle addFragment(Bundle args, String fragmentTag, int flags){
        Bundle bundle = new Bundle();
        bundle.putString(EventArgs.FragmentTag, fragmentTag);
        bundle.putInt(EventArgs.FragmentFlags, flags);
        args.putBundle(EventArgs.Fragment, bundle);
        return bundle;
    }
}
