
package com.zavaly.models.allcategorydetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("commission")
    @Expose
    private Integer commission;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("forCustomer")
    @Expose
    private String forCustomer;
    @SerializedName("forShopkeeper")
    @Expose
    private String forShopkeeper;
    @SerializedName("protype")
    @Expose
    private String protype;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    @SerializedName("products")
    @Expose
    private List<Product__1> products = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getForCustomer() {
        return forCustomer;
    }

    public void setForCustomer(String forCustomer) {
        this.forCustomer = forCustomer;
    }

    public String getForShopkeeper() {
        return forShopkeeper;
    }

    public void setForShopkeeper(String forShopkeeper) {
        this.forShopkeeper = forShopkeeper;
    }

    public String getProtype() {
        return protype;
    }

    public void setProtype(String protype) {
        this.protype = protype;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Product__1> getProducts() {
        return products;
    }

    public void setProducts(List<Product__1> products) {
        this.products = products;
    }

}
