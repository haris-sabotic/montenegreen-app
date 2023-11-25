package com.fiveg.montenegreen.models

import android.graphics.drawable.Drawable
import org.osmdroid.util.GeoPoint

data class MarkerModel(
    val title: String,
    val latlong: GeoPoint,
    val iconDrawable: Drawable,
)
