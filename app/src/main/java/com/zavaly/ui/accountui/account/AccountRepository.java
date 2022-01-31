package com.zavaly.ui.accountui.account;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.login.LoginResponse;
import com.zavaly.utils.Helper;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public AccountRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }


    public MutableLiveData<LoginResponse> userLogin(HashMap<String, String> loginMap) {

        MutableLiveData<LoginResponse> mutableLiveData = new MutableLiveData<>();

        Call<LoginResponse> pojoCall = apiInterface.userLogin(loginMap);

        if (Helper.isOnline(context)) {

            pojoCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.code() == 200 && response.body().getSuccess()) {

                        Toasty.success(context, "Login successful.").show();
                        mutableLiveData.setValue(response.body());

                    } else {

                        Toasty.error(context, "Login failed.").show();

                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed.").show();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection.").show();

        }

        return mutableLiveData;
    }
}
