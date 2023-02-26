package com.example.studyproject.common;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import androidx.core.util.Supplier;

public class ViewModelFactory<VM extends ViewModel> implements ViewModelProvider.Factory {

    private final Supplier<VM> supplier;

    public ViewModelFactory(Supplier<VM> supplier) {
        this.supplier = supplier;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) supplier.get();
    }
}
