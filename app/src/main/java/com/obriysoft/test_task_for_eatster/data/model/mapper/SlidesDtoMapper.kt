package com.obriysoft.test_task_for_eatster.data.model.mapper

import com.example.testtaskfore.data.model.SlidesResponse
import com.obriysoft.test_task_for_eatster.data.local.database.SlideEntity
import java.net.URLDecoder
import java.net.URLEncoder

fun SlidesResponse.toDatabaseModel(): List<SlideEntity> {
    return this.data.map { data ->
        SlideEntity(
            id = data.id,
            position = data.position,
            duration = data.duration.toInt(),
            imageUrl = data.url.urlEncoding()
        )
    }
}

// TODO: this solution is not working
private fun String.urlEncoding(): String {
    val decodedUrl = URLDecoder.decode(this, "UTF-8")
    val encodedUrl = URLEncoder.encode(decodedUrl, "UTF-8")
    return encodedUrl
}