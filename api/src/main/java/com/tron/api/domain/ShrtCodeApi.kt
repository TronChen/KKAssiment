package com.tron.api.domain

import com.tron.shared.model.ShrtCode
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ShrtCodeApi {
    @GET("shorten")
    fun getFirmware(@Query("url") url: String): Single<ShrtCode>
}
