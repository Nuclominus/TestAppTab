package com.nuclominus.testapptab.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.nuclominus.testapptab.model.IModelBase;
import com.nuclominus.testapptab.view_controller.IViewControllerBase;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSimpleAdapter<TView extends IViewControllerBase<TModel>,
        TViewFactory extends IViewFactoryBase<TView, TModel>,
        TModel extends IModelBase> extends RecyclerView.Adapter<AbstractSimpleAdapter.ViewHolder<TView, TModel>> {

    static class ViewHolder<TView extends IViewControllerBase<TModel>, TModel extends IModelBase>
            extends RecyclerView.ViewHolder {
        TView _viewController;
        TModel _model;

        ViewHolder(TView viewController) {
            super(viewController.getView());
            _viewController = viewController;
        }

    }

    private final TViewFactory _factory;
    private final List<TModel> _items;

    AbstractSimpleAdapter(TViewFactory factory, int defaultCapacity) {
        _factory = factory;
        _items = new ArrayList<>(defaultCapacity);
    }

    private TViewFactory getFactory() {
        return _factory;
    }

    @Override
    public long getItemId(int position){
        return RecyclerView.NO_ID;
    }

    @Override
    public int getItemViewType(int position) {
        TModel item = _items.get(position);
        return getFactory().getViewType(item);
    }

    @Override
    public int getItemCount() {
        return _items.size();
    }

    protected abstract TView createUnsupportedView(ViewGroup parent);

    @NonNull
    @Override
    public AbstractSimpleAdapter.ViewHolder<TView, TModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TView view = getFactory().create(parent, viewType);
        return new ViewHolder<>(view == null ? createUnsupportedView(parent) : view);
    }

    @Override
    public final void onBindViewHolder(@NonNull AbstractSimpleAdapter.ViewHolder<TView, TModel> holder, int position) {

    }

    @Override
    public final void onBindViewHolder(@NonNull AbstractSimpleAdapter.ViewHolder<TView, TModel> holder, int position, @NonNull List<Object> payloads) {
        TModel model = _items.get(position);
        TView controller = holder._viewController;

        if (payloads.isEmpty()) {
            holder._model = model;
        }else {
            controller.receivePayloads(payloads, model);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder<TView, TModel> holder) {
        super.onViewAttachedToWindow(holder);
        holder._viewController.setModel(holder._model);

    }

    public void setItems(List<TModel> items) {
        _items.clear();
        _items.addAll(items);
        notifyItemRangeInserted(0, items.size());
    }

    public void clearItems() {
        mergeItems(new ArrayList<>());
    }

    public void mergeItems(final List<TModel> items) {
        mergeItems(items, null);
    }

    private void mergeItems(final List<TModel> items, ListUpdateCallback callback) {
        mergeItems(items, callback, false);
    }

    private void replaceItems(List<TModel> items){
        _items.clear();
        _items.addAll(items);
    }

    private void mergeItems(final List<TModel> items, ListUpdateCallback callback, boolean detectMoves) {
        DiffResultCallback<TModel> diffCallback = getDiffCallback(new ArrayList<>(this._items), items);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback, detectMoves);
        replaceItems(items);
        if (callback == null) {
            diffResult.dispatchUpdatesTo(this);
        } else {
            diffResult.dispatchUpdatesTo(callback);
        }
    }

    protected abstract DiffResultCallback<TModel> getDiffCallback(List<TModel> oldItems, List<TModel> newItems);

    protected static abstract class DiffResultCallback<TModel extends IModelBase> extends DiffUtil.Callback {

        List<TModel> _oldItems;
        List<TModel> _newItems;

        DiffResultCallback(List<TModel> oldItems, List<TModel> newItems) {
            _oldItems = oldItems;
            _newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return _oldItems.size();
        }

        @Override
        public int getNewListSize() {
            return _newItems.size();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return true;
        }
    }

}
