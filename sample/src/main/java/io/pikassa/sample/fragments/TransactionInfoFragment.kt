package io.pikassa.sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.pikassa.sample.adapters.SimpleAdapter
import io.pikassa.sample.databinding.FragmentTransactionInfoBinding
import io.pikassa.sample.viewmodels.TransactionInfoViewModel

/**
Created by Denis Chornyy on 29,Июнь,2020
All rights received.
 */
class TransactionInfoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: TransactionInfoViewModel by viewModels()

        val binding = FragmentTransactionInfoBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewmodel
        }

        binding.recyclerviewHistory.adapter = SimpleAdapter(listOf())

        return binding.root
    }
}