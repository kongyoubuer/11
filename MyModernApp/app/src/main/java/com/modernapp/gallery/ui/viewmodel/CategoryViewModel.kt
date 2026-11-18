package com.modernapp.gallery.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modernapp.gallery.data.model.MediaCategory
import com.modernapp.gallery.data.model.MediaItem
import com.modernapp.gallery.data.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CategoryUiState(
    val categories: List<CategoryWithItems> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class CategoryWithItems(
    val category: MediaCategory,
    val items: List<MediaItem>,
    val count: Int
)

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: MediaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                repository.getAllMedia().collect { allItems ->
                    val categoriesWithItems = MediaCategory.values().map { category ->
                        val categoryItems = allItems.filter { it.category == category.name }
                        CategoryWithItems(
                            category = category,
                            items = categoryItems.take(6), // 每个分类显示前6个
                            count = categoryItems.size
                        )
                    }.filter { it.count > 0 }

                    _uiState.update {
                        it.copy(
                            categories = categoriesWithItems,
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

    fun refresh() {
        loadCategories()
    }
}
