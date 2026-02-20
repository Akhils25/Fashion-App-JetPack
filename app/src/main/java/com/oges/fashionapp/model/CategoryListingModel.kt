package com.oges.fashionapp.model

import com.google.gson.annotations.SerializedName

data class CategoryListingModel(
    @SerializedName("catId")
    val catId: String,
    @SerializedName("catName")
    val catName: String,
    @SerializedName("catImage")
    val catImage: String,
    @SerializedName("catRemarks")
    val catRemarks: String
)
