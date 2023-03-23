package com.example.project.networking;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    Integer id;

    @SerializedName("productId")
    Integer productId;

    @SerializedName("productName")
    String productName;

    @SerializedName("productDetails")
    String productDetails;

    @SerializedName("category")
    String productCategory;

    @SerializedName("availability")
    String productAvailable;


    @SerializedName("quantity")
    String productQuantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(String productAvailable) {
        this.productAvailable = productAvailable;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }
}
