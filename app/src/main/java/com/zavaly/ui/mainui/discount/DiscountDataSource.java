package com.zavaly.ui.mainui.discount;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.discountproducts.Datum;
import com.zavaly.models.discountproducts.DiscountProductsResponse;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountDataSource extends PageKeyedDataSource<Integer, Datum> {

    private Context context;
    private static final int FIRST_PAGE = 1;
    private static int LAST_PAGE = 1;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public DiscountDataSource(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Datum> callback) {

        Call<DiscountProductsResponse> pojoCall = apiInterface.getDiscountedProducts(FIRST_PAGE);

        pojoCall.enqueue(new Callback<DiscountProductsResponse>() {
            @Override
            public void onResponse(Call<DiscountProductsResponse> call, Response<DiscountProductsResponse> response) {

                if (response.body() != null) {

                    if (response.body().getData() != null) {

                        LAST_PAGE = response.body().getLastPage();
                        callback.onResult(response.body().getData(), null, FIRST_PAGE + 1);

                    } else {

                        Toasty.warning(context, "No products found").show();

                    }

                }

            }

            @Override
            public void onFailure(Call<DiscountProductsResponse> call, Throwable t) {

                //Toasty.error(context, t.getMessage());

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {

        Call<DiscountProductsResponse> pojoCall = apiInterface.getDiscountedProducts(params.key);

        pojoCall.enqueue(new Callback<DiscountProductsResponse>() {
            @Override
            public void onResponse(Call<DiscountProductsResponse> call, Response<DiscountProductsResponse> response) {

                if (response.body() != null) {

                    if (response.body().getData() != null) {

                        callback.onResult(response.body().getData(), (params.key > 1) ? params.key - 1 : null);

                    } else {

                        Toasty.warning(context, "No products found").show();

                    }

                }

            }

            @Override
            public void onFailure(Call<DiscountProductsResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {

        Call<DiscountProductsResponse> pojoCall = apiInterface.getDiscountedProducts(params.key);

        pojoCall.enqueue(new Callback<DiscountProductsResponse>() {
            @Override
            public void onResponse(Call<DiscountProductsResponse> call, Response<DiscountProductsResponse> response) {

                if (response.body() != null) {

                    if (response.body().getData() != null) {

                        callback.onResult(response.body().getData(), (params.key < LAST_PAGE) ? params.key + 1 : null);

                    } else {

                        Toasty.warning(context, "No products found").show();

                    }

                }

            }

            @Override
            public void onFailure(Call<DiscountProductsResponse> call, Throwable t) {

            }
        });

    }
}
