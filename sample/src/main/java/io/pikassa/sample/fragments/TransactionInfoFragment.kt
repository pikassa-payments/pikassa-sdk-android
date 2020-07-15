package io.pikassa.sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.pikassa.sample.R
import io.pikassa.sample.databinding.FragmentTransactionInfoBinding
import io.pikassa.sample.ext.shortToast
import io.pikassa.sample.viewmodels.TransactionInfoViewModel
import io.pikassa.sample.viewmodels.TransactionInfoViewModelFactory

/**
Created by pikassa, support@pikassa.io on 29,Июнь,2020
All rights received.
 */
class TransactionInfoFragment : Fragment() {

    private val args: TransactionInfoFragmentArgs by navArgs()
    private val viewModel: TransactionInfoViewModel by viewModels {
        TransactionInfoViewModelFactory(requireActivity().application, args.uuid)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTransactionInfoBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }

        observeViewModel(viewModel)
        viewModel.getStatus()

        return binding.root
    }

    private fun observeViewModel(
        viewModel: TransactionInfoViewModel
    ) {
        viewModel.error.observe(
            viewLifecycleOwner,
            Observer { activity?.shortToast(it.toString()) })
        viewModel.noInternet.observe(
            viewLifecycleOwner,
            Observer { activity?.shortToast(getString(R.string.no_internet)) })
        viewModel.newOrder.observe(
            viewLifecycleOwner,
            Observer { findNavController().navigateUp() }
        )
    }
}