package com.zavaly.ui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.searchresponse.Product;
import com.zavaly.models.searchresponse.SearchResponse;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Call<SearchResponse> searchPojo = apiInterface.getSearchProduct(searchText, FIRST_PAGE);

        searchPojo.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                if (response.body() != null) {

                    if (response.body().getSuccess()) {

                        if (response.body().getProducts() != null) {

                            callback.onResult(response.body().getProducts(), null, FIRST_PAGE + 1);

                        } else {

                            Toasty.warning(context, "No products found").show();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {

        Call<SearchResponse> searchPojo = apiInterface.getSearchProduct(searchText, params.key);

        searchPojo.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                int adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.body() != null) {

                    if (response.body().getSuccess()) {

                        if (response.body().getProducts() != null) {

                            callback.onResult(response.body().getProducts(), adjacentKey);

                        } else {

                            Toasty.warning(context, "No products found").show();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {


        Call<SearchResponse> searchPojo = apiInterface.getSearchProduct(searchText, params.key);

        searchPojo.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                if (response.body() != null) {

                    if (response.body().getSuccess()) {

                        if (response.body().getProducts() != null) {

                            callback.onResult(response.body().getProducts(), params.key + 1);

                        } else {
                            Toasty.warning(context, "No more products found").show();

                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });

    }
}
