package io.pikassa.sample.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.pikassa.sample.R
import io.pikassa.sample.databinding.FragmentBankCardBinding
import io.pikassa.sample.ext.hideKeyboard
import io.pikassa.sample.ext.shortToast
import io.pikassa.sample.helpers.SeparatorTextWatcher
import io.pikassa.sample.viewmodels.BankCardViewModel
import io.pikassa.sample.viewmodels.BankCardViewModelFactory


class BankCardFragment : Fragment() {

    private val args: BankCardFragmentArgs by navArgs()
    private val viewModel: BankCardViewModel by viewModels {
        BankCardViewModelFactory(requireActivity().application, args.orderData)
    }
    private var panCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBankCardBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        binding.edittextExpirationDate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, start: Int, removed: Int, added: Int) {
                if (start == 1 && start+added == 2 && p0?.contains('/') == false) {
                    binding.edittextExpirationDate.setText("$p0/")
                    binding.edittextExpirationDate.setSelection(start + 2)
                } else if (start == 3 && start-removed == 2 && p0?.contains('/') == true) {
                    binding.edittextExpirationDate.setText(p0.toString().replace("/", ""))
                    binding.edittextExpirationDate.setSelection(start-1 )
                }
                else if (start == 2 && start+added == 3 && p0?.contains('/') == false) {
                    binding.edittextExpirationDate.setText("${p0.substring(0, 2)}/${p0[2]}")
                    binding.edittextExpirationDate.setSelection(start+2)
                }
            }
        })

        binding.edittextPan.addTextChangedListener(object : SeparatorTextWatcher(' ', 4){
            override fun onAfterTextChanged(text: String) {
                binding.edittextPan.run {
                    setText(text)
                    setSelection(text.length)
                }
            }

        })

        observeViewModel()
        return binding.root
    }


    private fun observeViewModel() {
        viewModel.requestReceived.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            //activity?.shortToast(it.toString())
            if (it.redirect != null) {
                val action = BankCardFragmentDirections.actionBankCardFragmentToWebViewFragment(
                    it.redirect!!.url,
                    args.orderData.uuid,
                    args.orderData.successUrl,
                    args.orderData.failUrl
                )
                findNavController().navigate(action)
            } else {
                val action = BankCardFragmentDirections.gotoTransactionHistory(args.orderData.uuid)
                findNavController().navigate(action)
            }
        })

        viewModel.errorReceived.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            activity?.shortToast(it.toString())
        })
        viewModel.noInternet.observe(
            viewLifecycleOwner,
            Observer { activity?.shortToast(getString(R.string.no_internet)) })
    }
}