package com.tron.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.tron.api.domain.ShrtCodeApi
import com.tron.api.domain.ShrtCodeRepository
import com.tron.api.impl.ShrtCodeImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://api.shrtco.de/v2/"

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BODY
        }.let {
            OkHttpClient.Builder()
                .addInterceptor(it)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
        }
    }

    @Provides
    @Singleton
    fun provideShrtCodeApi(client: OkHttpClient): ShrtCodeApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ShrtCodeApi::class.java)

    @InstallIn(SingletonComponent::class)
    @Module
    abstract class Bind {
        @Binds
        abstract fun bindShrtCodeRepository(shrtCodeImpl: ShrtCodeImpl): ShrtCodeRepository
    }
}