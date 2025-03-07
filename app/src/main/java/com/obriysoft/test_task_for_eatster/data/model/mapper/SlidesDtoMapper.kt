package com.obriysoft.test_task_for_eatster.data.model.mapper

import com.example.testtaskfore.data.model.SlidesResponse
import com.obriysoft.test_task_for_eatster.data.local.database.SlideEntity

fun SlidesResponse.toDatabaseModel(): List<SlideEntity> {
    return this.data.map { data ->
        SlideEntity(
            id = data.id,
            position = data.position,
            duration = data.duration.toInt(),
            imageUrl = data.url
        )
    }
}