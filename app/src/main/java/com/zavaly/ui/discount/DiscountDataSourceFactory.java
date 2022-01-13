package com.zavaly.ui.discount;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class DiscountDataSourceFactory extends DataSource.Factory {

    private Context context;

    private MutableLiveData<DiscountDataSource> discountDataSourceMutableLiveData;

    public DiscountDataSourceFactory(Context context) {
        this.context = context;
        discountDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

        DiscountDataSource discountDataSource = new DiscountDataSource(context);
        discountDataSourceMutableLiveData.postValue(discountDataSource);

        return discountDataSource;

    }

    public MutableLiveData<DiscountDataSource> getDiscountDataSourceMutableLiveData() {
        return discountDataSourceMutableLiveData;
    }
}
