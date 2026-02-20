package com.oges.fashionapp.pages.Home

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oges.fashionapp.model.CategoryListingModel
import com.oges.fashionapp.model.ProductListingModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {
    private inline fun <reified T> getListFromAssets(fileName: String): List<T> {
        return try {
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<T>>() {}.type
            gson.fromJson(jsonString, type)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getProducts(): List<ProductListingModel.Product> {
        return getListFromAssets("products.json")
    }

    fun getCategories(): List<CategoryListingModel> {
        return getListFromAssets("categories.json")
    }
}