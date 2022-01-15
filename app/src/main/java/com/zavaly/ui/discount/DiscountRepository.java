package com.zavaly.ui.discount;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.adapter.ProductsRecyclerAdapter;
import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.models.discountproducts.DiscountProductsResponse;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public DiscountRepository(Context context) {

        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);

    }

    public void getList(RecyclerView recyclerView) {

        Call<DiscountProductsResponse> pojoCall = apiInterface.getDiscountedProducts(1);

        pojoCall.enqueue(new Callback<DiscountProductsResponse>() {
            @Override
            public void onResponse(Call<DiscountProductsResponse> call, Response<DiscountProductsResponse> response) {

                Log.e("*********", String.valueOf(response.code()));
                if (response.body() != null) {

                    Log.e("*********", "1");
                    if (response.body().getData() != null) {
                        Log.e("*********", "2");
                        ProductsRecyclerAdapter adapter = new ProductsRecyclerAdapter(context, null, null, null, response.body().getData(), String.valueOf(ZavalyEnums.List_Discounted));
                        GridLayoutManager manager = new GridLayoutManager(context, 2);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);

                    } else {
                        Log.e("*********", "3");
                        Toasty.warning(context, "No products found").show();

                    }

                } else {

                    try {
                        Log.e("********", response.errorBody().string());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                    Log.e("*********", "4");
                }

            }

            @Override
            public void onFailure(Call<DiscountProductsResponse> call, Throwable t) {

                Toasty.error(context, t.getMessage());

            }
        });

    }
}
