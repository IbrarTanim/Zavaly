package com.zavaly.ui.viewcategoryproduct;

import android.content.Context;
import android.view.View;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zavaly.adapter.ProductsRecyclerAdapter;
import com.zavaly.adapter.SubCategoryListAdapter;
import com.zavaly.apiutils.ApiClient;
import com.zavaly.apiutils.ApiInterface;
import com.zavaly.enums.ZavalyEnums;
import com.zavaly.models.specificcategory.SpecificCategoryResponse;
import com.zavaly.utils.Helper;
import com.zavaly.utils.RecyclerTouchListener;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCategoryProductRepository {

    private Context context;
    private ApiClient apiClient;
    private ApiInterface apiInterface;

    public ViewCategoryProductRepository(Context context) {
        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    public void getCategoryProducts(int categoryId, RecyclerView categoryListRV, RecyclerView categoryProductRV) {

        if (Helper.isOnline(context)) {

            Call<SpecificCategoryResponse> call = apiInterface.getSpecificCategory(categoryId);

            Helper.showLoader(context, "");

            call.enqueue(new Callback<SpecificCategoryResponse>() {
                @Override
                public void onResponse(Call<SpecificCategoryResponse> call, Response<SpecificCategoryResponse> response) {

                    if (response.code() == 200) {

                        if (response.body().getSuccess()) {

                            if (response.body().getCats().getCategory() != null) {

                                SubCategoryListAdapter categoryListAdapter = new SubCategoryListAdapter(context, response.body().getCats().getCategory());
                                GridLayoutManager manager = new GridLayoutManager(context, 3);
                                categoryListRV.setLayoutManager(manager);
                                categoryListRV.setAdapter(categoryListAdapter);

                                categoryListRV.addOnItemTouchListener(new RecyclerTouchListener(context, categoryListRV, new RecyclerTouchListener.ClickListener() {
                                    @Override
                                    public void onClick(View view, int position) {

                                        int catId = response.body().getCats().getCategory().get(position).getId();
                                        Navigation.findNavController(view).navigate(ViewCategoryProductFragmentDirections.actionNavigationViewCategoryProductsSelf(catId));

                                    }

                                    @Override
                                    public void onLongClick(View view, int position) {

                                    }
                                }));

                            }

                            if (response.body().getCats().getProducts() != null) {

                                ProductsRecyclerAdapter adapter = new ProductsRecyclerAdapter(context, null, response.body().getCats().getProducts(), null, String.valueOf(ZavalyEnums.List_Solo_Cat));
                                GridLayoutManager manager = new GridLayoutManager(context, 2);
                                categoryProductRV.setLayoutManager(manager);
                                categoryProductRV.setAdapter(adapter);

                                categoryProductRV.addOnItemTouchListener(new RecyclerTouchListener(context, categoryProductRV, new RecyclerTouchListener.ClickListener() {
                                    @Override
                                    public void onClick(View view, int position) {

                                        int productId = response.body().getCats().getProducts().get(position).getId();
                                        Navigation.findNavController(view).navigate(ViewCategoryProductFragmentDirections.actionNavigationViewCategoryProductsToNavigationProductDetails(productId));

                                    }

                                    @Override
                                    public void onLongClick(View view, int position) {

                                    }
                                }));
                            }

                        }

                    } else {

                        Toasty.error(context, "No products found").show();
                        Helper.cancelLoader();

                    }

                }

                @Override
                public void onFailure(Call<SpecificCategoryResponse> call, Throwable t) {

                    Toasty.error(context, "Server connection failed").show();
                    Helper.cancelLoader();

                }
            });

        } else {

            Toasty.warning(context, "No Internet Connection").show();

        }

    }
}
