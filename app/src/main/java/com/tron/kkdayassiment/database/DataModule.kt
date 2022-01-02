package com.tron.kkdayassiment.database

import android.content.Context
import com.tron.shared.dao.HistoryDao
import com.tron.shared.domain.HistoryRepository
import com.tron.shared.impl.HistoryImpl
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
    fun provideResponseDao(database: Database): HistoryDao = database.historyDao()

    @InstallIn(SingletonComponent::class)
    @Module
    abstract class Bind {
        @Binds
        abstract fun bindResponseRepository(responseImpl: HistoryImpl): HistoryRepository
    }
}
