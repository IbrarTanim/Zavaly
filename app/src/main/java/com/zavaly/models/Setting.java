
package com.zavaly.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Setting {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("settingname")
    @Expose
    private String settingname;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("details")
    @Expose
    private Object details;
    @SerializedName("value")
    @Expose
    private Object value;
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

    public String getSettingname() {
        return settingname;
    }

    public void setSettingname(String settingname) {
        this.settingname = settingname;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
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
