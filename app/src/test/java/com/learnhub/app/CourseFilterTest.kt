package com.learnhub.app


import com.learnhub.app.data.room.CourseEntity
import com.learnhub.app.utils.filterCompletedCourses
import com.learnhub.app.utils.filterContinueCourses
import com.learnhub.app.utils.filterExploreCourses
import org.junit.Assert.assertEquals
import org.junit.Test

class CourseFilterTest {

    private fun createCourse(
        id: String,
        completed: Int,
        total: Int
    ) = CourseEntity(
        courseId = id,
        title = "Course $id",
        instructor = "Instructor",
        completed = completed,
        lessonsCount = total,
        imageUrl = "",
        lessons = emptyList()
    )

    private val courses = listOf(
        createCourse("1", 0, 5),  // Explore
        createCourse("2", 2, 5),  // Continue
        createCourse("3", 5, 5),  // Completed
        createCourse("4", 0, 0)   // No lessons
    )

    @Test
    fun continueCourses_returnsPartiallyCompletedCourses() {
        assertEquals(
            listOf("2"),
            filterContinueCourses(courses).map { it.courseId }
        )
    }

    @Test
    fun exploreCourses_returnsNotStartedCourses() {
        assertEquals(
            listOf("1", "4"),
            filterExploreCourses(courses).map { it.courseId }
        )
    }

    @Test
    fun completedCourses_returnsFullyCompletedCourses() {
        assertEquals(
            listOf("3"),
            filterCompletedCourses(courses).map { it.courseId }
        )
    }
}
