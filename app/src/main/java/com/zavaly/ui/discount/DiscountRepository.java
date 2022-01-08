package com.zavaly.ui.discount;

import android.content.Context;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;

public class DiscountRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public DiscountRepository(Context context) {

        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);

    }
}
