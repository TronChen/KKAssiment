package com.tron.kkdayassiment.main

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tron.kkdayassiment.ShrtCodeUseCase
import com.tron.shared.Event
import com.tron.shared.Scheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val scheduler: Scheduler,
    private val shrtCodeUseCase: ShrtCodeUseCase
) : ViewModel() {

    private val trashBin = CompositeDisposable()

    private val _isInputValid = MutableLiveData<Boolean>()
    val isInputValid: LiveData<Boolean>
        get() = _isInputValid

    private val _state = MutableLiveData<Event<Boolean>>()
    val state: LiveData<Event<Boolean>>
        get() = _state

    private var searchText: String = ""

    override fun onCleared() {
        super.onCleared()
        trashBin.clear()
    }

    fun setTextValueAndCheckIsInputValid(input: String) {
        searchText = input
        checkIsInputValid(input)
    }

    fun getShrtCode() {
        shrtCodeUseCase.invoke(searchText)
            .doOnSubscribe { _state.postValue(Event.loading()) }
            .doOnComplete { _state.postValue(Event.success(true)) }
            .doOnError { _state.postValue(Event.error(it.message)) }
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.io())
            .subscribe(
                {
                    Log.d("getShrtCode", "Success")
                },
                {
                    Log.e("getShrtCode", "${it.message}")
                }
            )
            .apply { trashBin.add(this) }
    }

    private fun checkIsInputValid(input: String) {
        if (Patterns.WEB_URL.matcher(input).matches())
            _isInputValid.postValue(true)
        else _isInputValid.postValue(false)
    }
}
