package com.fiveg.montenegreen.api.requests

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,

    @SerializedName("confirm_password")
    val confirmPassword: String,
)
