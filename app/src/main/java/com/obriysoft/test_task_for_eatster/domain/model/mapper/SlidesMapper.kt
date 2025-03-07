package com.obriysoft.test_task_for_eatster.domain.model.mapper

import com.obriysoft.test_task_for_eatster.data.local.database.SlideEntity
import com.obriysoft.test_task_for_eatster.domain.model.Slide

fun List<SlideEntity>.toDomainModel(): List<Slide> {
    return this.map { data ->
        Slide(
            id = data.id,
            orderPosition = data.position,
            onScreenDuration = data.duration,
            imageUrl = data.imageUrl
        )
    }
}