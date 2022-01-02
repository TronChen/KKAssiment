package com.tron.kkdayassiment.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tron.kkdayassiment.R
import com.tron.kkdayassiment.databinding.WelcomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private val viewModel: WelcomeViewModel by viewModels()

    private var _binding: WelcomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WelcomeFragmentBinding.inflate(inflater)

        viewModel.getAllHistory()

        viewModel.isDatabaseEmpty.observe(viewLifecycleOwner) {
            if (it) {
                showEmptyView()
            } else {
                showNotEmptyView()
            }
        }

        binding.btCheck.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_historyFragment)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showEmptyView() {
        binding.tvTitle.text = context?.getString(R.string.empty_result_title) ?: ""
        binding.tvContent.visibility = View.VISIBLE
        binding.btCheck.visibility = View.INVISIBLE
    }

    private fun showNotEmptyView() {
        binding.tvTitle.text = context?.getString(R.string.not_empty_result_title) ?: ""
        binding.tvContent.visibility = View.INVISIBLE
        binding.btCheck.visibility = View.VISIBLE
    }
}
