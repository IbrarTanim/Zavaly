package com.zavaly.ui.accountui.forgetpassword;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.recoverpass.RecoverResponse;
import com.zavaly.utils.Helper;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public ForgetPasswordRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<RecoverResponse> recoverRequest(String phoneNumber) {

        MutableLiveData<RecoverResponse> mutableLiveData = new MutableLiveData<>();

        Call<RecoverResponse> pojoCall = apiInterface.forgetPasswordRequest(phoneNumber);

        if (Helper.isOnline(context)) {

            Helper.showLoader(context, "");

            pojoCall.enqueue(new Callback<RecoverResponse>() {
                @Override
                public void onResponse(Call<RecoverResponse> call, Response<RecoverResponse> response) {

                    if (response.code() == 200) {

                        if (response.body() != null) {

                            mutableLiveData.setValue(response.body());

                        } else {

                            Toasty.error(context, "Phone number not valid!").show();

                        }

                        Helper.cancelLoader();

                    } else {

                        Toasty.error(context, "Try again later.").show();
                        Helper.cancelLoader();

                    }

                }

                @Override
                public void onFailure(Call<RecoverResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed").show();
                    Helper.cancelLoader();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection.").show();

        }

        return mutableLiveData;

    }


    public MutableLiveData<RecoverResponse> recoverPassword(String phoneNumber, String password, String conPassword, String pin) {

        MutableLiveData<RecoverResponse> liveData = new MutableLiveData<>();

        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phoneNumber);
        params.put("password", password);
        params.put("password_confirmation", conPassword);
        params.put("pin", pin);

        Call<RecoverResponse> call = apiInterface.forgetPasswordConfirm(params);

        if (Helper.isOnline(context)) {

            Helper.showLoader(context, "");
            call.enqueue(new Callback<RecoverResponse>() {
                @Override
                public void onResponse(Call<RecoverResponse> call, Response<RecoverResponse> response) {

                    if (response.code() == 200) {

                        if (response.body() != null) {

                            liveData.setValue(response.body());

                        } else {

                            Toasty.error(context, "Phone number not valid!").show();

                        }

                        Helper.cancelLoader();

                    } else {

                        Toasty.error(context, "Try again later.").show();
                        Helper.cancelLoader();

                    }

                }

                @Override
                public void onFailure(Call<RecoverResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed").show();
                    Helper.cancelLoader();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection.").show();

        }

        return liveData;

    }
}
