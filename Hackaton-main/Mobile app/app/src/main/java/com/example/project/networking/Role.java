package com.example.project.networking;

import com.google.gson.annotations.SerializedName;

public class Role {
    @SerializedName("id")
    Integer id;

    @SerializedName("role")
    String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
