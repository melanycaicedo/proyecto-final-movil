package com.proyecto.ecotecsp2.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _isPremiumMode = MutableLiveData<Boolean>()
    val isPremiumMode: LiveData<Boolean> get() = _isPremiumMode

    init {
        _isPremiumMode.value = false // Modo estándar inicialmente
    }

    fun activatePremiumMode() {
        _isPremiumMode.value = true
    }
}