package com.obriysoft.test_task_for_eatster.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_database")
data class SlidesEntity(
    val imageUrl: String = "",
    val duration: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0
}