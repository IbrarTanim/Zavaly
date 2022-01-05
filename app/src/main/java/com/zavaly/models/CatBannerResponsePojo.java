
package com.zavaly.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CatBannerResponsePojo {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("cats")
    @Expose
    private List<Cat> cats = null;
    @SerializedName("banners")
    @Expose
    private List<String> banners = null;
    @SerializedName("offers")
    @Expose
    private List<Object> offers = null;
    @SerializedName("settings")
    @Expose
    private List<Setting> settings = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public List<String> getBanners() {
        return banners;
    }

    public void setBanners(List<String> banners) {
        this.banners = banners;
    }

    public List<Object> getOffers() {
        return offers;
    }

    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }

}
