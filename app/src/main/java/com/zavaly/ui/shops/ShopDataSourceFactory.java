package com.zavaly.ui.shops;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class ShopDataSourceFactory extends DataSource.Factory {

    private Context context;
    private MutableLiveData<ShopsDataSource> liveData;

    public ShopDataSourceFactory(Context context) {
        this.context = context;
        liveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {

        ShopsDataSource shopsDataSource = new ShopsDataSource(context);
        liveData.postValue(shopsDataSource);

        return shopsDataSource;
    }

    public MutableLiveData<ShopsDataSource> getLiveData() {
        return liveData;
    }
}
