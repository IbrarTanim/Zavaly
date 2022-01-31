
package com.zavaly.models.orders;


import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Order implements Parcelable {

    public final static Creator<Order> CREATOR = new Creator<Order>() {

        @Override
        public Order createFromParcel(android.os.Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return (new Order[size]);
        }

    };
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_code")
    @Expose
    private String orderCode;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("subtotal")
    @Expose
    private Integer subtotal;
    @SerializedName("total_discount")
    @Expose
    private Integer totalDiscount;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("shipping_name")
    @Expose
    private String shippingName;
    @SerializedName("shipping_phone")
    @Expose
    private String shippingPhone;
    @SerializedName("shipping_email")
    @Expose
    private String shippingEmail;
    @SerializedName("shipping_address")
    @Expose
    private String shippingAddress;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    protected Order(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderCode = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.subtotal = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalDiscount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.shippingName = ((String) in.readValue((String.class.getClassLoader())));
        this.shippingPhone = ((String) in.readValue((String.class.getClassLoader())));
        this.shippingEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.shippingAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Integer totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingEmail() {
        return shippingEmail;
    }

    public void setShippingEmail(String shippingEmail) {
        this.shippingEmail = shippingEmail;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
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

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(orderCode);
        dest.writeValue(userId);
        dest.writeValue(subtotal);
        dest.writeValue(totalDiscount);
        dest.writeValue(total);
        dest.writeValue(status);
        dest.writeValue(shippingName);
        dest.writeValue(shippingPhone);
        dest.writeValue(shippingEmail);
        dest.writeValue(shippingAddress);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
