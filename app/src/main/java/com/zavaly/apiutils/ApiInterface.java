package com.zavaly.apiutils;


import com.zavaly.models.CatBannerResponsePojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("fastcom")
    Call<CatBannerResponsePojo> getAllCatBanner();

}
