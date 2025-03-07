package com.example.testtaskfore.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SlidesResponse(
    @SerialName("data")
    val data: List<Data>
)

@Serializable
data class Data(
    @SerialName("content_type")
    val contentType: Int,
    @SerialName("cta")
    val cta: Int?,
    @SerialName("duration")
    val duration: Double,
    @SerialName("id")
    val id: Int,
    @SerialName("position")
    val position: Int,
    @SerialName("url")
    val url: String
)