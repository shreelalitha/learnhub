package com.learnhub.app.features.courseDetail.model

data class Lesson(
    val id: Int,
    val courseId: Int,
    val title: String,
    val completed: Boolean
)
