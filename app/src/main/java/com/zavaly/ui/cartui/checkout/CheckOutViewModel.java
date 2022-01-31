package com.zavaly.ui.cartui.checkout;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

public class CheckOutViewModel extends ViewModel {

    private CheckOutRepository repository;

    public void viewModelInit(Context context) {
        repository = new CheckOutRepository(context);
    }

    public MutableLiveData<Integer> checkoutConfirm(HashMap<String, String> params) {

        return repository.checkoutConfirm(params);

    }

}