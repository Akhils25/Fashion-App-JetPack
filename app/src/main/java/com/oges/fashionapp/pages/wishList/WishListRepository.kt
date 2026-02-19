package com.oges.fashionapp.pages.wishList

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WishListRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
}