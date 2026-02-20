package com.oges.fashionapp.model


import com.google.gson.annotations.SerializedName

data class ProductListingModel(
    @SerializedName("category")
    val category: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("status")
    val status: String
) {
    data class Product(
        @SerializedName("productId")
        val productId: String,
        @SerializedName("categoryId")
        val categoryId: String,
        @SerializedName("productCategoryName")
        val productCategoryName: String,
        @SerializedName("productName")
        val productName: String,
        @SerializedName("productImage")
        val productImage: String,
        @SerializedName("currency")
        val currency: String,
        @SerializedName("productMRP")
        val productMRP: Double,
        @SerializedName("sellingPrice")
        val sellingPrice: Double,
        @SerializedName("offerType")
        val offerType: String,
        @SerializedName("offerValues")
        val offerValues: Int,
        @SerializedName("quantity")
        val quantity: Int,
        @SerializedName("unitType")
        val unitType: String,
        @SerializedName("stockStatus")
        val stockStatus: Boolean,
        @SerializedName("availableProductCount")
        val availableProductCount: Int,
        @SerializedName("totalProductCount")
        val totalProductCount: Int,
        @SerializedName("expirationDate")
        val expirationDate: String,
        @SerializedName("isInCart")
        val isInCart: Boolean,
        @SerializedName("productQuantityInCart")
        val productQuantityInCart: Int,
        @SerializedName("productRemarks")
        val productRemarks: String,
        @SerializedName("indirectCartmessage")
        val indirectCartmessage: String,
        @SerializedName("indirectCartAdd")
        val indirectCartAdd: Int
    )
}