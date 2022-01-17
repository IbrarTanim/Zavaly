
package com.zavaly.models.cartdelete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zavaly.models.Cart;

import java.util.List;


public class CartDeleteResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("carts")
    @Expose
    private List<Cart> carts = null;
    @SerializedName("alert")
    @Expose
    private String alert;

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

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

}
