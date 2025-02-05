package com.kh.sbilyhour.composestructure.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kh.sbilyhour.composestructure.data.datasource.local.datastore.UserPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPreferencesDataSource: UserPreferencesDataSource
) :
    ViewModel() {

    // 🔄 Slider Images State
    private val _sliderImages = MutableStateFlow(
        listOf(
            "https://www.svcover.nl/images/ogimage.jpg",
            "https://www.svcover.nl/images/ogimage.jpg",
            "https://www.svcover.nl/images/ogimage.jpg"
        )
    )
    val sliderImages: StateFlow<List<String>> = _sliderImages.asStateFlow()

    // 📌 List Items State
    private val _itemsList = MutableStateFlow(
        listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
            "Item 7",
            "Item 8",
            "Item 9"
        )
    )
    val itemsList: StateFlow<List<String>> = _itemsList.asStateFlow()

    // 🚀 Auto-Sliding Effect for Carousel
    private val _currentPage = MutableStateFlow(0)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

    init {
        autoSlideImages()
    }

    private fun autoSlideImages() {
        viewModelScope.launch {
            while (true) {
                delay(3000) // Change slide every 3 seconds
                _currentPage.value = (_currentPage.value + 1) % _sliderImages.value.size
            }
        }
    }
}