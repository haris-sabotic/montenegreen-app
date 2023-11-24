package com.fiveg.montenegreen.models

import com.google.gson.annotations.SerializedName

data class PopustModel(
    val name: String,
    val location: String,
    val points: Int,
    val description: String,

    @SerializedName("photo")
    val photoUrl: String,
)
