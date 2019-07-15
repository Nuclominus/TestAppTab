package com.nuclominus.testapptab.ui.tabs.fragments;

import android.arch.lifecycle.MutableLiveData;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuclominus.testapptab.R;
import com.nuclominus.testapptab.adapter.AdapterListData;
import com.nuclominus.testapptab.base.AttractionsRecyclerView;
import com.nuclominus.testapptab.base.FragmentTabsBase;
import com.nuclominus.testapptab.factory.ViewFactoryDataList;
import com.nuclominus.testapptab.model.IDataModel;
import com.nuclominus.testapptab.ui.tabs.activity.ActivityDetails;
import com.nuclominus.testapptab.ui.tabs.viewmodel.DataViewModel;
import com.nuclominus.testapptab.utility.EventArgs;
import com.nuclominus.testapptab.view_controller.ViewData;

import java.util.List;

public class FragmentList extends FragmentTabsBase implements ViewData.ViewDataCallBack {

    private static final String BUNDLE_RECYCLER_LAYOUT = "recycler_params";
    AttractionsRecyclerView _recyclerView;
    AdapterListData _adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null)
            initData(arguments.getInt(EventArgs.RequestType));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
    }

    private void initData(int type) {
        DataViewModel vm = getActivityCallback().getViewModel();
        MutableLiveData<List<IDataModel>> liveData = vm.getLiveData(type);
        if (liveData != null)
            liveData.observe(this, modes -> _adapter.mergeItems(modes));
        vm.loadData(type);
    }

    private void initViews(View view) {
        _adapter = new AdapterListData(new ViewFactoryDataList(this));
        _recyclerView = view.findViewById(R.id.dataList);
        _recyclerView.setEmptyView(view.findViewById(R.id.emptyListText));
        LinearLayoutManager layoutManager = new LinearLayoutManager(_recyclerView.getContext());
        _recyclerView.setLayoutManager(layoutManager);
        _recyclerView.setItemViewCacheSize(40);
        _recyclerView.setAdapter(_adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (_recyclerView == null || _recyclerView.getLayoutManager() == null) return;

        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, _recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (_recyclerView == null || _recyclerView.getLayoutManager() == null || bundle == null)
            return;

        Parcelable listState = bundle.getParcelable(BUNDLE_RECYCLER_LAYOUT);
        if (listState != null) {
            _recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onItemClick(IDataModel model) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EventArgs.DetailsArgs, model);
        startActivityWithTransition(activity -> ActivityDetails.createIntent(getContext(), bundle));
    }
}
