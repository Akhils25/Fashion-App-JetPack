package com.oges.fashionapp.pages.productList

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val repository: ProductListingRepository
) : ViewModel() {
}