package com.modernapp.gallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.modernapp.gallery.data.model.MediaItem

@Database(
    entities = [MediaItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao
}
