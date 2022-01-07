package com.zavaly.ui.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.CatBannerResponsePojo;
import com.zavaly.utils.Helper;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private int PAGE_NUMBER = 1;
    private boolean isLoaded = false;

    public HomeRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<List<String>> getSliders() {

        MutableLiveData<List<String>> liveData = new MutableLiveData<>();

        Call<CatBannerResponsePojo> pojoCall = apiInterface.getAllCatBanner();

        if (Helper.isOnline(context)) {

            //Helper.showLoader(context, "");

            pojoCall.enqueue(new Callback<CatBannerResponsePojo>() {
                @Override
                public void onResponse(Call<CatBannerResponsePojo> call, Response<CatBannerResponsePojo> response) {

                    if (response.code() == 200) {

                        liveData.setValue(response.body().getBanners());
                        //Helper.cancelLoader();

                    }

                    //Helper.cancelLoader();

                }

                @Override
                public void onFailure(Call<CatBannerResponsePojo> call, Throwable t) {

                    //Helper.cancelLoader();

                }
            });

        } else {

            Toasty.warning(context, "No internet connection.").show();

        }


        return liveData;

    }
}
