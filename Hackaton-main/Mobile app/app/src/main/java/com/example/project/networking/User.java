package com.example.project.networking;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    Integer id;

    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    @SerializedName("roleId")
    Integer roleId;

    @SerializedName("productId")
    Integer productId;

    @SerializedName("name")
    String name;

    @SerializedName("email")
    String email;

    @SerializedName("phoneNumber")
    String phoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}




