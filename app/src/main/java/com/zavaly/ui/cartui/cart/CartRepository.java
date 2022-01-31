package com.zavaly.ui.cartui.cart;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.models.Cart;
import com.zavaly.models.cartdelete.CartDeleteResponse;
import com.zavaly.models.cartview.CartViewResponse;
import com.zavaly.models.checkoutresponse.CheckoutResponse;
import com.zavaly.models.productupdate.ProductUpdateResponse;
import com.zavaly.utils.Helper;
import com.zavaly.utils.SharedPreferencesUtils;

import java.util.HashMap;
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


    public MutableLiveData<Integer> deleteCartItem(String productId) {

        MutableLiveData<Integer> responseLiveData = new MutableLiveData<>();

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
                                responseLiveData.postValue(response.code());
                                //RESPONSE_CODE = response.code();

                            } else {

                                Toasty.warning(context, "Sorry, failed to remove.").show();

                            }

                        } else {

                            Toasty.warning(context, "Sorry, failed to remove.").show();

                        }

                    }

                    @Override
                    public void onFailure(Call<CartDeleteResponse> call, Throwable t) {

                        Toasty.warning(context, "Server connection failed").show();

                    }
                });

            } else {

                Toasty.warning(context, "No internet connection").show();

            }

        }

        return responseLiveData;

    }


    public int getRESPONSE_CODE() {
        return RESPONSE_CODE;
    }


    public MutableLiveData<Integer> updateProduct(HashMap<String, String> params) {

        MutableLiveData<Integer> responseLiveData = new MutableLiveData<>();

        String GUEST_ID = preferencesUtils.getString(String.valueOf(ZavalyEnums.KEY_GUEST));

        if (GUEST_ID.equals(String.valueOf(ZavalyEnums.NOT_FOUND))) {

            Toasty.warning(context, "Sorry, failed to update.").show();

        } else {

            if (Helper.isOnline(context)) {

                Call<ProductUpdateResponse> pojoCall = apiInterface.updateCart(params);

                pojoCall.enqueue(new Callback<ProductUpdateResponse>() {
                    @Override
                    public void onResponse(Call<ProductUpdateResponse> call, Response<ProductUpdateResponse> response) {

                        if (response.code() == 200) {

                            if (response.body().getSuccess()) {

                                responseLiveData.postValue(response.code());
                                Toasty.success(context, "Updated successfully").show();

                            } else {

                                Toasty.info(context, "Sorry, failed to update.").show();

                            }

                        } else {

                            Toasty.info(context, "Sorry, failed to update.").show();

                        }

                    }

                    @Override
                    public void onFailure(Call<ProductUpdateResponse> call, Throwable t) {

                        Toasty.info(context, "Server connection failed").show();

                    }
                });

            } else {

                Toasty.info(context, "No internet connection").show();

            }

        }

        return responseLiveData;

    }

    public MutableLiveData<Integer> checkoutFromCart() {

        MutableLiveData<Integer> responseCode = new MutableLiveData<>();

        if (Helper.isOnline(context)) {

            SharedPreferencesUtils utils = new SharedPreferencesUtils(context);

            String guestId = utils.getString(String.valueOf(ZavalyEnums.KEY_GUEST));

            if (guestId.equals(String.valueOf(ZavalyEnums.NOT_FOUND))) {

                Toasty.warning(context, "Please login first.").show();

            } else {


                Call<CheckoutResponse> call = apiInterface.checkoutFromCart(guestId);

                call.enqueue(new Callback<CheckoutResponse>() {
                    @Override
                    public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {

                        if (response.code() == 200) {

                            try {

                                if (response.body().getSuccess()) {

                                    //Toasty.success(context, "Checkout success").show();
                                    responseCode.postValue(response.code());

                                } else {

                                    Toasty.warning(context, "Checkout failed").show();

                                }

                            } catch (NullPointerException ne) {

                                Toasty.warning(context, "Checkout failed").show();

                            }


                        } else {

                            Toasty.warning(context, "Checkout failed").show();

                        }


                    }

                    @Override
                    public void onFailure(Call<CheckoutResponse> call, Throwable t) {

                        Toasty.warning(context, "Server connection failed").show();

                    }
                });

            }

        } else {

            Toasty.warning(context, "No internet connection").show();

        }

        return responseCode;

    }
}