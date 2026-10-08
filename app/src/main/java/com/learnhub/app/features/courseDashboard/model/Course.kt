package com.learnhub.app.features.courseDashboard.model

import com.learnhub.app.features.courseDetail.model.Lesson

data class Course(
    val id: Int,
    val title: String,
    val instructor: String,
    val progress: Int,
    val lessons: List<Lesson>
)
