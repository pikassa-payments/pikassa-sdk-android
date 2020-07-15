package io.pikassa.sample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import dev.icerock.moko.resources.desc.desc
import io.pikassa.sample.R
import io.pikassa.sample.databinding.FragmentBuyBinding
import io.pikassa.sample.ext.hideKeyboard
import io.pikassa.sample.ext.shortToast
import io.pikassa.sample.viewmodels.BuyViewModel


class BuyFragment : Fragment() {

    val viewModel: BuyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBuyBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewmodel = viewModel
            }
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.paymentCreated.observe(viewLifecycleOwner, Observer {
            hideKeyboard()
            val action = BuyFragmentDirections.gotoCardInfo(it)
            findNavController().navigate(action)
        })
        viewModel.isError.observe(
            viewLifecycleOwner,
            Observer { activity?.shortToast(it.toString()) })
        viewModel.noInternet.observe(
            viewLifecycleOwner,
            Observer { activity?.shortToast(getString(R.string.no_internet)) })
    }

}