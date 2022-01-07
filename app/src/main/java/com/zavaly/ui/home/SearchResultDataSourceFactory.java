package com.zavaly.ui.home;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class SearchResultDataSourceFactory extends DataSource.Factory {

    private Context context;
    private String searchText;
    private MutableLiveData<SearchResultDataSource> liveData;


    public SearchResultDataSourceFactory(Context context, String searchText) {
        this.context = context;
        this.searchText = searchText;
    }

    @NonNull
    @Override
    public DataSource create() {

        SearchResultDataSource searchResultDataSource = new SearchResultDataSource(context, searchText);

        liveData.postValue(searchResultDataSource);

        return searchResultDataSource;

    }

    public MutableLiveData<SearchResultDataSource> getLiveData() {
        return liveData;
    }
}
