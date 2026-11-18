package com.modernapp.gallery.data.local

import androidx.room.*
import com.modernapp.gallery.data.model.MediaItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {
    @Query("SELECT * FROM media_items ORDER BY timestamp DESC")
    fun getAllMedia(): Flow<List<MediaItem>>

    @Query("SELECT * FROM media_items WHERE category = :category ORDER BY timestamp DESC")
    fun getMediaByCategory(category: String): Flow<List<MediaItem>>

    @Query("SELECT * FROM media_items WHERE isFavorite = 1 ORDER BY timestamp DESC")
    fun getFavorites(): Flow<List<MediaItem>>

    @Query("SELECT * FROM media_items WHERE id = :id")
    suspend fun getMediaById(id: Long): MediaItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(item: MediaItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMedia(items: List<MediaItem>)

    @Update
    suspend fun updateMedia(item: MediaItem)

    @Delete
    suspend fun deleteMedia(item: MediaItem)

    @Query("DELETE FROM media_items")
    suspend fun deleteAllMedia()

    @Query("UPDATE media_items SET viewCount = viewCount + 1 WHERE id = :id")
    suspend fun incrementViewCount(id: Long)

    @Query("UPDATE media_items SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("SELECT DISTINCT category FROM media_items ORDER BY category")
    fun getAllCategories(): Flow<List<String>>
}
