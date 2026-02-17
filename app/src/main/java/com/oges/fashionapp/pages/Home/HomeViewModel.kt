package com.oges.fashionapp.pages.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oges.fashionapp.model.ProductListingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _products = MutableLiveData<List<ProductListingModel.Product>>()
    val products: LiveData<List<ProductListingModel.Product>> = _products

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = repository.getLocalProducts()
            _isLoading.value = false

            result.onSuccess {
                _products.value = it
            }.onFailure {
                // Handle error
            }
        }
    }
}