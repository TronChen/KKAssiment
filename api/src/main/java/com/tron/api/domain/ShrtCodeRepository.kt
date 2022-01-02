package com.tron.api.domain

import com.tron.shared.model.Response
import io.reactivex.Single

interface ShrtCodeRepository {
    fun getShrtCode(url: String): Single<Response>
}
