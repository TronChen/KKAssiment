package com.tron.shared.domain

import com.tron.shared.model.Response
import io.reactivex.Completable
import io.reactivex.Single

interface ResponseRepository {

    fun count(): Single<Int>

    fun getAllResponse(): Single<List<Response>>

    fun insertResponse(response: Response): Completable

    fun updateResponse(response: Response): Completable

    fun deleteResponse(code: String): Completable

    fun clear(): Completable
}
