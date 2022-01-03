package com.zavaly.ui.home;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.adapter.HomeAllCategoriesRecyclerAdapter;
import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.CatBannerResponsePojo;
import com.zavaly.models.allcategorydetails.AllCategoryDetailsResponse;
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

    public void getAllProducts(RecyclerView recyclerView) {

        Call<AllCategoryDetailsResponse> pojoCall = apiInterface.getAllCategoryProducts();

        if (Helper.isOnline(context)) {

            Helper.showLoader(context, "");

            pojoCall.enqueue(new Callback<AllCategoryDetailsResponse>() {
                @Override
                public void onResponse(Call<AllCategoryDetailsResponse> call, Response<AllCategoryDetailsResponse> response) {

                    if (response.isSuccessful()) {

                        if (response.body().getSuccess()) {

                            HomeAllCategoriesRecyclerAdapter adapter = new HomeAllCategoriesRecyclerAdapter(context, response.body().getCats().getData());
                            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);


                        }

                        Helper.cancelLoader();

                    }

                    Helper.cancelLoader();

                }

                @Override
                public void onFailure(Call<AllCategoryDetailsResponse> call, Throwable t) {
                    Helper.cancelLoader();
                }
            });

        }

    }
}
