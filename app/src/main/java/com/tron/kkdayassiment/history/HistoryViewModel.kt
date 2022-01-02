package com.tron.kkdayassiment.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tron.shared.Scheduler
import com.tron.shared.domain.HistoryRepository
import com.tron.shared.model.History
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val scheduler: Scheduler,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val trashBin = CompositeDisposable()

    private val _historyList = MutableLiveData<List<History>>()
    val historyList: LiveData<List<History>>
        get() = _historyList

    override fun onCleared() {
        super.onCleared()
        trashBin.clear()
    }

    fun getAllHistory() {
        historyRepository.getAllHistory()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.io())
            .subscribe(
                {
                    Log.d("getAllHistory", "$it")
                    _historyList.postValue(it)
                },
                {
                    Log.e("getAllHistory", "${it.message}")
                }
            )
            .apply { trashBin.add(this) }
    }

    fun cancelHistory(history: History) {
        historyRepository.deleteHistory(history.key)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.io())
            .subscribe()
            .apply { trashBin.add(this) }
    }
}
