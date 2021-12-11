package com.example.guatapev4.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GuatapeItem (
    @SerializedName("name")
    val name: String,
    @SerializedName("short_d")
    val short_d: String,
    @SerializedName("large_d")
    val large_d: String,
    @SerializedName("stars")
    val stars: String,
    @SerializedName("imageUrl")
    val imageUrl: String
    ) : Serializable
