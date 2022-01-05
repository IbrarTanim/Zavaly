
package com.zavaly.models.allcategorydetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


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
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("disprice")
    @Expose
    private Integer disprice;
    @SerializedName("cashback")
    @Expose
    private Integer cashback;
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
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("forapp")
    @Expose
    private String forapp;
    @SerializedName("forweb")
    @Expose
    private String forweb;
    @SerializedName("commission")
    @Expose
    private Integer commission;
    @SerializedName("bonus_buyable")
    @Expose
    private Integer bonusBuyable;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("ignoreMinimumCart")
    @Expose
    private String ignoreMinimumCart;
    @SerializedName("campaign")
    @Expose
    private String campaign;
    @SerializedName("campaign_id")
    @Expose
    private String campaignId;
    @SerializedName("agentcat_id")
    @Expose
    private String agentcatId;
    @SerializedName("can_wholesale")
    @Expose
    private String canWholesale;
    @SerializedName("min_buy")
    @Expose
    private Object minBuy;
    @SerializedName("pricing")
    @Expose
    private String pricing;
    @SerializedName("fastdeal")
    @Expose
    private String fastdeal;
    @SerializedName("longdeal")
    @Expose
    private String longdeal;
    @SerializedName("flashsale")
    @Expose
    private String flashsale;
    @SerializedName("promotionenddate")
    @Expose
    private Object promotionenddate;
    @SerializedName("promotionendhour")
    @Expose
    private Object promotionendhour;
    @SerializedName("promotionendmin")
    @Expose
    private Object promotionendmin;

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

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getDisprice() {
        return disprice;
    }

    public void setDisprice(Integer disprice) {
        this.disprice = disprice;
    }

    public Integer getCashback() {
        return cashback;
    }

    public void setCashback(Integer cashback) {
        this.cashback = cashback;
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

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getBonusBuyable() {
        return bonusBuyable;
    }

    public void setBonusBuyable(Integer bonusBuyable) {
        this.bonusBuyable = bonusBuyable;
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

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getIgnoreMinimumCart() {
        return ignoreMinimumCart;
    }

    public void setIgnoreMinimumCart(String ignoreMinimumCart) {
        this.ignoreMinimumCart = ignoreMinimumCart;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getAgentcatId() {
        return agentcatId;
    }

    public void setAgentcatId(String agentcatId) {
        this.agentcatId = agentcatId;
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

    public String getPricing() {
        return pricing;
    }

    public void setPricing(String pricing) {
        this.pricing = pricing;
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

    public String getFlashsale() {
        return flashsale;
    }

    public void setFlashsale(String flashsale) {
        this.flashsale = flashsale;
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

}
