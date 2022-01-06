package com.zavaly.ui.productdetails;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.productdetails.ProductDetailsResponse;
import com.zavaly.utils.Helper;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public ProductDetailsRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<ProductDetailsResponse> getDetails(int productId) {

        MutableLiveData<ProductDetailsResponse> liveData = new MutableLiveData<>();

        if (Helper.isOnline(context)) {

            Call<ProductDetailsResponse> responseCall = apiInterface.getProductDetails(productId);

            responseCall.enqueue(new Callback<ProductDetailsResponse>() {
                @Override
                public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {

                    if (response.code() == 200 && response.body().getSuccess()) {

                        liveData.setValue(response.body());

                    }

                }

                @Override
                public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed").show();

                }
            });

        } else {

            Toasty.error(context, "No internet connection").show();

        }

        return liveData;

    }
}
