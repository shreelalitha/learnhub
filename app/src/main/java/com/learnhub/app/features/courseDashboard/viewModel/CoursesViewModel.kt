package com.learnhub.app.features.courseDashboard.viewModel

import androidx.lifecycle.ViewModel
import com.learnhub.app.features.courseDashboard.repo.CourseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(private val repo: CourseRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<CourseUiState>(CourseUiState.Loading)
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

}