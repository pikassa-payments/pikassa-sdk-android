package io.pikassa.sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import io.pikassa.sample.R
import io.pikassa.sample.adapters.SimpleAdapter
import io.pikassa.sample.databinding.FragmentTransactionInfoBinding
import io.pikassa.sample.entities.OrderHistoryData
import io.pikassa.sample.ext.SimpleItemDecoration
import io.pikassa.sample.ext.reObserve
import io.pikassa.sample.ext.shortToast
import io.pikassa.sample.viewmodels.TransactionInfoViewModel
import io.pikassa.sample.viewmodels.TransactionInfoViewModelFactory
import kotlinx.android.synthetic.main.fragment_transaction_info.*

/**
Created by Denis Chornyy on 29,Июнь,2020
All rights received.
 */
class TransactionInfoFragment : Fragment() {

    private val args: TransactionInfoFragmentArgs by navArgs()
    private val viewModel: TransactionInfoViewModel by viewModels {
        TransactionInfoViewModelFactory(requireActivity().application, args.uuid)
    }

    private val simpleAdapter = SimpleAdapter()
    private val observer = Observer<OrderHistoryData> {
        simpleAdapter.onAddNewItem(it)
        recyclerviewHistory.smoothScrollToPosition(0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentTransactionInfoBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@TransactionInfoFragment
            viewModel = viewModel
        }
        binding.recyclerviewHistory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewHistory.addItemDecoration(SimpleItemDecoration(requireContext()))
        binding.recyclerviewHistory.adapter = simpleAdapter

        observeViewModel(viewModel)
        viewModel.getStatus()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.newStatus.reObserve(this, observer)
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
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}