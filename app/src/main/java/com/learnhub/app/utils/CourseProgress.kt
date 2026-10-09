package com.learnhub.app.utils

fun calcCourseProgress(completedCount: Int, totalLessons: Int): Float {
    if (totalLessons <= 0) return 0f
    return completedCount.toFloat() / totalLessons
}