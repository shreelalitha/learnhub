package com.learnhub.app.features.courseDashboard.viewModel

import com.learnhub.app.data.room.CourseEntity

interface CourseUiState {
    data object Loading : CourseUiState

    data class Success(
        val courses: List<CourseEntity>
    ) : CourseUiState

    data class Error(
        val message: String
    ) : CourseUiState
}