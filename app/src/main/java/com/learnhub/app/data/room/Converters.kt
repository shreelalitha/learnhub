package com.learnhub.app.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.learnhub.app.features.courseDetail.model.Lesson

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromLessons(lessons: List<Lesson>): String {
        return gson.toJson(lessons)
    }

    @TypeConverter
    fun toLessons(json: String): List<Lesson> {
        val type = object : TypeToken<List<Lesson>>() {}.type
        return gson.fromJson(json, type)
    }
}