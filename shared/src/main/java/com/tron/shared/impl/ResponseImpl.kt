package com.tron.shared.impl

import com.tron.shared.dao.ResponseDao
import com.tron.shared.domain.ResponseRepository
import com.tron.shared.model.Response
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ResponseImpl @Inject constructor(
    private val responseDao: ResponseDao
) : ResponseRepository {

    override fun count(): Single<Int> = responseDao.count()

    override fun getAllResponse(): Single<List<Response>> = responseDao.getAllResponse()

    override fun insertResponse(response: Response): Completable = responseDao.insertResponse(response)

    override fun updateResponse(response: Response): Completable = responseDao.updateResponse(response)

    override fun deleteResponse(code: String): Completable = responseDao.deleteResponse(code)

    override fun clear(): Completable = responseDao.clear()
}
