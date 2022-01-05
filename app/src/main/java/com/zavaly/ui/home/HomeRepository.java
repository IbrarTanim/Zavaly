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

    /*public void getAllProducts(RecyclerView recyclerView) {

        Call<AllCategoryDetailsResponse> pojoCall = apiInterface.getAllCategoryProducts(PAGE_NUMBER);

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

                            *//*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                @Override
                                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                    super.onScrollStateChanged(recyclerView, newState);

                                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){

                                        if (!isLoaded){

                                            isLoaded = true;
                                            //end of the bottom
                                            if (PAGE_NUMBER < response.body().getCats().getLastPage()){

                                                PAGE_NUMBER = PAGE_NUMBER + 1;
                                                adapter.updateList(loadMore());
                                                isLoaded = false;
                                                Toasty.success(context, "Page : " + PAGE_NUMBER).show();

                                            }

                                        }



                                    }*//**//*else if (!recyclerView.canScrollVertically(-1) && recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE){

                                        //To the Top
                                        if (response.body().getCats().getPrevPageUrl() != null){

                                            PAGE_NUMBER = PAGE_NUMBER - 1;
                                            getAllProducts(recyclerView);
                                            Toasty.success(context, "Page : " + PAGE_NUMBER).show();

                                        }

                                    }*//**//*

                                }
                            });*//*


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


    public List<Datum> loadMore(){

        final List<Datum>[] datumList = new List[]{new ArrayList<>()};

        Call<AllCategoryDetailsResponse> pojoCall = apiInterface.getAllCategoryProducts(PAGE_NUMBER);

        Helper.showLoader(context, "");

        pojoCall.enqueue(new Callback<AllCategoryDetailsResponse>() {
            @Override
            public void onResponse(Call<AllCategoryDetailsResponse> call, Response<AllCategoryDetailsResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getSuccess()) {
                        datumList[0] = response.body().getCats().getData();
                        for (Datum datum : datumList[0]){

                            Log.e("Array", datum.toString());

                        }

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

        return datumList[0];
    }*/
}
