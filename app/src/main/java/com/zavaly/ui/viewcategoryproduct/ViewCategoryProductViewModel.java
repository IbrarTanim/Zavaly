package com.zavaly.ui.viewcategoryproduct;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

public class ViewCategoryProductViewModel extends ViewModel {

    private ViewCategoryProductRepository repository;

    public void viewModelInit(Context context) {
        repository = new ViewCategoryProductRepository(context);
    }

    public void getCategoryProducts(int categoryId, RecyclerView categoryListRV, RecyclerView categoryProductRV) {

        repository.getCategoryProducts(categoryId, categoryListRV, categoryProductRV);

    }
}