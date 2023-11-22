package com.fiveg.montenegreen.ui.popusti

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PopustiViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Popusti"
    }
    val text: LiveData<String> = _text
}