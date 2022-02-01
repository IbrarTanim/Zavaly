package com.zavaly.ui.ordersui.orders;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.cache.ZavalyRoomDatabase;
import com.zavaly.cache.entities.LoginCache;
import com.zavaly.constants.NetworkConstants;
import com.zavaly.models.orders.OrdersResponse;
import com.zavaly.utils.Helper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private MutableLiveData<Integer> errorLiveData;
    private MutableLiveData<OrdersResponse> ordersLiveData;


    public OrdersRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
        errorLiveData = new MutableLiveData<>();
        ordersLiveData = new MutableLiveData<>();
        getOrders();
    }

    public void getOrders() {

        List<LoginCache> loginCaches = ZavalyRoomDatabase.getINSTANCE(context).loginCacheDao().getLoggedInfo();

        if (loginCaches.isEmpty()) {

            errorLiveData.postValue(NetworkConstants.notAuthorized);

        } else {

            String userId = String.valueOf(loginCaches.get(0).getUserId());

            if (userId.isEmpty()) {

                errorLiveData.postValue(NetworkConstants.notAuthorized);

            } else {

                if (Helper.isOnline(context)) {

                    Call<OrdersResponse> call = apiInterface.getOrders(userId);

                    call.enqueue(new Callback<OrdersResponse>() {
                        @Override
                        public void onResponse(Call<OrdersResponse> call, Response<OrdersResponse> response) {

                            if (response.code() == 200) {


                                try {

                                    if (response.body().getOrders() != null && !response.body().getOrders().isEmpty()) {

                                        errorLiveData.postValue(NetworkConstants.successRequest);
                                        ordersLiveData.postValue(response.body());

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
                        public void onFailure(Call<OrdersResponse> call, Throwable t) {

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

    public MutableLiveData<OrdersResponse> getOrdersLiveData() {
        return ordersLiveData;
    }
}
