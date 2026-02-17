package com.oges.fashionapp.pages.Home

import android.content.Context
import com.google.gson.Gson
import com.oges.fashionapp.model.ProductListingModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun getLocalProducts(): Result<List<ProductListingModel.Product>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val jsonString = context.assets.open("products.json").use { inputStream ->
                    inputStream.bufferedReader().use { it.readText() }
                }
                val response = Gson().fromJson(jsonString, ProductListingModel::class.java)
                response.products ?: emptyList()
            }
        }
    }
}