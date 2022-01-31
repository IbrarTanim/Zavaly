package com.zavaly.ui.productui.productdetails;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.addtocartresponse.AddToCartResponse;
import com.zavaly.models.productdetails.ProductDetailsResponse;
import com.zavaly.utils.Helper;

import java.io.IOException;
import java.util.HashMap;

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

    /**
     * Get
     * Product Details
     */

    public MutableLiveData<ProductDetailsResponse> getDetails(int productId) {

        MutableLiveData<ProductDetailsResponse> liveData = new MutableLiveData<>();

        if (Helper.isOnline(context)) {

            Call<ProductDetailsResponse> responseCall = apiInterface.getProductDetails(productId);

            responseCall.enqueue(new Callback<ProductDetailsResponse>() {
                @Override
                public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {

                    if (response.code() == 200 && response.body().getSuccess()) {

                        if (response.body().getProduct() != null) {

                            liveData.setValue(response.body());

                        }


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


    /**
     * Add to cart
     * <p>
     * Calculations
     */

    public MutableLiveData<Integer> addToCart(HashMap<String, String> params) {

        MutableLiveData<Integer> liveData = new MutableLiveData<>();


        Call<AddToCartResponse> responseCall = apiInterface.addToCart(params);

        if (Helper.isOnline(context)) {

            //Helper.showLoader(context, "");

            responseCall.enqueue(new Callback<AddToCartResponse>() {
                @Override
                public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {

                    if (response.code() == 200) {

                        if (response.body() != null && response.body().getSuccess()) {

                            if (response.body().getCarts() != null) {

                                Toasty.success(context, response.body().getAlert()).show();
                                liveData.postValue(200);

                            } else {

                                Toasty.success(context, response.body().getAlert()).show();

                            }

                        } else {

                            Toasty.warning(context, "Sorry, try again later.").show();

                        }
                        //Helper.cancelLoader();

                    } else {

                        //Helper.cancelLoader();
                        Log.e("Error Code", String.valueOf(response.code()));
                        try {
                            Log.e("Error Message", response.errorBody().string());
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Toasty.warning(context, "Add to cart failed!").show();

                    }

                }

                @Override
                public void onFailure(Call<AddToCartResponse> call, Throwable t) {

                    //Helper.cancelLoader();
                    Log.e("Error---", t.getMessage());
                    Toasty.warning(context, "Server connection failed.").show();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection").show();

        }

        return liveData;

    }
}
