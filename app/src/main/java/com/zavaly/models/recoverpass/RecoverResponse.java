package com.zavaly.models.recoverpass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecoverResponse {

    @SerializedName("success")
    @Expose
    private boolean success;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
