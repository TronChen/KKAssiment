package com.tron.shared.domain

import com.tron.shared.model.History
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface HistoryRepository {

    fun count(): Single<Int>

    fun getAllHistory(): Flowable<List<History>>

    fun insertHistory(history: History): Completable

    fun updateHistory(history: History): Completable

    fun deleteHistory(key: Int): Completable

    fun clear(): Completable
}
