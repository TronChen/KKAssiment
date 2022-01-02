package com.tron.kkdayassiment.history

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tron.kkdayassiment.R
import com.tron.kkdayassiment.databinding.HistoryFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private val viewModel: HistoryViewModel by viewModels()

    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HistoryFragmentBinding.inflate(inflater)

        val adapter = HistoryAdapter(
            cancelHistory = { history ->
                viewModel.cancelHistory(history)
            },
            copyText = { history ->
                copyTextToClipboard(history.full_short_link)
            },
            browserPageIntent = { history ->
                launchWebPageIntent(history.full_short_link)
            }
        )

        binding.rvHistory.adapter = adapter

        viewModel.getAllHistory()

        viewModel.historyList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun copyTextToClipboard(shortLink: String) {
        val clipboardManager = context?.let { getSystemService(it, ClipboardManager::class.java) }
        val clipData = ClipData.newPlainText("text", shortLink)
        clipboardManager?.setPrimaryClip(clipData)
        Toast.makeText(context, getString(R.string.toast_text_copy), Toast.LENGTH_LONG).show()
    }

    private fun launchWebPageIntent(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (error: Throwable) {
            Log.e("launchWebPageIntent", "${error.message}")
        }
    }
}
