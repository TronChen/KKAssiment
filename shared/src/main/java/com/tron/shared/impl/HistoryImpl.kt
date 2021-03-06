package com.tron.shared.impl

import com.tron.shared.dao.HistoryDao
import com.tron.shared.domain.HistoryRepository
import com.tron.shared.model.History
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class HistoryImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryRepository {

    override fun count(): Single<Int> = historyDao.count()

    override fun getAllHistory(): Flowable<List<History>> = historyDao.getAllHistory()

    override fun insertHistory(history: History): Completable = historyDao.insertHistory(history)

    override fun updateHistory(history: History): Completable = historyDao.updateHistory(history)

    override fun deleteHistory(key: Int): Completable = historyDao.deleteHistory(key)

    override fun clear(): Completable = historyDao.clear()
}
