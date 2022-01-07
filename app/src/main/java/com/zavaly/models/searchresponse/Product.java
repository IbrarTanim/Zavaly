
package com.zavaly.models.searchresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("disprice")
    @Expose
    private Integer disprice;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("hasbrand")
    @Expose
    private String hasbrand;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("user_role")
    @Expose
    private String userRole;
    @SerializedName("ownedtype")
    @Expose
    private String ownedtype;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("forapp")
    @Expose
    private String forapp;
    @SerializedName("forweb")
    @Expose
    private String forweb;
    @SerializedName("cashback")
    @Expose
    private Integer cashback;
    @SerializedName("offer_id")
    @Expose
    private Object offerId;
    @SerializedName("commission")
    @Expose
    private Integer commission;
    @SerializedName("catcommission")
    @Expose
    private Integer catcommission;
    @SerializedName("categoryinfo")
    @Expose
    private Categoryinfo categoryinfo;
    @SerializedName("campaign")
    @Expose
    private String campaign;
    @SerializedName("campaign_id")
    @Expose
    private Object campaignId;
    @SerializedName("can_wholesale")
    @Expose
    private String canWholesale;
    @SerializedName("min_buy")
    @Expose
    private Object minBuy;
    @SerializedName("fastdeal")
    @Expose
    private String fastdeal;
    @SerializedName("longdeal")
    @Expose
    private String longdeal;
    @SerializedName("promotionenddate")
    @Expose
    private Object promotionenddate;
    @SerializedName("promotionendhour")
    @Expose
    private Object promotionendhour;
    @SerializedName("promotionendmin")
    @Expose
    private Object promotionendmin;
    @SerializedName("brands")
    @Expose
    private List<Object> brands = null;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("bonus_buyable")
    @Expose
    private Integer bonusBuyable;
    @SerializedName("ignoreMinimumCart")
    @Expose
    private String ignoreMinimumCart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDisprice() {
        return disprice;
    }

    public void setDisprice(Integer disprice) {
        this.disprice = disprice;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getHasbrand() {
        return hasbrand;
    }

    public void setHasbrand(String hasbrand) {
        this.hasbrand = hasbrand;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getOwnedtype() {
        return ownedtype;
    }

    public void setOwnedtype(String ownedtype) {
        this.ownedtype = ownedtype;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getForapp() {
        return forapp;
    }

    public void setForapp(String forapp) {
        this.forapp = forapp;
    }

    public String getForweb() {
        return forweb;
    }

    public void setForweb(String forweb) {
        this.forweb = forweb;
    }

    public Integer getCashback() {
        return cashback;
    }

    public void setCashback(Integer cashback) {
        this.cashback = cashback;
    }

    public Object getOfferId() {
        return offerId;
    }

    public void setOfferId(Object offerId) {
        this.offerId = offerId;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getCatcommission() {
        return catcommission;
    }

    public void setCatcommission(Integer catcommission) {
        this.catcommission = catcommission;
    }

    public Categoryinfo getCategoryinfo() {
        return categoryinfo;
    }

    public void setCategoryinfo(Categoryinfo categoryinfo) {
        this.categoryinfo = categoryinfo;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public Object getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Object campaignId) {
        this.campaignId = campaignId;
    }

    public String getCanWholesale() {
        return canWholesale;
    }

    public void setCanWholesale(String canWholesale) {
        this.canWholesale = canWholesale;
    }

    public Object getMinBuy() {
        return minBuy;
    }

    public void setMinBuy(Object minBuy) {
        this.minBuy = minBuy;
    }

    public String getFastdeal() {
        return fastdeal;
    }

    public void setFastdeal(String fastdeal) {
        this.fastdeal = fastdeal;
    }

    public String getLongdeal() {
        return longdeal;
    }

    public void setLongdeal(String longdeal) {
        this.longdeal = longdeal;
    }

    public Object getPromotionenddate() {
        return promotionenddate;
    }

    public void setPromotionenddate(Object promotionenddate) {
        this.promotionenddate = promotionenddate;
    }

    public Object getPromotionendhour() {
        return promotionendhour;
    }

    public void setPromotionendhour(Object promotionendhour) {
        this.promotionendhour = promotionendhour;
    }

    public Object getPromotionendmin() {
        return promotionendmin;
    }

    public void setPromotionendmin(Object promotionendmin) {
        this.promotionendmin = promotionendmin;
    }

    public List<Object> getBrands() {
        return brands;
    }

    public void setBrands(List<Object> brands) {
        this.brands = brands;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Integer getBonusBuyable() {
        return bonusBuyable;
    }

    public void setBonusBuyable(Integer bonusBuyable) {
        this.bonusBuyable = bonusBuyable;
    }

    public String getIgnoreMinimumCart() {
        return ignoreMinimumCart;
    }

    public void setIgnoreMinimumCart(String ignoreMinimumCart) {
        this.ignoreMinimumCart = ignoreMinimumCart;
    }

}
