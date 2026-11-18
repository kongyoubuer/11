package com.modernapp.gallery.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_items")
data class MediaItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val videoUrl: String? = null,
    val category: String,
    val tags: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false,
    val viewCount: Int = 0
)

enum class MediaCategory(val displayName: String) {
    NATURE("自然风光"),
    CITY("城市建筑"),
    PEOPLE("人物肖像"),
    FOOD("美食"),
    TECHNOLOGY("科技"),
    ART("艺术"),
    SPORTS("体育"),
    TRAVEL("旅行")
}
