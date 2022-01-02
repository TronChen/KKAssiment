package com.tron.kkdayassiment.welcome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tron.shared.Scheduler
import com.tron.shared.domain.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val scheduler: Scheduler,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val trashBin = CompositeDisposable()

    private val _isDatabaseEmpty = MutableLiveData<Boolean>()
    val isDatabaseEmpty: LiveData<Boolean>
        get() = _isDatabaseEmpty

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
                    if (it.isNullOrEmpty()) _isDatabaseEmpty.postValue(true)
                    else _isDatabaseEmpty.postValue(false)
                },
                {
                    Log.e("getAllHistory", "${it.message}")
                }
            )
            .apply { trashBin.add(this) }
    }
}
