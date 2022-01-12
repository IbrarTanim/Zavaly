
package com.zavaly.models.discountproducts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DiscountProductsResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
