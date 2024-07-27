package com.proyecto.ecotecsp2.ui.albumes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlbumViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Este es mi album"
    }
    val text: LiveData<String> = _text
}