package com.fiveg.montenegreen.ui.zadaci

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ZadaciViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Zadaci"
    }
    val text: LiveData<String> = _text
}