package com.learnhub.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CourseEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CourseDB: RoomDatabase() {
    abstract fun courseDao(): CourseDao
}