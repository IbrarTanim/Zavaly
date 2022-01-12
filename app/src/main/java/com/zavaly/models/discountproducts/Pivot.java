
package com.zavaly.models.discountproducts;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pivot {

    @SerializedName("products_id")
    @Expose
    private Integer productsId;
    @SerializedName("brands_id")
    @Expose
    private Integer brandsId;

    public Integer getProductsId() {
        return productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }

    public Integer getBrandsId() {
        return brandsId;
    }

    public void setBrandsId(Integer brandsId) {
        this.brandsId = brandsId;
    }

}
