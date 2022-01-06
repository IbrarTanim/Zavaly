
package com.zavaly.models.productdetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductDetailsResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("product")
    @Expose
    private Product product;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
