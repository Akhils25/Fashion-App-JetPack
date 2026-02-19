package com.oges.fashionapp.pages.cart

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CartRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
}