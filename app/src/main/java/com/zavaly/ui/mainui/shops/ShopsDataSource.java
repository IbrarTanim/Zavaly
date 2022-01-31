package com.zavaly.ui.mainui.shops;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.shopsresponse.Datum;
import com.zavaly.models.shopsresponse.ShopsResponse;
import com.zavaly.utils.Helper;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsDataSource extends PageKeyedDataSource<Integer, Datum> {

    private static final int FIRST_PAGE = 1;
    private Context context;
    private int LAST_PAGE;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public ShopsDataSource(Context context) {

        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Datum> callback) {

        Call<ShopsResponse> call = apiInterface.getAllShops(FIRST_PAGE);

        if (Helper.isOnline(context)) {

            call.enqueue(new Callback<ShopsResponse>() {
                @Override
                public void onResponse(Call<ShopsResponse> call, Response<ShopsResponse> response) {

                    if (response.body() != null) {

                        if (response.body().getData() != null) {

                            LAST_PAGE = response.body().getLastPage();
                            callback.onResult(response.body().getData(), null, FIRST_PAGE + 1);

                        } else {

                            Toasty.warning(context, "No data found").show();

                        }

                    } else {


                    }

                }

                @Override
                public void onFailure(Call<ShopsResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed").show();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection").show();

        }

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {

        Call<ShopsResponse> call = apiInterface.getAllShops(params.key);

        if (Helper.isOnline(context)) {

            call.enqueue(new Callback<ShopsResponse>() {
                @Override
                public void onResponse(Call<ShopsResponse> call, Response<ShopsResponse> response) {

                    if (response.body() != null) {

                        if (response.body().getData() != null) {

                            int adjacentKey = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getData(), adjacentKey);

                        } else {

                            Toasty.warning(context, "No data found").show();

                        }

                    } else {


                    }

                }

                @Override
                public void onFailure(Call<ShopsResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed").show();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection").show();

        }

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {

        Call<ShopsResponse> call = apiInterface.getAllShops(params.key);

        if (Helper.isOnline(context)) {

            call.enqueue(new Callback<ShopsResponse>() {
                @Override
                public void onResponse(Call<ShopsResponse> call, Response<ShopsResponse> response) {

                    if (response.body() != null) {

                        if (response.body().getData() != null) {

                            callback.onResult(response.body().getData(), (params.key < LAST_PAGE) ? params.key + 1 : null);

                        } else {

                            Toasty.warning(context, "No data found").show();

                        }

                    } else {


                    }

                }

                @Override
                public void onFailure(Call<ShopsResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed").show();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection").show();

        }

    }
}
