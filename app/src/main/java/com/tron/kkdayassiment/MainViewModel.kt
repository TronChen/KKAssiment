package com.tron.kkdayassiment

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun checkIsInputValid(input: String) {
        if (Patterns.WEB_URL.matcher(input).matches())
            _isInputValid.postValue(true)
        else _isInputValid.postValue(false)
    }
}
