package com.tron.kkdayassiment.database

import android.content.Context
import com.tron.shared.dao.ResponseDao
import com.tron.shared.domain.ResponseRepository
import com.tron.shared.impl.ResponseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DataModule.Bind::class])
object DataModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext appContext: Context): Database =
        Database.get(appContext)

    @Provides
    @Singleton
    fun provideResponseDao(database: Database): ResponseDao = database.responseDao()

    @InstallIn(SingletonComponent::class)
    @Module
    abstract class Bind {
        @Binds
        abstract fun bindResponseRepository(responseImpl: ResponseImpl): ResponseRepository
    }
}
