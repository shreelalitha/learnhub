package com.learnhub.app.features.courseDetail.viewModel

import com.learnhub.app.data.room.CourseEntity

interface CourseDetailsUiState {
    data object Loading : CourseDetailsUiState

    data class Success(
        val course: CourseEntity
    ) : CourseDetailsUiState

    data class Error(
        val message: String
    ) : CourseDetailsUiState
}