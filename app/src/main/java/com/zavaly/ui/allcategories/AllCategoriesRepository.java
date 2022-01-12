package com.zavaly.ui.allcategories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.Cat;
import com.zavaly.models.CatBannerResponsePojo;
import com.zavaly.utils.Helper;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoriesRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public AllCategoriesRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<List<Cat>> getCategories() {

        MutableLiveData<List<Cat>> liveData = new MutableLiveData<>();

        Call<CatBannerResponsePojo> pojoCall = apiInterface.getAllCatBanner();

        if (Helper.isOnline(context)) {


            pojoCall.enqueue(new Callback<CatBannerResponsePojo>() {
                @Override
                public void onResponse(Call<CatBannerResponsePojo> call, Response<CatBannerResponsePojo> response) {

                    if (response.code() == 200) {

                        liveData.setValue(response.body().getCats());

                    }

                }

                @Override
                public void onFailure(Call<CatBannerResponsePojo> call, Throwable t) {

                }
            });

        } else {

            Toasty.warning(context, "No internet connection.").show();

        }

        return liveData;

    }
}
