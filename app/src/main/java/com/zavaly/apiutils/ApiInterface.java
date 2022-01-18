package com.zavaly.apiutils;


import com.zavaly.models.CatBannerResponsePojo;
import com.zavaly.models.addtocartresponse.AddToCartResponse;
import com.zavaly.models.allcategorydetails.AllCategoryDetailsResponse;
import com.zavaly.models.cartdelete.CartDeleteResponse;
import com.zavaly.models.cartview.CartViewResponse;
import com.zavaly.models.discountproducts.DiscountProductsResponse;
import com.zavaly.models.login.LoginResponse;
import com.zavaly.models.productdetails.ProductDetailsResponse;
import com.zavaly.models.productupdate.ProductUpdateResponse;
import com.zavaly.models.recoverpass.RecoverResponse;
import com.zavaly.models.register.RegisterResponse;
import com.zavaly.models.registerverify.RegisterVerifyResponse;
import com.zavaly.models.searchresponse.SearchResponse;
import com.zavaly.models.shopsresponse.ShopsResponse;
import com.zavaly.models.specificcategory.SpecificCategoryResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("fastcom")
    Call<CatBannerResponsePojo> getAllCatBanner();

    @POST("login")
    Call<LoginResponse> userLogin(@QueryMap HashMap<String, String> params);

    @POST("register")
    Call<RegisterResponse> userRegister(@QueryMap HashMap<String, String> params);

    @POST("verify")
    Call<RegisterVerifyResponse> userRegisterVerify(@QueryMap HashMap<String, String> params);

    @POST("recoverRequest")
    Call<RecoverResponse> forgetPasswordRequest(@Query("phone") String phone);

    @POST("recover")
    Call<RecoverResponse> forgetPasswordConfirm(@QueryMap HashMap<String, String> params);

    @GET("category")
    Call<AllCategoryDetailsResponse> getAllCategoryProducts();

    @GET("category")
    Call<AllCategoryDetailsResponse> getAllCategoryProducts(@Query("page") int pageNumber);

    @GET("category")
    Call<SpecificCategoryResponse> getSpecificCategory(@Query("id") int categoryId);

    @GET("product/details/{id}")
    Call<ProductDetailsResponse> getProductDetails(@Path("id") int productId);

    @GET("product/search")
    Call<SearchResponse> getSearchProduct(@Query("text") String text, @Query("page") int pageNumber);

    @POST("allshop")
    Call<ShopsResponse> getAllShops(@Query("page") int pageNumber);

    @GET("discount/product")
    Call<DiscountProductsResponse> getDiscountedProducts(@Query("page") int pageNumber);

    /**
     * Add To Cart Api
     */
    @POST("add-to-cart")
    Call<AddToCartResponse> addToCart(@QueryMap HashMap<String, String> params);

    /**
     * Cart
     * View
     */
    @POST("cart-view")
    Call<CartViewResponse> getCartView(@Query("guest_id") String guestId);

    /**
     * Cart
     * Delete
     * Product
     */
    @POST("cart-delete")
    Call<CartDeleteResponse> deleteCartItem(@Query("guest_id") String guestId, @Query("product_id") String productId);

    /**
     * Cart
     * Product
     * Update
     */
    @POST("cart-update")
    Call<ProductUpdateResponse> updateCart(@QueryMap HashMap<String, String> params);


}
