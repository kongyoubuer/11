package com.modernapp.gallery.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modernapp.gallery.data.model.MediaItem
import com.modernapp.gallery.data.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val mediaItem: MediaItem? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MediaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val itemId: Long = savedStateHandle.get<Long>("itemId") ?: 0L

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        loadMediaDetail()
    }

    private fun loadMediaDetail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val item = repository.getMediaById(itemId)
                if (item != null) {
                    repository.incrementViewCount(itemId)
                    _uiState.update {
                        it.copy(
                            mediaItem = item.copy(viewCount = item.viewCount + 1),
                            isLoading = false,
                            error = null
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "内容未找到"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "加载失败: ${e.message}"
                    )
                }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            _uiState.value.mediaItem?.let { item ->
                try {
                    val newFavoriteStatus = !item.isFavorite
                    repository.toggleFavorite(item.id, newFavoriteStatus)
                    _uiState.update {
                        it.copy(mediaItem = item.copy(isFavorite = newFavoriteStatus))
                    }
                } catch (e: Exception) {
                    _uiState.update { it.copy(error = "操作失败: ${e.message}") }
                }
            }
        }
    }
}
