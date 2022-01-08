
package com.zavaly.models.shopsresponse;

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
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("shopname")
    @Expose
    private String shopname;
    @SerializedName("shoplogo")
    @Expose
    private Object shoplogo;
    @SerializedName("shoplocation")
    @Expose
    private String shoplocation;
    @SerializedName("sublocations")
    @Expose
    private String sublocations;
    @SerializedName("shopaddress")
    @Expose
    private String shopaddress;
    @SerializedName("shopcontact")
    @Expose
    private String shopcontact;
    @SerializedName("reviews")
    @Expose
    private List<Object> reviews = null;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("approved")
    @Expose
    private Integer approved;
    @SerializedName("disabled")
    @Expose
    private Integer disabled;
    @SerializedName("verified")
    @Expose
    private Integer verified;
    @SerializedName("commission")
    @Expose
    private Integer commission;
    @SerializedName("refercode")
    @Expose
    private String refercode;
    @SerializedName("referby")
    @Expose
    private Object referby;
    @SerializedName("added_by")
    @Expose
    private Object addedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("totalsale")
    @Expose
    private List<Object> totalsale = null;
    @SerializedName("totalpending")
    @Expose
    private List<Object> totalpending = null;
    @SerializedName("depuy")
    @Expose
    private String depuy;
    @SerializedName("bonus_use_limit")
    @Expose
    private Integer bonusUseLimit;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public Object getShoplogo() {
        return shoplogo;
    }

    public void setShoplogo(Object shoplogo) {
        this.shoplogo = shoplogo;
    }

    public String getShoplocation() {
        return shoplocation;
    }

    public void setShoplocation(String shoplocation) {
        this.shoplocation = shoplocation;
    }

    public String getSublocations() {
        return sublocations;
    }

    public void setSublocations(String sublocations) {
        this.sublocations = sublocations;
    }

    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress;
    }

    public String getShopcontact() {
        return shopcontact;
    }

    public void setShopcontact(String shopcontact) {
        this.shopcontact = shopcontact;
    }

    public List<Object> getReviews() {
        return reviews;
    }

    public void setReviews(List<Object> reviews) {
        this.reviews = reviews;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public String getRefercode() {
        return refercode;
    }

    public void setRefercode(String refercode) {
        this.refercode = refercode;
    }

    public Object getReferby() {
        return referby;
    }

    public void setReferby(Object referby) {
        this.referby = referby;
    }

    public Object getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Object addedBy) {
        this.addedBy = addedBy;
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

    public List<Object> getTotalsale() {
        return totalsale;
    }

    public void setTotalsale(List<Object> totalsale) {
        this.totalsale = totalsale;
    }

    public List<Object> getTotalpending() {
        return totalpending;
    }

    public void setTotalpending(List<Object> totalpending) {
        this.totalpending = totalpending;
    }

    public String getDepuy() {
        return depuy;
    }

    public void setDepuy(String depuy) {
        this.depuy = depuy;
    }

    public Integer getBonusUseLimit() {
        return bonusUseLimit;
    }

    public void setBonusUseLimit(Integer bonusUseLimit) {
        this.bonusUseLimit = bonusUseLimit;
    }

}
