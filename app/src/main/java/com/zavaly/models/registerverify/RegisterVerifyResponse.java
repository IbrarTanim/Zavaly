
package com.zavaly.models.registerverify;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RegisterVerifyResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("token")
    @Expose
    private String token;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
