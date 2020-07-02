package io.pikassa.sample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.pikassa.sample.R
import io.pikassa.sample.databinding.FragmentWebViewBinding


/**
Created by Denis Chornyy on 02,Июль,2020
All rights received.
 */
class WebViewFragment: Fragment() {

    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWebViewBinding.inflate(inflater, container, false)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean  {
                if (request.url.toString() == requireContext().resources.getString(R.string.payment_url_link)) {
                        val action = WebViewFragmentDirections.actionWebViewFragmentToTransactionInfoFragment(args.uuid)
                        findNavController().navigate(action)
                    }
                return false
            }
        }
        binding.webView.loadUrl(args.url)
        return binding.root
    }
}