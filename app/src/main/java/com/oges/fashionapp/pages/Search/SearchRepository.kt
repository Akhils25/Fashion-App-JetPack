package com.oges.fashionapp.pages.Search

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SearchRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
}