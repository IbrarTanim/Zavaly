
package com.zavaly.models.orders;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class OrdersResponse implements Parcelable {

    public final static Creator<OrdersResponse> CREATOR = new Creator<OrdersResponse>() {


        @Override
        public OrdersResponse createFromParcel(android.os.Parcel in) {
            return new OrdersResponse(in);
        }

        @Override
        public OrdersResponse[] newArray(int size) {
            return (new OrdersResponse[size]);
        }

    };
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;

    protected OrdersResponse(android.os.Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.orders, (Order.class.getClassLoader()));
    }

    public OrdersResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeList(orders);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
