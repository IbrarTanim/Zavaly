package com.zavaly.ui.ordersui.ordersdetails;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.cache.ZavalyRoomDatabase;
import com.zavaly.cache.entities.LoginCache;
import com.zavaly.constants.NetworkConstants;
import com.zavaly.models.ordersdetails.OrderDetailsResponse;
import com.zavaly.utils.Helper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersDetailsRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private MutableLiveData<Integer> errorLiveData;
    private MutableLiveData<OrderDetailsResponse> responseLiveData;

    public OrdersDetailsRepository(Context context, String orderCode) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
        errorLiveData = new MutableLiveData<>();
        responseLiveData = new MutableLiveData<>();
        getDetailsResponse(orderCode);
    }

    private void getDetailsResponse(String orderCode) {

        List<LoginCache> loginCaches = ZavalyRoomDatabase.getINSTANCE(context).loginCacheDao().getLoggedInfo();

        if (loginCaches.isEmpty()) {

            errorLiveData.postValue(NetworkConstants.notAuthorized);

        } else {

            String userId = String.valueOf(loginCaches.get(0).getUserId());

            if (userId.isEmpty()) {

                errorLiveData.postValue(NetworkConstants.notAuthorized);

            } else {

                if (Helper.isOnline(context)) {

                    Call<OrderDetailsResponse> call = apiInterface.getOrderDetails(userId, orderCode);

                    call.enqueue(new Callback<OrderDetailsResponse>() {
                        @Override
                        public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {

                            if (response.code() == 200) {


                                try {

                                    if (response.body().getSuccess() != null && !response.body().getSuccess().equals(true)) {

                                        errorLiveData.postValue(NetworkConstants.successRequest);
                                        responseLiveData.postValue(response.body());

                                    } else {

                                        errorLiveData.postValue(NetworkConstants.noData);

                                    }


                                } catch (Exception e) {

                                    errorLiveData.postValue(NetworkConstants.noData);
                                }


                            } else {

                                errorLiveData.postValue(NetworkConstants.noData);

                            }


                        }

                        @Override
                        public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {

                            errorLiveData.postValue(NetworkConstants.serverConnectionFailed);

                        }
                    });

                } else {

                    errorLiveData.postValue(NetworkConstants.noInternet);

                }


            }


        }

    }

    public MutableLiveData<Integer> getErrorLiveData() {
        return errorLiveData;
    }

    public MutableLiveData<OrderDetailsResponse> getResponseLiveData() {
        return responseLiveData;
    }
}
