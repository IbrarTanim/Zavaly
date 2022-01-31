package com.zavaly.ui.mainui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class CategoryPageDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<CategoryPageDataSource> liveDataSource;
    private Context context;

    public CategoryPageDataSourceFactory(Context context) {
        liveDataSource = new MutableLiveData<>();
        this.context = context;
    }

    @NonNull
    @Override
    public DataSource create() {
        CategoryPageDataSource categoryPageDataSource = new CategoryPageDataSource(context);

        liveDataSource.postValue(categoryPageDataSource);

        return categoryPageDataSource;
    }

    public MutableLiveData<CategoryPageDataSource> getLiveDataSource() {
        return liveDataSource;
    }
}
