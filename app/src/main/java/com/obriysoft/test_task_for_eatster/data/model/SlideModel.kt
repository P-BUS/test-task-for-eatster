package com.example.testtaskfore.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Slide(
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("duration")
    val duration: Int
)
