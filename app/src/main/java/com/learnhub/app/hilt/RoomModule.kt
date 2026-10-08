package com.learnhub.app.hilt

import android.content.Context
import androidx.room.Room
import com.learnhub.app.data.room.CourseDB
import com.learnhub.app.data.room.CourseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CourseDB{
        return Room.databaseBuilder(
            context,
            CourseDB::class.java,
            "course_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCourseDao(database: CourseDB): CourseDao {
        return database.courseDao()
    }
}