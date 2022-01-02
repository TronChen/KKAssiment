package com.tron.kkdayassiment.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tron.kkdayassiment.R
import com.tron.shared.afterTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.shorten_view
import kotlinx.android.synthetic.main.view_search.view.bt_shorten
import kotlinx.android.synthetic.main.view_search.view.tf_short_code

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shorten_view.tf_short_code.afterTextChanged {
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
            if (!it) {
                setEditTextError()
            }
        }
    }

    private fun setEditTextError() {
        shorten_view.tf_short_code.error
        shorten_view.tf_short_code.hint = "So bad bad"
        shorten_view.tf_short_code.setHintTextColor(applicationContext.getColorStateList(R.color.design_default_color_error))
    }
}
