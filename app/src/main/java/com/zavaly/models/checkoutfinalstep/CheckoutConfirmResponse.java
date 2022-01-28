
package com.zavaly.models.checkoutfinalstep;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CheckoutConfirmResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("alert")
    @Expose
    private String alert;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

}
