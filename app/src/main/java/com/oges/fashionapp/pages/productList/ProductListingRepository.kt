package com.oges.fashionapp.pages.productList

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ProductListingRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
}