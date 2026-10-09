package com.learnhub.app

import com.learnhub.app.utils.calcCourseProgress
import org.junit.Assert.assertEquals
import org.junit.Test

class CourseProgressTest {

    @Test
    fun calculateCourseProgress_returnsCorrectProgress() {
        assertEquals(0.5f, calcCourseProgress(5, 10), 0.001f)
    }

    @Test
    fun calculateCourseProgress_returnsZeroForNoLessons() {
        assertEquals(0f, calcCourseProgress(0, 0), 0.001f)
    }

    @Test
    fun calculateCourseProgress_returnsFullProgress() {
        assertEquals(1f, calcCourseProgress(10, 10), 0.001f)
    }
}
