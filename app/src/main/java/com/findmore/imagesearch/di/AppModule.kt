package com.findmore.imagesearch.di

import android.content.Context
import androidx.room.Room
import com.findmore.imagesearch.db.PhotoDatabase
import com.findmore.imagesearch.util.Constants.Companion.RUNNING_DATABASE_NAME

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        PhotoDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideSearchDao(db: PhotoDatabase) = db.getSearchDao()
}








