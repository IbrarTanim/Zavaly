package com.zavaly.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.searchresponse.Product;

public class SearchResultDataSource extends PageKeyedDataSource<Integer, Product> {

    private static final int FIRST_PAGE = 1;
    private Context context;
    private String searchText;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public SearchResultDataSource(Context context, String searchText) {
        this.context = context;
        this.searchText = searchText;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {

    }
}
