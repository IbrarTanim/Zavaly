package com.zavaly.ui.mainui.home;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.allcategorydetails.AllCategoryDetailsResponse;
import com.zavaly.models.allcategorydetails.Datum;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPageDataSource extends PageKeyedDataSource<Integer, Datum> {

    private static final int FIRST_PAGE = 1;
    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public CategoryPageDataSource(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Datum> callback) {

        Call<AllCategoryDetailsResponse> pojoCall = apiInterface.getAllCategoryProducts(FIRST_PAGE);

        pojoCall.enqueue(new Callback<AllCategoryDetailsResponse>() {
            @Override
            public void onResponse(Call<AllCategoryDetailsResponse> call, Response<AllCategoryDetailsResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getSuccess()) {

                        if (response.body().getCats().getData() != null) {
                            callback.onResult(response.body().getCats().getData(), null, FIRST_PAGE + 1);
                        } else {
                            Toasty.error(context, "No products found!").show();
                        }

                    }

                } else {

                }

            }

            @Override
            public void onFailure(Call<AllCategoryDetailsResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {

        Call<AllCategoryDetailsResponse> pojoCall = apiInterface.getAllCategoryProducts(params.key);

        pojoCall.enqueue(new Callback<AllCategoryDetailsResponse>() {
            @Override
            public void onResponse(Call<AllCategoryDetailsResponse> call, Response<AllCategoryDetailsResponse> response) {

                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.isSuccessful()) {

                    if (response.body().getSuccess()) {

                        if (response.body().getCats().getData() != null) {
                            callback.onResult(response.body().getCats().getData(), adjacentKey);
                        } else {
                            Toasty.error(context, "No products found!").show();
                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<AllCategoryDetailsResponse> call, Throwable t) {
            }
        });

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {

        Call<AllCategoryDetailsResponse> pojoCall = apiInterface.getAllCategoryProducts(params.key);

        pojoCall.enqueue(new Callback<AllCategoryDetailsResponse>() {
            @Override
            public void onResponse(Call<AllCategoryDetailsResponse> call, Response<AllCategoryDetailsResponse> response) {

                //Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.isSuccessful()) {

                    if (response.body().getSuccess()) {

                        if (response.body().getCats().getData() != null) {
                            Integer key = params.key < response.body().getCats().getLastPage() ? params.key + 1 : null;
                            callback.onResult(response.body().getCats().getData(), key);
                        } else {
                            Toasty.error(context, "No products found!").show();
                        }

                    }

                }

            }

            @Override
            public void onFailure(Call<AllCategoryDetailsResponse> call, Throwable t) {
            }
        });

    }
}
