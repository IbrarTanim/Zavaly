package com.zavaly.ui.accountui.createaccount;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.models.register.RegisterResponse;
import com.zavaly.models.registerverify.RegisterVerifyResponse;
import com.zavaly.utils.Helper;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public CreateAccountRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<RegisterResponse> registerCustomer(String customerName, String phoneNumber, String password, String confirmPassword) {

        MutableLiveData<RegisterResponse> liveData = new MutableLiveData<>();

        HashMap<String, String> params = new HashMap<>();
        params.put("name", customerName);
        params.put("phone", phoneNumber);
        params.put("password", password);
        params.put("password_confirmation", confirmPassword);
        params.put("role", String.valueOf(ZavalyEnums.customer));

        Call<RegisterResponse> responseCall = apiInterface.userRegister(params);

        if (Helper.isOnline(context)) {

            Helper.showLoader(context, "");

            responseCall.enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                    if (response.code() == 200) {

                        if (response.body().getSuccess()) {

                            Toasty.success(context, "Account created.").show();
                            liveData.setValue(response.body());

                        } else {

                            Toasty.error(context, "Phone number already taken").show();

                        }

                        Helper.cancelLoader();

                    } else if (response.code() == 500) {

                        Toasty.error(context, "Invalid phone number!").show();
                        Helper.cancelLoader();

                    }

                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed.").show();
                    Helper.cancelLoader();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection.").show();

        }

        return liveData;

    }


    public MutableLiveData<RegisterVerifyResponse> verifyRegisterAccount(String pin, String phone) {

        MutableLiveData<RegisterVerifyResponse> liveData = new MutableLiveData<>();

        HashMap<String, String> params = new HashMap<>();
        params.put("pin", pin);
        params.put("phone", phone);

        Call<RegisterVerifyResponse> responseCall = apiInterface.userRegisterVerify(params);

        if (Helper.isOnline(context)) {

            Helper.showLoader(context, "");
            responseCall.enqueue(new Callback<RegisterVerifyResponse>() {
                @Override
                public void onResponse(Call<RegisterVerifyResponse> call, Response<RegisterVerifyResponse> response) {

                    if (response.code() == 200) {

                        if (response.body().getSuccess()) {

                            Toasty.success(context, "Account verified.").show();
                            liveData.setValue(response.body());

                        } else {

                            Toasty.error(context, "CODE invalid.").show();

                        }

                        Helper.cancelLoader();

                    } else {

                        Toasty.error(context, "Verification failed.").show();
                        Helper.cancelLoader();

                    }
                }

                @Override
                public void onFailure(Call<RegisterVerifyResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed.").show();
                    Helper.cancelLoader();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection.").show();

        }

        return liveData;
    }
}
