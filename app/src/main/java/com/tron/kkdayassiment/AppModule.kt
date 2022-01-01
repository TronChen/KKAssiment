package com.tron.kkdayassiment

import com.tron.shared.AppSchedulers
import com.tron.shared.Scheduler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @InstallIn(SingletonComponent::class)
    @Module
    abstract class Bind {

        @Binds
        abstract fun bindScheduler(appSchedulers: AppSchedulers): Scheduler
    }
}
