package com.zavaly.apiutils;


import com.zavaly.models.CatBannerResponsePojo;
import com.zavaly.models.login.LoginResponse;
import com.zavaly.models.recoverpass.RecoverResponse;
import com.zavaly.models.register.RegisterResponse;
import com.zavaly.models.registerverify.RegisterVerifyResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("fastcom")
    Call<CatBannerResponsePojo> getAllCatBanner();

    @POST("login")
    Call<LoginResponse> userLogin(@QueryMap HashMap<String, String> params);

    @POST("register")
    Call<RegisterResponse> userRegister(@QueryMap HashMap<String, String> params);

    @POST("verify")
    Call<RegisterVerifyResponse> userRegisterVerify(@QueryMap HashMap<String, String> params);

    @POST("recoverRequest")
    Call<RecoverResponse> forgetPasswordRequest(@Query("phone") String phone);

    @POST("recover")
    Call<RecoverResponse> forgetPasswordConfirm(@QueryMap HashMap<String, String> params);

}
