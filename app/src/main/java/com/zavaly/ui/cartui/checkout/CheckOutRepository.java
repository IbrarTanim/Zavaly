package com.zavaly.ui.cartui.checkout;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.checkoutfinalstep.CheckoutConfirmResponse;
import com.zavaly.utils.Helper;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public CheckOutRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }


    public MutableLiveData<Integer> checkoutConfirm(HashMap<String, String> params) {

        MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();

        if (Helper.isOnline(context)) {


            Call<CheckoutConfirmResponse> call = apiInterface.confirmCheckout(params);

            call.enqueue(new Callback<CheckoutConfirmResponse>() {
                @Override
                public void onResponse(Call<CheckoutConfirmResponse> call, Response<CheckoutConfirmResponse> response) {

                    if (response.code() == 200) {

                        try {

                            Toasty.success(context, response.body().getAlert()).show();
                            integerMutableLiveData.postValue(200);

                        } catch (NullPointerException ne) {

                            integerMutableLiveData.postValue(401);

                        }

                    } else {

                        Toasty.error(context, "Failed to checkout..").show();

                    }

                }

                @Override
                public void onFailure(Call<CheckoutConfirmResponse> call, Throwable t) {

                    Toasty.warning(context, "Server connection failed").show();

                }
            });


        } else {

            Toasty.warning(context, "No internet connection").show();

        }

        return integerMutableLiveData;

    }

}
