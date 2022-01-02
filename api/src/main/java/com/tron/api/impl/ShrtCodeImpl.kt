package com.tron.api.impl

import com.tron.api.domain.ShrtCodeApi
import com.tron.api.domain.ShrtCodeRepository
import com.tron.shared.exception.ShrtCodeBadRequestException
import com.tron.shared.model.Response
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShrtCodeImpl @Inject constructor(
    private val shrtCodeApi: ShrtCodeApi
) : ShrtCodeRepository {
    override fun getShrtCode(url: String): Single<Response> =
        shrtCodeApi.getFirmware(url)
            .map {
                if (it.ok)
                    it.result
                else error(ShrtCodeBadRequestException())
            }
}
