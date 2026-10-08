package com.learnhub.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CourseEntity::class], version = 1)
abstract class CourseDB: RoomDatabase() {
    abstract fun courseDao(): CourseDao
}