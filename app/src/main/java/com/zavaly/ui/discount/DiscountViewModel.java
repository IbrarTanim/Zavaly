package com.zavaly.ui.discount;

import android.content.Context;

import androidx.lifecycle.ViewModel;

public class DiscountViewModel extends ViewModel {

    private DiscountRepository repository;

    public void viewModelInit(Context context) {
        repository = new DiscountRepository(context);
    }
}