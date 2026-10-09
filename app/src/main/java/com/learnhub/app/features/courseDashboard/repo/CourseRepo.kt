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

    fun getCoursesFromRoom(): Flow<List<CourseEntity>> {
        return courseDao.getCourses()
    }

    fun getCourseFromRoom(courseId: String): Flow<CourseEntity?> {
        return courseDao.getCourse(courseId)
    }

    suspend fun fetchCoursesFromRemote(): List<CourseEntity> {
        return try {
            courseApi.getCourses()
        } catch (e: SocketTimeoutException) {
            throw Exception("Oops! The request took too long")
        } catch (e: IOException) {
            throw Exception("Oops! There is no internet")
        } catch (e: Exception) {
            throw Exception("Uh oh! Unable to load courses")
        }
    }

    suspend fun insertCoursesToRoom(courses: List<CourseEntity>) {
        courseDao.insertCourses(courses)
    }

    suspend fun updateCourseToRoom(course: CourseEntity) {
        courseDao.insertCourse(course)
    }
}
