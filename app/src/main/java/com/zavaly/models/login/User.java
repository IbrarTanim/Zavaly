
package com.zavaly.models.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("avater")
    @Expose
    private Object avater;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("verified")
    @Expose
    private Integer verified;
    @SerializedName("approved")
    @Expose
    private Integer approved;
    @SerializedName("disabled")
    @Expose
    private Integer disabled;
    @SerializedName("commission")
    @Expose
    private String commission;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("refercode")
    @Expose
    private String refercode;
    @SerializedName("referby")
    @Expose
    private Object referby;
    @SerializedName("added_by")
    @Expose
    private Object addedBy;
    @SerializedName("shopname")
    @Expose
    private Object shopname;
    @SerializedName("shoplogo")
    @Expose
    private Object shoplogo;
    @SerializedName("shoplocation")
    @Expose
    private Object shoplocation;
    @SerializedName("sublocations")
    @Expose
    private String sublocations;
    @SerializedName("shopcontact")
    @Expose
    private Object shopcontact;
    @SerializedName("shopaddress")
    @Expose
    private Object shopaddress;
    @SerializedName("email_verified_at")
    @Expose
    private Object emailVerifiedAt;
    @SerializedName("depuy")
    @Expose
    private Object depuy;
    @SerializedName("bonus_use_limit")
    @Expose
    private Integer bonusUseLimit;
    @SerializedName("mall_id")
    @Expose
    private Object mallId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getAvater() {
        return avater;
    }

    public void setAvater(Object avater) {
        this.avater = avater;
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

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
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

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Object getShopname() {
        return shopname;
    }

    public void setShopname(Object shopname) {
        this.shopname = shopname;
    }

    public Object getShoplogo() {
        return shoplogo;
    }

    public void setShoplogo(Object shoplogo) {
        this.shoplogo = shoplogo;
    }

    public Object getShoplocation() {
        return shoplocation;
    }

    public void setShoplocation(Object shoplocation) {
        this.shoplocation = shoplocation;
    }

    public String getSublocations() {
        return sublocations;
    }

    public void setSublocations(String sublocations) {
        this.sublocations = sublocations;
    }

    public Object getShopcontact() {
        return shopcontact;
    }

    public void setShopcontact(Object shopcontact) {
        this.shopcontact = shopcontact;
    }

    public Object getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(Object shopaddress) {
        this.shopaddress = shopaddress;
    }

    public Object getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Object emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public Object getDepuy() {
        return depuy;
    }

    public void setDepuy(Object depuy) {
        this.depuy = depuy;
    }

    public Integer getBonusUseLimit() {
        return bonusUseLimit;
    }

    public void setBonusUseLimit(Integer bonusUseLimit) {
        this.bonusUseLimit = bonusUseLimit;
    }

    public Object getMallId() {
        return mallId;
    }

    public void setMallId(Object mallId) {
        this.mallId = mallId;
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

}
