
package com.zavaly.models.cartview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavaly.models.Cart;

import java.util.List;


public class CartViewResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("carts")
    @Expose
    private List<Cart> carts = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

}
