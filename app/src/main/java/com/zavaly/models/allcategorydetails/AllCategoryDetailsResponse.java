
package com.zavaly.models.allcategorydetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AllCategoryDetailsResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("cats")
    @Expose
    private Cats cats;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Cats getCats() {
        return cats;
    }

    public void setCats(Cats cats) {
        this.cats = cats;
    }

}
