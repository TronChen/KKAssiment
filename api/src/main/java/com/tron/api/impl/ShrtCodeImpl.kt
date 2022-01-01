package com.tron.api.impl

import com.tron.api.domain.ShrtCodeApi
import com.tron.api.domain.ShrtCodeRepository
import com.tron.shared.model.ShrtCode
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShrtCodeImpl @Inject constructor(
    private val shrtCodeApi: ShrtCodeApi
) : ShrtCodeRepository {

    override fun getShrtCode(url: String): Single<ShrtCode> = shrtCodeApi.getFirmware(url)
}
