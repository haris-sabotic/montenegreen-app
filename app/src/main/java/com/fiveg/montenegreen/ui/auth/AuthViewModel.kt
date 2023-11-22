package com.fiveg.montenegreen.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Auth"
    }
    val text: LiveData<String> = _text
}