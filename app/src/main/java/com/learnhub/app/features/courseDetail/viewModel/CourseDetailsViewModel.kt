package com.learnhub.app.features.courseDetail.viewModel

import androidx.lifecycle.ViewModel
import com.learnhub.app.features.courseDashboard.repo.CourseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseDetailsViewModel @Inject constructor(
    private val repo: CourseRepo
): ViewModel(){

    private val _uiState = MutableStateFlow<CourseDetailsUiState>(CourseDetailsUiState.Loading)
    val uiState: StateFlow<CourseDetailsUiState> = _uiState.asStateFlow()

    fun getCourse(courseId: String) {
        viewModelScope.launch {
            repo.getCourseFromRoom(courseId).collect { course ->
                _uiState.value = if (course != null) {
                    CourseDetailsUiState.Success(course)
                } else {
                    CourseDetailsUiState.Error("Course not found")
                }
            }
        }
    }

    fun markLessonCompleted(lessonId: String) {
        val state = _uiState.value
        if (state !is CourseDetailsUiState.Success) return

        val course = state.course

        val updatedLessons = course.lessons.map { lesson ->
            if (lesson.lessonId == lessonId) {
                lesson.copy(completed = true)
            } else {
                lesson
            }
        }

        viewModelScope.launch {
            repo.updateCourseToRoom(
                course.copy(
                    lessons = updatedLessons,
                    completed = updatedLessons.count { it.completed }
                )
            )
        }
    }
}