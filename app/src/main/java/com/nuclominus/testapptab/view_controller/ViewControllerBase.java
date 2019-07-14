package com.nuclominus.testapptab.view_controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nuclominus.testapptab.model.IModelBase;

import java.util.List;

public class ViewControllerBase<TModel extends IModelBase> implements IViewControllerBase<TModel>{

    private final View _view;
    private final Context _context;
    private Object _callback;
    private TModel _model;

    public ViewControllerBase(Context context, View view) {
        _view = view;
        _context = context;
        loadControls(view);
        initControls(view);
    }

    ViewControllerBase(ViewGroup parent, int viewId) {
        _context = parent.getContext();
        _view = inflate(_context, parent, viewId);
        loadControls(_view);
        initControls(_view);
    }

    private static View inflate(Context context , ViewGroup parent, int id){
        return LayoutInflater.from(context).inflate(id, parent, false);
    }

    public View getView() {
        return _view;
    }

    public Context getContext() {
        return _context;
    }

    public void setCallback(Object callback) {
        _callback = callback;
    }

    @SuppressWarnings("unchecked")
    public <T> T getCallback() {
        return (T)_callback;
    }

    public void setModel(TModel model) {
        beforeSetModel(model);
        _model =  model;
        afterSetModel(model);
    }

    protected void loadControls(View view) {
    }

    protected void initControls(View view) {
    }

    protected  void afterSetModel(TModel model) {

    }

    protected void beforeSetModel(TModel model) {
    }

    @SuppressWarnings("unchecked")
    public <T extends TModel> T getModel() {
        return (T) _model;
    }

    @Override
    public void receivePayloads(List<Object> payloads, TModel model) {

    }
}
