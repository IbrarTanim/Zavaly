package com.zavaly.ui.ordersui.ordersdetails;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zavaly.models.ordersdetails.OrderDetailsResponse;

public class OrderDetailsViewModel extends ViewModel {

    private OrdersDetailsRepository repository;

    public void initViewModel(Context context, String orderCode) {

        repository = new OrdersDetailsRepository(context, orderCode);

    }

    public MutableLiveData<Integer> getErrorLiveData() {
        return repository.getErrorLiveData();
    }

    public MutableLiveData<OrderDetailsResponse> getResponseLiveData() {
        return repository.getResponseLiveData();
    }
}