package com.zavaly.ui.mainui.shops;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.zavaly.models.shopsresponse.Datum;

public class ShopDataSourceFactory extends DataSource.Factory<Integer, Datum> {

    private Context context;
    private MutableLiveData<ShopsDataSource> liveData;

    public ShopDataSourceFactory(Context context) {
        this.context = context;
        liveData = new MutableLiveData<>();
    }


    @NonNull
    @Override
    public DataSource<Integer, Datum> create() {

        ShopsDataSource shopsDataSource = new ShopsDataSource(context);
        liveData.postValue(shopsDataSource);

        return shopsDataSource;
    }


    public MutableLiveData<ShopsDataSource> getLiveData() {
        return liveData;
    }
}
