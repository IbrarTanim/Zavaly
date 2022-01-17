package com.zavaly.ui.cart;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.models.Cart;
import com.zavaly.models.cartdelete.CartDeleteResponse;
import com.zavaly.models.cartview.CartViewResponse;
import com.zavaly.utils.Helper;
import com.zavaly.utils.SharedPreferencesUtils;

import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private SharedPreferencesUtils preferencesUtils;
    private int RESPONSE_CODE = 0;

    public CartRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
        preferencesUtils = new SharedPreferencesUtils(context);
    }

    public MutableLiveData<List<Cart>> getCartDetails() {

        MutableLiveData<List<Cart>> cartList = new MutableLiveData<>();

        String GUEST_ID = preferencesUtils.getString(String.valueOf(ZavalyEnums.KEY_GUEST));

        if (GUEST_ID.equals(String.valueOf(ZavalyEnums.NOT_FOUND))) {

            Toasty.warning(context, "Your cart is empty.").show();

        } else {

            if (Helper.isOnline(context)) {

                Call<CartViewResponse> responseCall = apiInterface.getCartView(GUEST_ID);

                responseCall.enqueue(new Callback<CartViewResponse>() {
                    @Override
                    public void onResponse(Call<CartViewResponse> call, Response<CartViewResponse> response) {

                        if (response.code() == 200) {

                            if (Objects.requireNonNull(response.body()).getSuccess() && response.body().getCarts() != null) {

                                List<Cart> carts = response.body().getCarts();

                                cartList.postValue(carts);

                            } else {

                                Toasty.warning(context, "Your cart is empty.").show();

                            }

                        } else {

                            Toasty.warning(context, "Your cart is empty.").show();

                        }

                    }

                    @Override
                    public void onFailure(Call<CartViewResponse> call, Throwable t) {

                        Toasty.warning(context, "Server connection failed.").show();

                    }
                });

            } else {

                Toasty.warning(context, "No internet connection").show();

            }

        }

        return cartList;

    }


    public void deleteCartItem(String productId) {


        String GUEST_ID = preferencesUtils.getString(String.valueOf(ZavalyEnums.KEY_GUEST));

        if (GUEST_ID.equals(String.valueOf(ZavalyEnums.NOT_FOUND))) {

            Toasty.warning(context, "Sorry, failed to remove.").show();

        } else {

            if (Helper.isOnline(context)) {

                Call<CartDeleteResponse> cartDeleteResponseCall = apiInterface.deleteCartItem(GUEST_ID, productId);

                cartDeleteResponseCall.enqueue(new Callback<CartDeleteResponse>() {
                    @Override
                    public void onResponse(Call<CartDeleteResponse> call, Response<CartDeleteResponse> response) {

                        if (response.code() == 200) {

                            if (response.body() != null && response.body().getSuccess()) {

                                Toasty.success(context, "Removed successfully").show();
                                RESPONSE_CODE = response.code();

                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<CartDeleteResponse> call, Throwable t) {

                    }
                });

            } else {

                Toasty.warning(context, "No internet connection").show();

            }

        }

    }


    public int getRESPONSE_CODE() {
        return RESPONSE_CODE;
    }
}
