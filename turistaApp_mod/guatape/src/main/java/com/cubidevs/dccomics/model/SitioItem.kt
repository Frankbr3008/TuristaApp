package com.cubidevs.dccomics.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SitioItem(
    @SerializedName("place_name")
    val placename: String,
    @SerializedName("place_descripcion")
    val placedescripcion: String,
    @SerializedName("stars")
    val stars: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
) : Serializable