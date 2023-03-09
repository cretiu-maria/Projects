package com.example.proiect_scd_mobile.networking;

import com.google.gson.annotations.SerializedName;

public class collection {

    @SerializedName("_id")
    String _id;

    @SerializedName("floorPrice")
    String floorPrice;

    @SerializedName("name")
    String name;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(String floorPrice) {
        this.floorPrice = floorPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
