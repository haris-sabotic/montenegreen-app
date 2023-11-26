package com.fiveg.montenegreen.models

import com.google.gson.annotations.SerializedName

data class BlogpostModel(
    val name: String,
    val description: String,
    val youtube: String,

    @SerializedName("photo")
    val photoUrl: String,
)
