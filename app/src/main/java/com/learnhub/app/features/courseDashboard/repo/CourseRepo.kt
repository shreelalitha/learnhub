package com.learnhub.app.features.courseDashboard.repo

import com.learnhub.app.data.retrofit.CoursesApi
import com.learnhub.app.data.room.CourseDao
import com.learnhub.app.data.room.CourseEntity
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject


class CourseRepo @Inject constructor(
    private val courseApi: CoursesApi,
    private val courseDao: CourseDao
) {

}
