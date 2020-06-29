package io.pikassa.sample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import io.pikassa.sample.R
import io.pikassa.sample.databinding.FragmentBankCardBinding
import io.pikassa.sample.viewmodels.CardInfoViewModel


class BankCardFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: CardInfoViewModel by viewModels()
        val binding = FragmentBankCardBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        observeViewModel(viewModel)
        return binding.root
    }

    private fun observeViewModel(viewModel: CardInfoViewModel) {

    }

    companion object {
        @JvmStatic
        fun newInstance() = BankCardFragment()
    }
}