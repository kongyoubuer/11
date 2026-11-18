package com.modernapp.gallery.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modernapp.gallery.data.model.MediaItem
import com.modernapp.gallery.data.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val mediaItems: List<MediaItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategory: String = "ALL"
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MediaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _selectedCategory = MutableStateFlow("ALL")

    init {
        loadMedia()
        initializeSampleDataIfNeeded()
    }

    private fun initializeSampleDataIfNeeded() {
        viewModelScope.launch {
            try {
                repository.getAllMedia().first().let { items ->
                    if (items.isEmpty()) {
                        repository.initializeSampleData()
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "初始化数据失败: ${e.message}") }
            }
        }
    }

    private fun loadMedia() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                combine(
                    repository.getAllMedia(),
                    _selectedCategory
                ) { items, category ->
                    if (category == "ALL") items
                    else items.filter { it.category == category }
                }.collect { filteredItems ->
                    _uiState.update {
                        it.copy(
                            mediaItems = filteredItems,
                            isLoading = false,
                            error = null
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

    fun selectCategory(category: String) {
        _selectedCategory.value = category
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun toggleFavorite(itemId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(itemId, isFavorite)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "操作失败: ${e.message}") }
            }
        }
    }

    fun refresh() {
        loadMedia()
    }
}
