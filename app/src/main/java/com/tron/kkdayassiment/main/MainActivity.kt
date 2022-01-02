package com.tron.kkdayassiment.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tron.kkdayassiment.R
import com.tron.shared.Event
import com.tron.shared.EventState
import com.tron.shared.afterTextChanged
import com.tron.shared.unless
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.progressBar
import kotlinx.android.synthetic.main.activity_main.shorten_view
import kotlinx.android.synthetic.main.view_search.view.bt_shorten
import kotlinx.android.synthetic.main.view_search.view.et_short_code
import kotlinx.android.synthetic.main.view_search.view.ti_short_code

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shorten_view.et_short_code.afterTextChanged {
            Log.d("EditText", it)
            viewModel.setTextValueAndCheckIsInputValid(it)
        }

        shorten_view.bt_shorten.setOnClickListener {
            if (viewModel.isInputValid.value == true) {
                viewModel.getShrtCode()
            } else {
                setEditTextError()
            }
        }

        viewModel.isInputValid.observe(this) {
            Log.d("isInputValid", "$it")
            if (it) {
                setEditTextCorrect()
            } else {
                setEditTextError()
            }
        }

        viewModel.state.observe(this) {
            handleState(it)
        }
    }

    private fun setEditTextError() {
        shorten_view.ti_short_code.error = getString(R.string.input_not_valid)
        shorten_view.ti_short_code.hintTextColor = this.getColorStateList(R.color.design_default_color_error)
    }

    private fun setEditTextCorrect() {
        shorten_view.ti_short_code.error = null
    }

    private fun handleState(event: Event<Boolean>) {
        unless(!event.hasBeenHandled) {
            when (event.status) {
                EventState.ERROR -> {
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, getString(R.string.toast_bad_request), Toast.LENGTH_LONG).show()
                }
                EventState.SUCCESS -> {
                    progressBar.visibility = View.INVISIBLE
                }
                EventState.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                else -> {}
            }
        }
    }
}
