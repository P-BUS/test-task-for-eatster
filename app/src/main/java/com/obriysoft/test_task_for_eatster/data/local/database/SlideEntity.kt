package com.obriysoft.test_task_for_eatster.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "slides_database")
data class SlideEntity(
    @PrimaryKey val id: Int,
    val position: Int,
    val duration: Int,
    val imageUrl: String
)