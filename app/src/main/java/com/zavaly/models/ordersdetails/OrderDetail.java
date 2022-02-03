
package com.zavaly.models.ordersdetails;


import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OrderDetail implements Parcelable {

    public final static Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {


        @Override
        public OrderDetail createFromParcel(android.os.Parcel in) {
            return new OrderDetail(in);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return (new OrderDetail[size]);
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
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("discount")
    @Expose
    private Double discount;
    @SerializedName("subtotal")
    @Expose
    private Double subtotal;
    @SerializedName("total_discount")
    @Expose
    private Double totalDiscount;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("product_for")
    @Expose
    private String productFor;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    protected OrderDetail(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orderCode = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.product = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        this.size = ((String) in.readValue((String.class.getClassLoader())));
        this.quantity = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.price = ((Double) in.readValue((Double.class.getClassLoader())));
        this.discount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.subtotal = ((Double) in.readValue((Double.class.getClassLoader())));
        this.totalDiscount = ((Double) in.readValue((Double.class.getClassLoader())));
        this.total = ((Double) in.readValue((Double.class.getClassLoader())));
        this.productFor = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderDetail() {
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getProductFor() {
        return productFor;
    }

    public void setProductFor(String productFor) {
        this.productFor = productFor;
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
        dest.writeValue(product);
        dest.writeValue(color);
        dest.writeValue(size);
        dest.writeValue(quantity);
        dest.writeValue(price);
        dest.writeValue(discount);
        dest.writeValue(subtotal);
        dest.writeValue(totalDiscount);
        dest.writeValue(total);
        dest.writeValue(productFor);
        dest.writeValue(createdAt);
        dest.writeValue(updatedAt);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
