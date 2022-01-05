package com.zavaly.cache.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login_cache")
public class LoginCache {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "login_id")
    private int id;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "user_phone")
    private String userPhone;

    @ColumnInfo(name = "user_token")
    private String userToken;

    @ColumnInfo(name = "user_avater")
    private String userAvater;

    public LoginCache(String userName, String userPhone, String userToken, String userAvater) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userToken = userToken;
        this.userAvater = userAvater;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserAvater() {
        return userAvater;
    }

    public void setUserAvater(String userAvater) {
        this.userAvater = userAvater;
    }


    @Override
    public String toString() {
        return "LoginCache{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userToken='" + userToken + '\'' +
                ", userAvater='" + userAvater + '\'' +
                '}';
    }
}
