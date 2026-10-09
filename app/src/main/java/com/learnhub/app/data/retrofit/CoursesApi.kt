package com.learnhub.app.data.retrofit

import com.learnhub.app.data.room.CourseEntity
import com.learnhub.app.features.courseDashboard.model.Course
import retrofit2.http.GET

interface CoursesApi {

    @GET("courses")
    suspend fun getCourses(): List<CourseEntity>
}