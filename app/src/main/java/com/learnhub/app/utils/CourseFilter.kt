package com.learnhub.app.utils

import com.learnhub.app.data.room.CourseEntity

fun filterContinueCourses(courses: List<CourseEntity>): List<CourseEntity> {
    return courses.filter {
        it.completed > 0 && it.completed < it.lessonsCount
    }
}

fun filterExploreCourses(courses: List<CourseEntity>): List<CourseEntity> {
    return courses.filter {
        it.completed == 0
    }
}

fun filterCompletedCourses(courses: List<CourseEntity>): List<CourseEntity> {
    return courses.filter {
        it.lessonsCount > 0 && it.completed >= it.lessonsCount
    }
}