package com.zavaly.ui.ordersui.orders;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zavaly.models.orders.OrdersResponse;

public class OrdersViewModel extends ViewModel {

    private OrdersRepository ordersRepository;

    public void initViewModel(Context context) {

        ordersRepository = new OrdersRepository(context);

    }

    public MutableLiveData<Integer> getErrorLiveData() {
        return ordersRepository.getErrorLiveData();
    }

    public MutableLiveData<OrdersResponse> getOrdersLiveData() {
        return ordersRepository.getOrdersLiveData();
    }
}