package com.zavaly.ui.cart;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zavaly.models.Cart;

import java.util.HashMap;
import java.util.List;

public class CartViewModel extends ViewModel {

    private CartRepository cartRepository;

    public void initViewModel(Context context) {
        cartRepository = new CartRepository(context);
    }

    public MutableLiveData<List<Cart>> getCartDetails() {
        return cartRepository.getCartDetails();
    }

    public MutableLiveData<Integer> updateProduct(HashMap<String, String> params) {
        return cartRepository.updateProduct(params);
    }

    public MutableLiveData<Integer> deleteCartItem(String productId) {
        return cartRepository.deleteCartItem(productId);
    }

    public MutableLiveData<Integer> checkoutFromCart() {
        return cartRepository.checkoutFromCart();
    }
}