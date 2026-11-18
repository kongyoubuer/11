package com.modernapp.gallery.data.repository

import com.modernapp.gallery.data.local.MediaDao
import com.modernapp.gallery.data.model.MediaItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepository @Inject constructor(
    private val mediaDao: MediaDao
) {
    fun getAllMedia(): Flow<List<MediaItem>> = mediaDao.getAllMedia()

    fun getMediaByCategory(category: String): Flow<List<MediaItem>> =
        mediaDao.getMediaByCategory(category)

    fun getFavorites(): Flow<List<MediaItem>> = mediaDao.getFavorites()

    suspend fun getMediaById(id: Long): MediaItem? = mediaDao.getMediaById(id)

    suspend fun insertMedia(item: MediaItem): Long = mediaDao.insertMedia(item)

    suspend fun insertAllMedia(items: List<MediaItem>) = mediaDao.insertAllMedia(items)

    suspend fun updateMedia(item: MediaItem) = mediaDao.updateMedia(item)

    suspend fun deleteMedia(item: MediaItem) = mediaDao.deleteMedia(item)

    suspend fun incrementViewCount(id: Long) = mediaDao.incrementViewCount(id)

    suspend fun toggleFavorite(id: Long, isFavorite: Boolean) =
        mediaDao.updateFavoriteStatus(id, isFavorite)

    fun getAllCategories(): Flow<List<String>> = mediaDao.getAllCategories()

    // 初始化示例数据
    suspend fun initializeSampleData() {
        val sampleData = listOf(
            MediaItem(
                title = "壮丽山河",
                description = "清晨的山脉被云雾缠绕，展现大自然的鬼斧神工",
                imageUrl = "https://picsum.photos/800/600?random=1",
                category = "NATURE",
                tags = "山脉,云雾,风景"
            ),
            MediaItem(
                title = "现代都市",
                description = "繁华都市夜景，霓虹闪烁，展现城市活力",
                imageUrl = "https://picsum.photos/800/600?random=2",
                category = "CITY",
                tags = "城市,夜景,建筑"
            ),
            MediaItem(
                title = "海滨日落",
                description = "金色的夕阳洒在海面上，波光粼粼",
                imageUrl = "https://picsum.photos/800/600?random=3",
                videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                category = "NATURE",
                tags = "日落,海洋,黄昏"
            ),
            MediaItem(
                title = "美食艺术",
                description = "精致的料理摆盘，色香味俱全",
                imageUrl = "https://picsum.photos/800/600?random=4",
                category = "FOOD",
                tags = "美食,料理,艺术"
            ),
            MediaItem(
                title = "科技未来",
                description = "前沿科技产品，引领未来生活",
                imageUrl = "https://picsum.photos/800/600?random=5",
                category = "TECHNOLOGY",
                tags = "科技,创新,未来"
            ),
            MediaItem(
                title = "艺术画廊",
                description = "现代艺术作品展览，充满创意与想象",
                imageUrl = "https://picsum.photos/800/600?random=6",
                category = "ART",
                tags = "艺术,画廊,展览"
            ),
            MediaItem(
                title = "运动瞬间",
                description = "捕捉运动员的精彩瞬间",
                imageUrl = "https://picsum.photos/800/600?random=7",
                category = "SPORTS",
                tags = "运动,竞技,活力"
            ),
            MediaItem(
                title = "旅行记忆",
                description = "世界各地的旅行足迹与美好回忆",
                imageUrl = "https://picsum.photos/800/600?random=8",
                category = "TRAVEL",
                tags = "旅行,探索,冒险"
            ),
            MediaItem(
                title = "森林秘境",
                description = "茂密森林中的神秘小径",
                imageUrl = "https://picsum.photos/800/600?random=9",
                category = "NATURE",
                tags = "森林,绿色,自然"
            ),
            MediaItem(
                title = "建筑奇观",
                description = "世界著名建筑的独特设计",
                imageUrl = "https://picsum.photos/800/600?random=10",
                category = "CITY",
                tags = "建筑,设计,地标"
            )
        )
        mediaDao.insertAllMedia(sampleData)
    }
}
