package com.learnhub.app.features.courseDashboard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learnhub.app.features.courseDashboard.repo.CourseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(private val repo: CourseRepo) : ViewModel() {

    private val _uiState = MutableStateFlow<CourseUiState>(CourseUiState.Loading)
    val uiState: StateFlow<CourseUiState> = _uiState

    init {
        getCourses()
    }

    private fun getCourses() {
        viewModelScope.launch {
            try {
                val localCourses = repo.getCoursesFromRoom().first()

                if (localCourses.isEmpty()) {
                    val remoteCourses = repo.fetchCoursesFromRemote()
                    repo.insertCoursesToRoom(remoteCourses)
                }

                repo.getCoursesFromRoom().collect { list ->
                    _uiState.value = CourseUiState.Success(list)
                }

            } catch (e: Exception) {
                _uiState.value = CourseUiState.Error(
                    e.message ?: "Unable to load courses"
                )
            }
        }
    }

}