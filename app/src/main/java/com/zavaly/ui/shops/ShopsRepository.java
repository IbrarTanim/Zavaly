package com.zavaly.ui.shops;

import android.content.Context;
import android.os.Handler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.adapter.ShopsRecyclerAdapter;
import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.models.shopsresponse.ShopsResponse;
import com.zavaly.utils.Helper;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public ShopsRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    public void getAllShops(RecyclerView recyclerView) {

        Call<ShopsResponse> call = apiInterface.getAllShops();

        if (Helper.isOnline(context)) {

            Helper.showLoader(context, "");

            call.enqueue(new Callback<ShopsResponse>() {
                @Override
                public void onResponse(Call<ShopsResponse> call, Response<ShopsResponse> response) {

                    if (response.body() != null) {

                        if (response.body().getData() != null) {

                            ShopsRecyclerAdapter adapter = new ShopsRecyclerAdapter(context, response.body().getData());
                            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Helper.cancelLoader();
                                }
                            }, 1000);

                        } else {

                            Toasty.warning(context, "No data found").show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Helper.cancelLoader();
                                }
                            }, 1000);

                        }

                    } else {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Helper.cancelLoader();
                            }
                        }, 1000);

                    }

                }

                @Override
                public void onFailure(Call<ShopsResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed").show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Helper.cancelLoader();
                        }
                    }, 1000);

                }
            });

        } else {

            Toasty.warning(context, "No internet connection").show();

        }

    }
}
