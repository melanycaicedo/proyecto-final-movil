package com.proyecto.ecotecsp2.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "esta es mi musica"
    }
    val text: LiveData<String> = _text
}