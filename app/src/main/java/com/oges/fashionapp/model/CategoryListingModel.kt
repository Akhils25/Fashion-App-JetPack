package com.oges.fashionapp.model

import com.google.gson.annotations.SerializedName

data class CategoryListingModel(
    @SerializedName("status")
    val status: String,
    @SerializedName("category")
    val category: List<Category>?
) {
    data class Category(
        @SerializedName("catId")
        val catId: String,
        @SerializedName("catName")
        val catName: String,
        @SerializedName("catImage")
        val catImage: String,
        @SerializedName("catRemarks")
        val catRemarks: String
    )
}