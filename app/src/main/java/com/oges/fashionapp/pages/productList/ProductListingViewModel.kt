package com.oges.fashionapp.pages.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oges.fashionapp.model.ProductListingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListingViewModel @Inject constructor(
    private val repository: ProductListingRepository
) : ViewModel() {
    private val _products = MutableLiveData<List<ProductListingModel.Product>>()
    val products: LiveData<List<ProductListingModel.Product>> = _products
}