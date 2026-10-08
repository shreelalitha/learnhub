package com.learnhub.app.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learnhub.app.features.courseDetail.model.Lesson

@Entity(tableName = "courses")
data class CourseEntity (
    @PrimaryKey
    val courseId: String,
    val title: String,
    val instructor: String,
    val completed: Int,
    val lessonsCount: Int,
    val imageUrl: String,
    val lessons: List<Lesson>
)