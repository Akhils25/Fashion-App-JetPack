package com.oges.fashionapp.pages.Home

import android.content.Context
import com.google.gson.Gson
import com.oges.fashionapp.model.CategoryListingModel
import com.oges.fashionapp.model.ProductListingModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {

    fun getProducts(): List<ProductListingModel.Product> {
        return try {
            val jsonString = context.assets.open("products.json").bufferedReader().use { it.readText() }
            val response = gson.fromJson(jsonString, ProductListingModel::class.java)
            val list = response?.product ?: emptyList()
            list
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getCategories(): List<CategoryListingModel.Category> {
        return try {
            val jsonString = context.assets.open("categories.json").bufferedReader().use { it.readText() }
            val response = gson.fromJson(jsonString, CategoryListingModel::class.java)
            val list = response?.category ?: emptyList()
            list
        } catch (e: Exception) {
            emptyList()
        }
    }
}