package com.oges.fashionapp.pages.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oges.fashionapp.model.CategoryListingModel
import com.oges.fashionapp.model.ProductListingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductListingModel.Product>>()
    val products: LiveData<List<ProductListingModel.Product>> = _products

    private val _categories = MutableLiveData<List<CategoryListingModel>>()
    val categories: LiveData<List<CategoryListingModel>> = _categories

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _isLoading.value = true
        _products.value = repository.getProducts()
        _isLoading.value = false
    }

    private fun loadCategories() {
        _isLoading.value = true
        _categories.value = repository.getCategories()
        _isLoading.value = false
    }
}