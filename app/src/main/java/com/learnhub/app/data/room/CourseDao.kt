package com.learnhub.app.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.learnhub.app.features.courseDashboard.model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Query("SELECT * FROM courses")
    fun getCourses(): Flow<List<Course>>

    @Query("SELECT * FROM courses WHERE courseId = :courseId")
    fun getCourse(courseId: String): Flow<Course?>
}