
package com.zavaly.models.ordersdetails;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class OrderDetailsResponse implements Parcelable {

    public final static Creator<OrderDetailsResponse> CREATOR = new Creator<OrderDetailsResponse>() {


        @Override
        public OrderDetailsResponse createFromParcel(Parcel in) {
            return new OrderDetailsResponse(in);
        }

        @Override
        public OrderDetailsResponse[] newArray(int size) {
            return (new OrderDetailsResponse[size]);
        }

    };
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("order")
    @Expose
    private Order order;
    @SerializedName("order_details")
    @Expose
    private List<OrderDetail> orderDetails = null;

    protected OrderDetailsResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.order = ((Order) in.readValue((Order.class.getClassLoader())));
        in.readList(this.orderDetails, (OrderDetail.class.getClassLoader()));
    }

    public OrderDetailsResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(order);
        dest.writeList(orderDetails);
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
