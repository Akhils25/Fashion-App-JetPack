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
        @SerializedName("brand")
        val brand: String,
        @SerializedName("discount")
        val discount: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("isTrending")
        val isTrending: Boolean,
        @SerializedName("material")
        val material: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("occasion")
        val occasion: String,
        @SerializedName("price")
        val price: Double,
        @SerializedName("rating")
        val rating: Double
    )
}