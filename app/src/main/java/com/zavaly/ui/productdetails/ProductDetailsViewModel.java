package com.zavaly.ui.productdetails;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zavaly.models.productdetails.ProductDetailsResponse;

import java.util.HashMap;

public class ProductDetailsViewModel extends ViewModel {

    private ProductDetailsRepository repository;

    public void viewModelInit(Context context) {

        repository = new ProductDetailsRepository(context);

    }

    public MutableLiveData<ProductDetailsResponse> getDetails(int productId) {

        return repository.getDetails(productId);

    }

    public void addToCart(HashMap<String, String> params) {
        repository.addToCart(params);
    }
}