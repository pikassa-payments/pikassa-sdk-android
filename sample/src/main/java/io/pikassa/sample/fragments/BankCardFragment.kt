package io.pikassa.sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import io.pikassa.sample.databinding.FragmentBankCardBinding
import io.pikassa.sample.ext.hideKeyboard
import io.pikassa.sample.ext.shortToast
import io.pikassa.sample.viewmodels.BankCardViewModel


class BankCardFragment : BaseFragment() {

    private val viewModel: BankCardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBankCardBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        observeViewModel()
        return binding.root
    }


    private fun observeViewModel() {
        viewModel.requestReceived.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            activity?.shortToast(it.toString())
            val action = BankCardFragmentDirections.gotoTransactionHistory()
            findNavController().navigate(action)
        })

        viewModel.errorReceived.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            activity?.shortToast(it.toString())
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = BankCardFragment()
    }
}