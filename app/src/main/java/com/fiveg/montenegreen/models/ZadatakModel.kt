package com.fiveg.montenegreen.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ZadatakModel(
    val name: String,
    val location: String,
    val points: Int,
    val description: String,

    @SerializedName("photo")
    val photoUrl: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeInt(points)
        parcel.writeString(description)
        parcel.writeString(photoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ZadatakModel> {
        override fun createFromParcel(parcel: Parcel): ZadatakModel {
            return ZadatakModel(parcel)
        }

        override fun newArray(size: Int): Array<ZadatakModel?> {
            return arrayOfNulls(size)
        }
    }
}
